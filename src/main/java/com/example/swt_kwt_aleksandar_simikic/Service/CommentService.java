package com.example.swt_kwt_aleksandar_simikic.Service;

import com.example.swt_kwt_aleksandar_simikic.Model.Comment;
import com.example.swt_kwt_aleksandar_simikic.Model.Post;
import com.example.swt_kwt_aleksandar_simikic.Model.User;
import com.example.swt_kwt_aleksandar_simikic.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public Comment addCommentToPost(Long postId, Comment comment, User user){
        Post post = postService.findPostById(postId);
        comment.setBelongsTo(post);
        comment.setWrittenBy(user);

        return commentRepository.save(comment);
    }

    public Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findCommentsForPost(Long postId){
        return commentRepository.findByBelongsToIdAndDeletedFalse(postId);
    }

    public Comment updateComment(Long id, Comment updateComment){
        Comment comment = findCommentById(id);
        comment.setText(updateComment.getText());
        comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return commentRepository.save(comment);
    }

    public ResponseEntity<?> deleteComment(Long commentId){
        Comment comment = findCommentById(commentId);
        if(comment != null){
            comment.setDeleted(true);
            commentRepository.save(comment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
