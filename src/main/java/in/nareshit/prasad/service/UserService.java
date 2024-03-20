package in.nareshit.prasad.service;

import java.util.Optional;

import in.nareshit.prasad.model.User;

public interface UserService
{

	Integer saveUser(User user);
	
	Optional<User> findByUsername(String username);
	
}
