package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.entity.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);

    void save(User user);

    boolean checkUser(User user);
}
