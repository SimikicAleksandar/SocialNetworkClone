package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.repository.GroupRepository;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service

@Primary
public class GroupServiceImpl {
    @Autowired
    private GroupRepository groupRepository;
    public List<Group> findAllGroups() {
        return this.groupRepository.findAllByDeleted(false);
    }
    public void saveGroup(Group dt) {
        this.groupRepository.save(dt);
    }
    public void deleteGroup( Long Id) {
        Group grupa =  this.groupRepository.findGroupById(Id);
        grupa.setDeleted(true);
        this.groupRepository.save(grupa);

    }
    public Group getGroupById(Long Id) {
        return this.groupRepository.findGroupById(Id);


    }
    public Group createGroup(GroupDTO dto,long adminID) {

        Optional<Group> group = groupRepository.findGroupByNameAndDeleted(dto.getName(),false);

        if(group.isPresent()){
            return null;
        }

        Group newGroup = new Group();
        newGroup.setName(dto.getName());
        newGroup.setCreationDate(LocalDateTime.now());
        newGroup.setDescription(dto.getDescription());
        newGroup.setGroupAdmin(adminID);
        newGroup.setDeleted(false);
        newGroup = groupRepository.save(newGroup);


        return newGroup;
    }
}
