package ru.lenivtsev.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lenivtsev.market.api.dto.AppError;
import ru.lenivtsev.market.api.dto.JwtRequest;
import ru.lenivtsev.market.api.dto.JwtResponse;
import ru.lenivtsev.market.api.dto.UserDto;
import ru.lenivtsev.market.auth.model.RoleMapper;
import ru.lenivtsev.market.auth.model.User;
import ru.lenivtsev.market.auth.model.mapers.RoleDtoMapper;
import ru.lenivtsev.market.auth.services.UserService;
import ru.lenivtsev.market.auth.utils.JwtTokenUtil;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleMapper roleDtoMapper;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
       } catch (BadCredentialsException e){
           return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
       }
       UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
       String token = jwtTokenUtil.generateToken(userDetails);
       return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/registration")
    public ResponseEntity<?> createAuthToken(@RequestBody UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUserName(userDto.getUserName()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles()
                .stream()
                .map(roleDtoMapper::map)
                .collect(Collectors.toList()));
        userService.save(user);
        //userService.save(userDto);

        UserDetails userDetails = userService.loadUserByUsername(userDto.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
