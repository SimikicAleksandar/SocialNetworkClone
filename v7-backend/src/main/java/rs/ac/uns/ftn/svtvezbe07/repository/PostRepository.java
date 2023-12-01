package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserAndDeleted(Long User, boolean Deleted);

    List<Post> findAllByDeleted(boolean Deleted);
    Post findFirstById(Long Id);
}
