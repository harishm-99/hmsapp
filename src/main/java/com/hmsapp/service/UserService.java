package com.hmsapp.service;

import com.hmsapp.entity.User;
import com.hmsapp.exception.ResourceAlreadyExists;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.ProfileDTO;
import com.hmsapp.payload.UserDto;
import com.hmsapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private JWTService jwtService;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    //used for user signup
    public UserDto createUser(UserDto userDto){
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if(opUsername.isPresent()){
            throw new ResourceAlreadyExists("Username already exists.");
        }

        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()) {
            throw new ResourceAlreadyExists("Email already exists.");
        }

        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if(opMobile.isPresent()) {
             throw new ResourceAlreadyExists("Mobile no. already exists.");
        }

        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(10)));
        userDto.setRole("ROLE_USER");
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        savedUser.setPassword(null);
        return mapToDto(savedUser);
    }

    //used for user signup
    public UserDto createPropertyOwner(UserDto userDto){
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if(opUsername.isPresent()){
            throw new ResourceAlreadyExists("Username already exists.");
        }

        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()) {
            throw new ResourceAlreadyExists("Email already exists.");
        }

        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if(opMobile.isPresent()) {
             throw new ResourceAlreadyExists("Mobile no. already exists.");
        }

        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(10)));
        userDto.setRole("ROLE_OWNER");
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        savedUser.setPassword(null);
        return mapToDto(savedUser);
    }

    //used for user signup
    public UserDto createBlogManager(UserDto userDto){
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if(opUsername.isPresent()){
            throw new ResourceAlreadyExists("Username already exists.");
        }

        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()) {
            throw new ResourceAlreadyExists("Email already exists.");
        }

        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if(opMobile.isPresent()) {
            throw new ResourceAlreadyExists("Mobile no. already exists.");
        }

        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(10)));
        userDto.setRole("ROLE_BLOGMANAGER");
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        savedUser.setPassword(null);
        return mapToDto(savedUser);
    }

    public String verifyLogin(
            LoginDto loginDto
    ){
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());

        if(opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                String token = jwtService.generateToken(user.getUsername());
                return token;
            }else{
                return null;
            }
        }
        return null;
    }

    public ProfileDTO getUserProfile(User user){
        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setUsername(user.getUsername());
        profileDTO.setName(user.getName());
        profileDTO.setEmail(user.getEmail());

        return profileDTO;
    }
}
