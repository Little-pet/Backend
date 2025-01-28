package LittlePet.UMC.User.repository;

import LittlePet.UMC.domain.userEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySocialId(String SocialId);
    boolean existsByNameAndIdNot(String name, Long id);

}
