package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.model.entity.ReactionType;

import java.util.List;
@Repository
@Primary
public interface ReactionCommentsRepository extends JpaRepository<Reaction, Long> {

    Reaction findReactionById(Long id);
    List<Reaction> findAllByType(ReactionType reactionType);
    List<Reaction> findAll();
}
