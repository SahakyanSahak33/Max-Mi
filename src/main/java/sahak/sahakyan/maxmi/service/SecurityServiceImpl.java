//package sahak.sahakyan.maxmi.service;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SecurityServiceImpl implements SecurityService{
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    public String findLoggedInUsername() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
//    }
//
//    @Override
//    public void autoLogin(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(authenticationToken);
//        if (authenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            logger.debug(String.format("successfully auto logged in." + username));
//        }
//    }
//}
