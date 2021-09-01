package com.cts.zuul.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.zuul.beans.MyUserDetails;
import com.cts.zuul.beans.User;
import com.cts.zuul.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new MyUserDetails(new User("user", "{noop}pass", authorities));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}*/
	
	@Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username).get();
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
}