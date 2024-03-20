package in.nareshit.prasad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nareshit.prasad.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByUsername(String username);
}
