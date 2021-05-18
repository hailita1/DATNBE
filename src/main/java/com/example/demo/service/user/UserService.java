package com.example.demo.service.user;

import com.example.demo.model.auth.Role;
import com.example.demo.model.auth.User;
import com.example.demo.model.auth.UserPrinciple;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.example.demo.model.auth.RoleName.ROLE_USER;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (user.getRoles() == null) {
            Role role = roleService.findByName(ROLE_USER.toString());
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAvt() == null) {
            user.setAvt("https://firebasestorage.googleapis.com/v0/b/demoupload-d290c.appspot.com/o/img%2F1620963043293?alt=media&token=921648bd-6ad5-4b26-93a1-05a3da221bbc");
        }
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(username));
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
