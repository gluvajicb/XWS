package xmlproject.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xmlproject.be.dto.UserDTO;
import xmlproject.be.dto.UserLoginDTO;
import xmlproject.be.dto.UserRegisterDTO;
import xmlproject.be.security.UserTokenState;
import xmlproject.be.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin()
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/test")
    public ResponseEntity<UserLoginDTO> test(@RequestBody  UserLoginDTO dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto) {
        try {
            UserTokenState tokenState = userService.loginUser(dto);
            return new ResponseEntity<>(tokenState, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        try {
            return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.findByIdXml(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/byUsername/{username}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userService.findByUsernameXml(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/byEmail/{email}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.findByEmailXml(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser() {
        try {
            List<String> users = userService.findAllXml();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/reviewers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReviewers() {
        try {
            List<UserDTO> users = userService.findAllReviewers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
