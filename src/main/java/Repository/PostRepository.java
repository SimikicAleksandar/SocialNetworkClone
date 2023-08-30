package Repository;

import Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedBy(Long userId);
    List<Post> findByContainedById(Long groupId);
    Post findPostByContainedById(Long groupId);
}
