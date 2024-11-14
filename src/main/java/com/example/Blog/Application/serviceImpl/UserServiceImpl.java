package com.example.Blog.Application.serviceImpl;

import com.example.Blog.Application.model.Users;
import com.example.Blog.Application.model.security.RoleEntity;
import com.example.Blog.Application.repository.security.RoleRepository;
import com.example.Blog.Application.repository.UserRepository;
import com.example.Blog.Application.request.UserRequest;
import com.example.Blog.Application.response.UserResponse;
import com.example.Blog.Application.response.list.UserResponseList;
import com.example.Blog.Application.response.util.MessageResponse;
import com.example.Blog.Application.service.UserService;
import com.example.Blog.Application.utility.enums.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createUser(UserRequest userRequest, String role) {
        Users user = new Users();
        user.setUsername(userRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        RoleEntity authority = roleRepository.findByName(Role.valueOf("ROLE_" + role.toUpperCase())).orElseThrow(()-> new RuntimeException("Error:Role not found"));
        user.setRole(authority);
        userRepository.save(user);
    }

    @Override
    public UserResponse GetUserByID(Long userId) {
        return new UserResponse(userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found")));
    }

    @Override
    public void updateUser(Long userId, UserRequest request) {
        Users user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
        userRepository.delete(user);
    }

    @Override
    public UserResponseList getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageWithSort = PageRequest.of(pageNumber,pageSize, sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Page<Users> usersPage = userRepository.findAll(pageWithSort);
        List<UserResponse> userResponses = usersPage.stream().map(UserResponse::new).collect(Collectors.toList());
        return new UserResponseList(pageNumber,pageSize,sortBy,sortDir,usersPage.getTotalPages(),userResponses);
    }

    @Override
    public String getUserRoleByEmail(String username) {
        Users users =  userRepository.findByEmailIgnoreCase(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with email: " + username); // Assuming 'role' is a field in the Users entity
        } return users.getRole().getName().toString();
    }

    @PostConstruct
    public ResponseEntity<MessageResponse> saveRoles() {
        try {
            if ( roleRepository.findAll ( ).isEmpty ( ) ) {
                List<RoleEntity> roles = new ArrayList<>( );
                roles.add ( new RoleEntity ( Role.ROLE_USER ) );
                roles.add ( new RoleEntity ( Role.ROLE_ADMIN ) );
                roleRepository.saveAll ( roles );
            }
        }
        catch ( Exception e ) {

            return new ResponseEntity<> ( new MessageResponse ( "Error occurred" ), HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity<> ( new MessageResponse ( "Success" ), HttpStatus.OK );

    }

}
