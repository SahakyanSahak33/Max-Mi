package sahak.sahakyan.maxmi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.AuthorityRepository;
import sahak.sahakyan.maxmi.dao.UserRepository;
import sahak.sahakyan.maxmi.entity.Authority;
import sahak.sahakyan.maxmi.entity.User;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        user.setDate(currentTime);
        user.setEnabled(true);
        Set<Authority> hashSet = new HashSet<>();
        Authority authority = authorityRepository.findById(1L).orElse(null);
        hashSet.add(authority);
        user.setAuthorities(hashSet);
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean checkUser(User user) {
        return !(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPhoneNumber(user.getPhoneNumber()));
    }
}
