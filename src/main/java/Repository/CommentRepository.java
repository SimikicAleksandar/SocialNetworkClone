package Repository;

import Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBelongsToIdAndDeletedFalse(Long postId);
    List<Comment> findCommentsByBelongsToId(Long id);


}
