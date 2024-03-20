package in.nareshit.prasad.model;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="usertable")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String username;
	private String password;
	
	@ElementCollection
	@CollectionTable(
			name="rolestable",
	        joinColumns=@JoinColumn(name="id"))
	private Set<String> roles;
	
}
