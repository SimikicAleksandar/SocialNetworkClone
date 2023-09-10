package com.example.swt_kwt_aleksandar_simikic.Controller;

import com.example.swt_kwt_aleksandar_simikic.Model.Comment;
import com.example.swt_kwt_aleksandar_simikic.Model.User;
import com.example.swt_kwt_aleksandar_simikic.Service.CommentService;
import com.example.swt_kwt_aleksandar_simikic.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment){
        User user = userService.getCurrentUser();
        Comment newComment = commentService.addCommentToPost(postId, comment, user);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> findCommentsForPost(@PathVariable Long postId){
        List<Comment> comments = commentService.findCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment updatedComment){
        Comment comment = commentService.updateComment(commentId, updatedComment);
        if(comment == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

}
