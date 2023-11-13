package Project.TravelBusan.repository;

import Project.TravelBusan.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    Optional<User> findByUsername(String username);

    default User findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 아이디 입니다."));
    }

    default User findByUsernameOrElseThrow(String username){
        return findByUsername(username).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 아이디 입니다."));
    }
}
