package sahak.sahakyan.maxmi.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahak.sahakyan.maxmi.entity.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByName(String name);
    @NotNull Optional<Authority> findById(@NotNull Long id);
}
