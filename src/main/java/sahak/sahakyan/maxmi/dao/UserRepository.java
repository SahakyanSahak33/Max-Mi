package sahak.sahakyan.maxmi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahak.sahakyan.maxmi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phone);
}
