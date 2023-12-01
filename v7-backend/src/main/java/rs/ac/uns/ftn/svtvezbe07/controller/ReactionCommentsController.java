package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.*;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.model.entity.ReactionType;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.GroupServiceImpl;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.PostServiceImpl;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.ReactionCommentServiceImpl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reaction")
public class ReactionCommentsController {

    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserService userService;

    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ReactionCommentServiceImpl reactionCommentService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus saveReaction(Principal user, @RequestBody @Validated ReactionReceiverDTO dto) {

        User u = this.userService.findByUsername(user.getName());
        Reaction reaction = new Reaction();
        Post post = postService.getPostById(dto.getId());

        if(dto.getReactionInt() == 0)
        {
            reaction.setType(ReactionType.LIKE);
        }

        if(dto.getReactionInt() == 1)
        {
            reaction.setType(ReactionType.DISLIKE);
        }

        if(dto.getReactionInt() == 2)
        {
            reaction.setType(ReactionType.HEART);
        }
        reaction.setTimestamp(LocalDate.now());
        reaction.setUser(u.getId());
        reaction.setPost(post.getId());
        reactionCommentService.saveReaction(reaction);
        post.getReactions().add(reaction);
        postService.savePost(post);

        return HttpStatus.ACCEPTED;
    }

}
