package Service;

import Model.Comment;
import Model.Post;
import Model.User;
import Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        comment.setCreatedAt(LocalDateTime.now());
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
