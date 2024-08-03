package sn.xarandev.thymeleaf_springboot.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sn.xarandev.thymeleaf_springboot.dao.IUserDao;
import sn.xarandev.thymeleaf_springboot.entities.UserEntity;
import sn.xarandev.thymeleaf_springboot.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserDao repository;

    public UserDetailsServiceImpl(IUserDao repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserEntity user = repository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(String.format("User does not exist, email: %s", email)));
        //Les roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}