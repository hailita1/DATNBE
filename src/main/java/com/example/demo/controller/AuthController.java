package com.example.demo.controller;

import com.example.demo.model.Bill;
import com.example.demo.model.Category;
import com.example.demo.model.auth.JwtResponse;
import com.example.demo.model.auth.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getTelephoneNumber(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (user.getAvt() == null) {
            user.setAvt("https://firebasestorage.googleapis.com/v0/b/demoupload-d290c.appspot.com/o/avatar.jpg?alt=media&token=9ac8b329-207a-4c5b-9581-98d5269b160d");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/registerGoogle")
    public ResponseEntity<User> registerGoogle(@RequestBody User user) {
        int check = 0;
        User user1 = new User();
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                user1 = currentUser;
                check++;
            }
        }
        if (check == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }
    }


    @GetMapping("/accounts")
    public ResponseEntity<Iterable<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/accounts")
    public ResponseEntity<User> getUser(@RequestBody Long user) {
        Optional<User> userOptional = userService.findById(user);
        return userOptional.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/accounts")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userOptional = userService.findById(user.getId());
        return userOptional.map(user1 -> {
            user1.setId(user1.getId());
            user1.setEmail(user.getEmail());
            user1.setTelephoneNumber(user.getTelephoneNumber());
            user1.setFullName(user.getFullName());
            user1.setAvt(user.getAvt());
            userService.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user) {
        Optional<User> userOptional = userService.findById(user.getId());
        return userOptional.map(user1 -> {
            user1.setId(user1.getId());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/resetpassword")
    public ResponseEntity<User> resetPassword(@RequestBody User user) {
        String password = "Abcxyz123";
        User user1 = userService.findByEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(password));
        userService.save(user1);
        emailService.sendEmail(user.getEmail(), "Lấy lại mật khẩu thành công", "\nMật khẩu mới của bạn là: " + password + "\nXin cám ơn bạn đã sử dụng dịch vụ của chúng tôi !");
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }


    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> {
            userService.remove(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
