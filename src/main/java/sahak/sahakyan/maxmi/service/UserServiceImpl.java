package sahak.sahakyan.maxmi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.AuthorityRepository;
import sahak.sahakyan.maxmi.dao.UserRepository;
import sahak.sahakyan.maxmi.dto.DashboardDTO;
import sahak.sahakyan.maxmi.dto.UserDTO;
import sahak.sahakyan.maxmi.entity.Authority;
import sahak.sahakyan.maxmi.entity.User;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final DateService dateService;

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
    public void saveUser(User user) {
        System.out.println("saveUser Method");
        user.setDate(dateService.askDate());
        user.setEnabled(true);
        List<Authority> list = new ArrayList<>();
        Authority authority = authorityRepository.findById(1L).orElse(null);
        list.add(authority);
        user.setAuthorities(list);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean checkUser(User user) {
        return !(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPhoneNumber(user.getPhoneNumber()));
    }

    @Override
    public User changeUserFromUserDTO(DashboardDTO dashboardDTO, User oldUser) {
        User user = new User();
        user.setFirstName(dashboardDTO.getFirstName());
        user.setLastName(dashboardDTO.getLastName());
        user.setEmail(dashboardDTO.getEmail());
        user.setPhoneNumber(dashboardDTO.getPhoneNumber());
        user.setUsername(dashboardDTO.getUsername());
        user.setDate(oldUser.getDate());
        user.setId(oldUser.getId());
        user.setAuthorities(oldUser.getAuthorities());
        user.setGender(oldUser.getGender());
        user.setEnabled(oldUser.isEnabled());
        user.setPassword(oldUser.getPassword());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthority(user.getAuthorities())
        );
    }

    private Set<SimpleGrantedAuthority> getAuthority(List<Authority> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Authority role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
