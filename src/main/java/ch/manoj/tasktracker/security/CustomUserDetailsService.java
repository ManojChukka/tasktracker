package ch.manoj.tasktracker.security;

import ch.manoj.tasktracker.entity.Users;
import ch.manoj.tasktracker.exception.UserNotFoundException;
import ch.manoj.tasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users users = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("User with email: %s is not found", email))
        );

        Set<String> authorizedRoles = new HashSet<>();
        authorizedRoles.add("ROLE_ADMIN");

        return new User(users.getEmail(),users.getPassword(),userAuthorities(authorizedRoles));
    }

    private Collection<? extends GrantedAuthority> userAuthorities(Set<String> authorizedRoles){

        return authorizedRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
