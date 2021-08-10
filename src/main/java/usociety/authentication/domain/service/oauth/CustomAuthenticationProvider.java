package usociety.authentication.domain.service.oauth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import usociety.authentication.domain.model.User;
import usociety.authentication.domain.repository.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordManager passwordManager;

    @Autowired
    public CustomAuthenticationProvider(@Lazy PasswordManager passwordManager,
                                        UserRepository userRepository) {
        this.passwordManager = passwordManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("Authentication failed");
        }

        User user = passwordManager.checkPassword(optionalUser.get(), password);
        return new UsernamePasswordAuthenticationToken(
                userName,
                password,
                getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        return user.getRole()
                .getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
