package ru.lenivtsev.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lenivtsev.market.api.dto.UserDto;
import ru.lenivtsev.market.auth.model.Role;
import ru.lenivtsev.market.auth.model.User;
import ru.lenivtsev.market.auth.model.mapers.UserDtoMapper;
import ru.lenivtsev.market.auth.model.mapers.UserEncoderDtoMapper;
import ru.lenivtsev.market.auth.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

/*

    public Page<UserDto> findAllByFilter(String usernameFilter, String emailFilter, int page, int size, String sortField) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";
        return userRepository.usersByFilter(usernameFilter, emailFilter, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<UserDto> findUserDtoById(Long id) {
        return userRepository.findById(id).map(mapper::map);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
*/

    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

//    public void deleteUserById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public Optional<UserDto> findUser(String name) {
//        return userRepository.findByUserName(name).map(mapper::map);
//    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /* old realization 
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException(username));

    }*/

}
