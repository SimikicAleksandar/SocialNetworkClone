package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.UserDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.UserProfileDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Roles;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.repository.UserRepository;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
*/
    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public UserProfileDTO findDTOByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setDisplayName(user.get().getDisplayName());
        userProfileDTO.setDescription(user.get().getDescription());
        return userProfileDTO;
    }

    @Override
    public User createUser(UserDTO userDTO) {

        Optional<User> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(Roles.USER);
        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstname(userDTO.getFirstname());
        newUser.setLastname(userDTO.getLastname());
        newUser.setLastLogin(LocalDateTime.now());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void ChangeUserPassword(String username,String password) {
        Optional<User> user = userRepository.findFirstByUsername(username);

        User u=  user.get();
        u.setPassword(passwordEncoder.encode(password));
        userRepository.save(u);
    }
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
    @Override
    public User findUserById(Long id) {
        return this.userRepository.findUserById(id);
    }
    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}
