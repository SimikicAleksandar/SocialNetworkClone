package rs.ac.uns.ftn.svtvezbe07.service;

import rs.ac.uns.ftn.svtvezbe07.model.dto.UserDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.UserProfileDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User findUserById(Long id);
    User saveUser(User user);

    UserProfileDTO findDTOByUsername(String username);
    User createUser(UserDTO userDTO);
    List<User> findAll();
    void ChangeUserPassword(String username, String password);

}
