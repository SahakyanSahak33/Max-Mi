//package sahak.sahakyan.maxmi.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import sahak.sahakyan.maxmi.dao.UserRepository;
//import sahak.sahakyan.maxmi.entity.User;
//import javax.transaction.Transactional;
//import java.util.Collection;
//
//@RequiredArgsConstructor
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository repository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user with that email");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                getAuthorities(user));
//    }
//
//    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getAuthorities().stream().map((role) -> role.getName()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }
//}
