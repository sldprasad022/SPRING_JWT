package in.nareshit.prasad.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nareshit.prasad.model.User;
import in.nareshit.prasad.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService
{
	@Autowired
	private UserRepository userRepository; //HAS-A RelationShip

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Integer saveUser(User user)
	{
		//TODO : Encode Password 
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		 
		
		return userRepository.save(user).getId();
	}
	
	//getByUsername
	public Optional<User> findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	 
	//-----------------------------------------//

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User>  opt = findByUsername(username);
		if (opt.isEmpty()) 
		
			throw new UsernameNotFoundException("User not Exist");
		
			//read user(From DB)
			User user = opt.get();
			
			return new org.springframework.security.core.userdetails.User(
					username,
					user.getPassword(),
					user.getRoles().stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
					);
		}
	}

}
