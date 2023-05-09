package sahak.sahakyan.maxmi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.UserRepository;
import sahak.sahakyan.maxmi.entity.User;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;



    @Override
    @Transactional
    public User findByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkUser(User user) {
        return !(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPhoneNumber(user.getPhoneNumber()));
    }
}
