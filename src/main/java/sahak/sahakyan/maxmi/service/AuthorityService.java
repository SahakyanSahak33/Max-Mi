package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.entity.Authority;

public interface AuthorityService {
    void saveAuthority(Authority authority);
    Authority findByName(String name);
    Authority findById(Long id);
}
