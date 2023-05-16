//package sahak.sahakyan.maxmi.dto;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import sahak.sahakyan.maxmi.entity.Authority;
//import sahak.sahakyan.maxmi.entity.User;
//
///**
// * @author Ramesh Fadatare
// *
// */
//public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
//{
//
//    private static final long serialVersionUID = 1L;
//    private User user;
//
//    public AuthenticatedUser(User user)
//    {
//        super(user.getEmail(), user.getPassword(), getAuthorities(user));
//        this.user = user;
//    }
//
//    public User getUser()
//    {
//        return user;
//    }
//
//    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
//    {
//        Set<String> AuthorityAndPermissions = new HashSet<>();
//        Set<Authority> authorities = user.getAuthorities();
//
//        for (Authority Authority : authorities)
//        {
//            AuthorityAndPermissions.add(Authority.getName());
//        }
//        String[] AuthorityNames = new String[AuthorityAndPermissions.size()];
//        Collection<GrantedAuthority> authorities1 = AuthorityUtils.createAuthorityList(AuthorityAndPermissions.toArray(AuthorityNames));
//        return authorities1;
//    }
//}
