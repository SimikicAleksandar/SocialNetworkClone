package com.example.swt_kwt_aleksandar_simikic.Service;

import com.example.swt_kwt_aleksandar_simikic.Dto.PasswordChangeDto;
import com.example.swt_kwt_aleksandar_simikic.Model.User;
import com.example.swt_kwt_aleksandar_simikic.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            return new ResponseEntity<>("User with this Id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(PasswordChangeDto passwordChangeDto, User user){
         if(passwordChangeDto.getNewPassword().length() < 6){
            return new ResponseEntity<>("New password must be at least 6 characters long", HttpStatus.BAD_REQUEST);
        }

        if(user == null){
            return new ResponseEntity<>("User not found !", HttpStatus.NOT_FOUND);
        }

        if(!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getNewPasswordd())){
            return new ResponseEntity<>("New password must match!", HttpStatus.BAD_REQUEST);
        }

        if(!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())){
            return new ResponseEntity<>("Password not correct", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public User findByUsername(String username){
        Optional<User> user = userRepository.findFirstByUsername(username);
        if(!user.isEmpty()){
            return user.get();
        }
        return null ;
    }

    public User getUserFromAuthentication(Authentication authentication){
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return findByUsername(userDetails.getUsername());
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Claims claims = Jwts.parser().setSigningKey("anyString").parseClaimsJws(token).getBody();
            String username = claims.getSubject();

            Optional<User> optionalUser = userRepository.findByUsername(username);
            User user = optionalUser.get();
            return user;
        }

        return null;
    }



}
