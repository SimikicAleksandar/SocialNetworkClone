package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupReceivedRequestDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.GroupServiceImpl;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/group")
public class GroupController {
    @Autowired
    GroupServiceImpl groupService;
    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Group> getAllGroups(Principal user) {
        return this.groupService.findAllGroups();
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus deleteGroup(Principal user, @RequestBody @Validated Long id) {
        User groupAdmin = this.userService.findByUsername(user.getName());

        if( this.groupService.getGroupById(id).getGroupAdmin() == groupAdmin.getId())
        {
            this.groupService.deleteGroup(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }
    @PostMapping("/one")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group getOneGroup(Principal user, @RequestBody @Validated  Long id) {
        User groupAdmin = this.userService.findByUsername(user.getName());

        if( this.groupService.getGroupById(id).getGroupAdmin() == groupAdmin.getId())
        {
            return groupService.getGroupById(id);

        }
        else return null;
    }
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group saveGroup(Principal user, @RequestBody @Validated GroupReceivedRequestDTO dto) {
        User groupAdmin = this.userService.findByUsername(user.getName());

        if( this.groupService.getGroupById(dto.getId()).getGroupAdmin() == groupAdmin.getId())
        {
            Group newGroup =  groupService.getGroupById(dto.getId());
            newGroup.setName(dto.getName());
            newGroup.setDescription(dto.getDescription());
            groupService.saveGroup(newGroup);

        }
        else return null;
        return null;
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group createGroup(Principal user,@RequestBody @Validated GroupDTO dto) {

        User groupAdmin = this.userService.findByUsername(user.getName());
        return this.groupService.createGroup(dto,groupAdmin.getId());
    }

}
