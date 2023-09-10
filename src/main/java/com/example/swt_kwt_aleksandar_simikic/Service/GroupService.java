package com.example.swt_kwt_aleksandar_simikic.Service;

import com.example.swt_kwt_aleksandar_simikic.Dto.CreateGroupDto;
import com.example.swt_kwt_aleksandar_simikic.Dto.UpdateGroupDto;

import com.example.swt_kwt_aleksandar_simikic.Repository.CommentRepository;
import com.example.swt_kwt_aleksandar_simikic.Repository.GroupRepository;
import com.example.swt_kwt_aleksandar_simikic.Repository.PostRepository;
import com.example.swt_kwt_aleksandar_simikic.Repository.UserGroupRepository;
import com.example.swt_kwt_aleksandar_simikic.Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserService userService;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<?> createGroup(CreateGroupDto createGroupDto, User user){
        if(createGroupDto.getName() == null || createGroupDto.getName().isEmpty()){
            return new ResponseEntity<>("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        Group group = new Group();

        group.setCreatorGroup(user);
        group.setName(createGroupDto.getName());
        group.setDescription(createGroupDto.getDescription());
        group.setCreatedAt(LocalDateTime.now());
        group = groupRepository.save(group);

        UserGroup userGroup = new UserGroup(user, group);
        userGroupRepository.save(userGroup);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> findById(Long id){
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isEmpty()){
            return new ResponseEntity<>("Group with the given ID does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalGroup.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> findGroupsByUser(Long userId) {
        ResponseEntity<?> findByIdUserResponse = userService.findById(userId);
        if(findByIdUserResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return findByIdUserResponse;
        }
        List<UserGroup> userGroups = userGroupRepository.findByUserId(userId);
        if(userGroups.isEmpty()){
            return new ResponseEntity<>("The given user is not a member of any group", HttpStatus.NOT_FOUND);
        }

        List<Group> groups = new ArrayList<>();
        userGroups.forEach(userGroup -> {groups.add(userGroup.getGroup());});

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    public ResponseEntity<?> updateGroup(Long id, UpdateGroupDto updateGroupDto){
        if(updateGroupDto.getName() == null || updateGroupDto.getName().isEmpty()) {
            return new ResponseEntity<>("Name must not be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(optionalGroup.isEmpty()){
            return new ResponseEntity<>("Group with given ID does not exist", HttpStatus.NOT_FOUND);
        }

        Group group = optionalGroup.get();
        group.setName(updateGroupDto.getName());
        group.setDescription(updateGroupDto.getDescription());
        groupRepository.save(group);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteGroup(Long id){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()){
            return new ResponseEntity<>("Group with given ID does not exist", HttpStatus.NOT_FOUND);
        }

        Group group = optionalGroup.get();

        List<UserGroup> userGroups = userGroupRepository.findByGroupId(id);
        for(UserGroup userGroup : userGroups){
            removeUserFromGroup(userGroup.getGroup().getId(), userGroup.getUser().getId());
        }

        Post findPost = postRepository.findPostByContainedById(id);
        if(findPost != null){
            List<Comment> comments = (List<Comment>) commentRepository.findCommentsByBelongsToId(findPost.getId());
            for(Comment comment: comments){
                commentRepository.delete(comment);
            }
        }

        List<Post> posts = (List<Post>) postRepository.findByContainedById(id);
        for(Post post: posts){
            postRepository.delete(post);
        }

        groupRepository.delete(group);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> removeUserFromGroup(Long groupId, Long userId){
        ResponseEntity<?> findByIdGroupResponse = findById(groupId);
        if(findByIdGroupResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return findByIdGroupResponse;
        }

        ResponseEntity<?> findByIdUserResponse = userService.findById(userId);
        if(findByIdUserResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return findByIdUserResponse;
        }

        Optional<UserGroup> optionalUserGroup = userGroupRepository.findByUserIdAndGroupId(userId, groupId);
        if (optionalUserGroup.isEmpty()){
            return new ResponseEntity<>("User is not a group member", HttpStatus.CONFLICT);
        }

        userGroupRepository.delete(optionalUserGroup.get());
        return new ResponseEntity<>("User successfully removed from group !",  HttpStatus.OK);

    }

    public ResponseEntity<?> addUserToGroup(Long groupId, Long userId){
        ResponseEntity<?> findByIdGroupResponse = findById(groupId);
        if(findByIdGroupResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return findByIdGroupResponse;
        }

        ResponseEntity<?> findByIdUserResponse = userService.findById(userId);
        if(findByIdUserResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return findByIdUserResponse;
        }

        if(userGroupRepository.existsByUserIdAndGroupId(userId, groupId)){
            return new ResponseEntity<>("User is already a group member", HttpStatus.CONFLICT);
        }

        User user = (User) findByIdUserResponse.getBody();
        Group group = (Group) findByIdGroupResponse.getBody();

        UserGroup userGroup = new UserGroup(user, group);
        userGroupRepository.save(userGroup);

        return new ResponseEntity<>("User successfully added to group !", HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        List<Group> allGroups = new ArrayList<>();
        allGroups = groupRepository.findAll();
        return new ResponseEntity<>(allGroups, HttpStatus.OK);
    }

}
