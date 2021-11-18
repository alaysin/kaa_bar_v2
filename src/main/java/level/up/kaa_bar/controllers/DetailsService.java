package level.up.kaa_bar.controllers;

import level.up.kaa_bar.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public DetailsService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.encoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN", "USER")
                    .build();
        }
        level.up.kaa_bar.model.User found = userRepo.findUserByLogin(username);

        if (found == null) {
            throw  new UsernameNotFoundException("User "+ username + "not found!");
        }

        return User.withUsername(username)
                .password(found.getPassword())
                .roles("USER")
                .build();
    }

}
