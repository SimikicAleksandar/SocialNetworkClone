package com.example.swt_kwt_aleksandar_simikic.Controller;

import com.example.swt_kwt_aleksandar_simikic.Dto.CreateGroupDto;
import com.example.swt_kwt_aleksandar_simikic.Dto.UpdateGroupDto;
import com.example.swt_kwt_aleksandar_simikic.Model.Group;
import com.example.swt_kwt_aleksandar_simikic.Model.User;
import com.example.swt_kwt_aleksandar_simikic.Service.GroupService;
import com.example.swt_kwt_aleksandar_simikic.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupDto createGroupDto){
        User user = userService.getCurrentUser();
        return groupService.createGroup(createGroupDto, user);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findGroupById(@PathVariable("id") Long id){
        return groupService.findById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findGroupByUser(@RequestParam(name = "user") Long userId){
        return groupService.findGroupsByUser(userId);
    }

    @GetMapping(path = {"/all"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Group>> getAll(){
        return (ResponseEntity<List<Group>>) groupService.getAll();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGroup(@PathVariable("id") Long id, @RequestBody UpdateGroupDto updateGroupDto) {
        return groupService.updateGroup(id, updateGroupDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Long id){
        return groupService.deleteGroup(id);
    }

    @PatchMapping(path = "/{id}/remove/{user-id}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable("id") Long groupId, @PathVariable("user-id") Long userId){
        return groupService.removeUserFromGroup(groupId, userId);
    }

    @PatchMapping(path = "{id}/add/{user-id}")
    public ResponseEntity<?> addUserToGroup(@PathVariable("id") Long groupId, @PathVariable("user-id") Long userId){
        return groupService.addUserToGroup(groupId, userId);
    }
}
