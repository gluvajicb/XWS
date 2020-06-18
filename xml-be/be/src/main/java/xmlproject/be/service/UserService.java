package xmlproject.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.xmltim.user.Role;
import rs.ac.uns.xmltim.user.User;
import xmlproject.be.dto.UserLoginDTO;
import xmlproject.be.dto.UserRegisterDTO;
import xmlproject.be.repository.UserRepository;
import xmlproject.be.security.TokenHelper;
import xmlproject.be.security.UserTokenState;

import java.util.Collections;
import java.util.List;

@Qualifier("UserService")
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    public List<String> findAllXml() throws Exception {
        return userRepository.findAllXml();
    }

    public User findById(String id) throws Exception {
        return userRepository.findById(id);
    }

    public String findByIdXml(String id) throws Exception {
        return userRepository.findByIdXml(id);
    }

    public UserDetails loadUserByUsername(String username) {
        User user = null;

        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByUsername(String username) throws Exception {
        return userRepository.findByUsername(username);
    }

    public String findByUsernameXml(String username) throws Exception {
        return userRepository.findByUsernameXml(username);
    }

    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    public String findByEmailXml(String email) throws Exception {
        return userRepository.findByEmailXml(email);
    }

    public UserTokenState loginUser(UserLoginDTO loginData) throws Exception {
        if (loginData.username == null || loginData.username.trim().equals("") || loginData.password == null || loginData.password.trim().equals("")) {
            throw new Exception("Username and password must be provided");
        }

        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.username, loginData.password));

            if (authentication != null && authentication.getName() != null) {
                User user = userRepository.findByUsername(authentication.getName());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenHelper.generateToken(authentication.getName());
                return new UserTokenState(jwt, 43000L, "Bearer", user.getID(), user.getUsername(), user.getEmail(), Collections.singletonList(user.getRole().value()));
            }
        } catch (Exception e) {
            throw new Exception("Wrong credentials");
        }

        return null;
    }

    public boolean registerUser(UserRegisterDTO userDTO) throws Exception {
        if (userDTO.name == null || userDTO.name.isEmpty()
                || userDTO.surname == null || userDTO.surname.isEmpty()
                || userDTO.username == null || userDTO.username.isEmpty()
                || userDTO.email == null || userDTO.email.isEmpty()
                || userDTO.password == null || userDTO.password.isEmpty()) {
            throw new Exception("All fields are required for registration.");
        }

        trimUserDataStrings(userDTO);

        if (userDTO.password.length() < 6 || userDTO.password.length() > 20) {
            throw new Exception("Password must be between 6 and 20 characters long");
        } else if (userDTO.username.length() < 3 || userDTO.username.length() > 20) {
            throw new Exception("Username must be between 3 and 20 characters long");
        } else if (userDTO.name.length() < 3 || userDTO.name.length() > 50) {
            throw new Exception("Name must be between 3 and 50 characters long");
        } else if (userDTO.surname.length() < 3 || userDTO.surname.length() > 50) {
            throw new Exception("Surname must be between 3 and 50 characters long");
        }

        if (findByUsername(userDTO.username) != null) {
            throw new Exception("Username is already taken.");
        }

        if (findByEmail(userDTO.email) != null) {
            throw new Exception("Email is already in use.");
        }

        User user = new User(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.password));
        user.setRole(Role.EDITOR);

        userRepository.save(user);

        return true;
    }

    private void trimUserDataStrings(UserRegisterDTO user) {
        user.username = user.username.trim();
        user.email = user.email.trim();
        user.name = user.name.trim();
        user.surname = user.surname.trim();
        user.password = user.password.trim();
    }
}
