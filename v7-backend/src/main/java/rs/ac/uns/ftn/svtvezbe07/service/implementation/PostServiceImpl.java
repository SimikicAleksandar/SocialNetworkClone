package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Post;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.model.entity.ReactionType;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.repository.PostRepository;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service

@Primary
public class PostServiceImpl {
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    public Post createPost(String content, long userID) {


        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setCreationDate(LocalDateTime.now());
        newPost.setUser(userID);
        newPost.setDeleted(false);
        newPost = postRepository.save(newPost);
        return newPost;
    }
    public void deletePost(Long Id) {
        Post postDeleted =  this.postRepository.findFirstById(Id);
        postDeleted.setDeleted(true);
        this.postRepository.save(postDeleted);

    }
    public Post getPostById(Long Id) {
        return this.postRepository.findFirstById(Id);


    }
    public Post savePost(Post postSaved) {
        return this.postRepository.save(postSaved);


    }
    public List<PostDTO> getAllPosts(long a) {

        List<PostDTO> postsList= new ArrayList<PostDTO>();
        List<Post> postsEntityList = postRepository.findAllByUserAndDeleted(a,false);
        for (Post post:postsEntityList) {
            PostDTO postDTO = new PostDTO();
            User user = userService.findUserById(post.getUser());
            if(user.getDisplayName() != null){
                postDTO.setUser(user.getDisplayName());
            }
            else{
                postDTO.setUser(userService.findUserById(post.getUser()).getFirstname());
            }
            postDTO.setContent(post.getContent());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String formatDateTime = post.getCreationDate().format(formatter);
            postDTO.setCreationDate(formatDateTime);
            postDTO.setId(post.getId());
            postsList.add(postDTO);
        }
        return postsList;
    }

    public List<PostDTO> getAbsolutellyAllPosts() {

        List<PostDTO> allPostsList= new ArrayList<PostDTO>();
        List<Post> postsEntityList = postRepository.findAllByDeleted(false);
        for (Post post : postsEntityList) {
            PostDTO postDTO = new PostDTO();

            for(Reaction r : post.getReactions()){
                if(r.getType() == ReactionType.LIKE){
                    postDTO.setLikes(postDTO.getLikes() + 1);
                }
                if(r.getType() == ReactionType.DISLIKE){
                    postDTO.setDislikes(postDTO.getDislikes() + 1);
                }
                if(r.getType() == ReactionType.HEART){
                    postDTO.setHearts(postDTO.getHearts() + 1);
                }
            }
            User user = userService.findUserById(post.getUser());
            if(user.getDisplayName() != null){
                postDTO.setUser(user.getDisplayName());
            }
            else{
                postDTO.setUser(userService.findUserById(post.getUser()).getFirstname());
            }
            postDTO.setContent(post.getContent());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String formatDateTime = post.getCreationDate().format(formatter);
            postDTO.setCreationDate(formatDateTime);
            postDTO.setId(post.getId());
            allPostsList.add(postDTO);
        }
        return allPostsList;
    }
}
