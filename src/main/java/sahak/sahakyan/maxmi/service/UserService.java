package sahak.sahakyan.maxmi.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sahak.sahakyan.maxmi.entity.User;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User findByUsername(String username);

    void saveUser(User user);
    void save(User user);

    User findById(Long id);

    boolean checkUser(User user);
}
