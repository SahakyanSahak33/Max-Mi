package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.AuthorityRepository;
import sahak.sahakyan.maxmi.entity.Authority;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    @Transactional
    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }
}
