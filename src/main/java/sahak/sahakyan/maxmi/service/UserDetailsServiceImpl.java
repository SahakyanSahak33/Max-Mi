package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sahak.sahakyan.maxmi.dao.UserRepository;
import sahak.sahakyan.maxmi.dto.ShopmeUserDetails;
import sahak.sahakyan.maxmi.entity.Authority;
import sahak.sahakyan.maxmi.entity.User;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with that email");
        }
        return new ShopmeUserDetails(user);
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<Authority> userAuthoritys = user.getAuthorities();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userAuthoritys.size());
        for(Authority userAuthority : userAuthoritys){
            authorities.add(new SimpleGrantedAuthority(userAuthority.getName().toUpperCase()));
        }
        return authorities;
    }
}
