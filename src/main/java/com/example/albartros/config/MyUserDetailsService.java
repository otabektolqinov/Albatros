package com.example.albartros.config;

import com.example.albartros.model.AuthUser;
import com.example.albartros.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optional = userRepository.findByUsernameAndDeletedAtIsNull(username);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException("USER 404");
        }

        return new UserPrincipal(optional.get());
    }
}
