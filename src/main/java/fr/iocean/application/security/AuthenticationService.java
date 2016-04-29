package fr.iocean.application.security;

import fr.iocean.application.credential.model.Credential;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> option = userService.findOneByLogin(username);

            if (option.isPresent()) {
                User user = option.get();
                List<GrantedAuthority> rules = this.getUserCredentials(user);
                return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
        }

        throw new UsernameNotFoundException("username.not.found");
    }

	private List<GrantedAuthority> getUserCredentials(User user) {
		List<GrantedAuthority> rules = new ArrayList<>();
		
		List<Credential> credentials = user.getCredentials();
		for (Credential credential : credentials) {
			rules.add(new SimpleGrantedAuthority(credential.getValue()));
		}
		
		return rules;
	}
}
