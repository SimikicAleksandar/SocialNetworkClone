package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostGroupDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostSaveDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.postupdateDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.GroupServiceImpl;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.PostServiceImpl;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserService userService;

    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post createPost(Principal user, @RequestBody @Validated PostSaveDTO dto) {

        User u = this.userService.findByUsername(user.getName());

        Post post = this.postService.createPost(dto.getText(),u.getId());
        u.getPosts().add(post);
        userService.saveUser(u);
        return post;
    }

    @PostMapping("/createInGroup")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post createPostInGroup(Principal user, @RequestBody @Validated PostGroupDTO dto) {

        User u = this.userService.findByUsername(user.getName());
        Group postGroup = this.groupService.getGroupById(dto.getGroupID());
        Post post = this.postService.createPost(dto.getText(),u.getId());
        postGroup.getPosts().add(post);
        groupService.saveGroup(postGroup);
        u.getPosts().add(post);
        userService.saveUser(u);
        return post;
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus deletePost(Principal user, @RequestBody @Validated  Long id) {
        // Long id = Long.valueOf(idd);
        User u = this.userService.findByUsername(user.getName());

        if( this.postService.getPostById(id).getUser() == u.getId())
        {
            this.postService.deletePost(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }
    @GetMapping("/All")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PostDTO> allPosts(Principal user) {

        User u = this.userService.findByUsername(user.getName());
        return postService.getAllPosts(u.getId());

    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PostDTO> absolutellyAllPosts(Principal user) {

        return postService.getAbsolutellyAllPosts();

    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus savePost(Principal user, @RequestBody @Validated postupdateDTO dto ) {

        User u = this.userService.findByUsername(user.getName());
        Post post = postService.getPostById(dto.getId());

        if(post.getUser() == u.getId())
        {
            post.setContent(dto.getContent());
            postService.savePost(post);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @PostMapping("/one")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post getPostbyId(Principal user, @RequestBody @Validated Long id ) {

        User u = this.userService.findByUsername(user.getName());
        Post post = postService.getPostById(id);
        if(post.getUser() == u.getId())
        {

            return this.postService.getPostById(id);
        }
        else return null;
    }
}
