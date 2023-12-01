package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.ReactionDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.model.entity.ReactionType;
import rs.ac.uns.ftn.svtvezbe07.repository.PostRepository;
import rs.ac.uns.ftn.svtvezbe07.repository.ReactionCommentsRepository;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReactionCommentServiceImpl {

    @Autowired
    private UserService userService;

    @Autowired
    private ReactionCommentsRepository reactionCommentsRepository;
    @Autowired
    private PostRepository postRepository;

    public Reaction getReactionById(Long Id) {
        return this.reactionCommentsRepository.findReactionById(Id);


    }
    public Reaction saveReaction(Reaction reactionSaved) {
        return this.reactionCommentsRepository.save(reactionSaved);


    }
    public List<ReactionDTO> getAllReactions(ReactionType type) {

        List<ReactionDTO> reactionDTOList= new ArrayList<ReactionDTO>();
        List<Reaction> reactionsEntityList = reactionCommentsRepository.findAllByType(type);
        for (Reaction reaction : reactionsEntityList) {
            ReactionDTO reactionDTO = new ReactionDTO();
            reactionDTO.setType(reaction.getType());
            reactionDTO.setTimestamp(reaction.getTimestamp());
            reactionDTO.setId(reaction.getId());
            reactionDTOList.add(reactionDTO);
        }
        return reactionDTOList;
    }
}
