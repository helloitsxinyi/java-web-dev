package sg.edu.iss.caps.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Administrator extends User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminId;

	public Administrator(String username,  byte[] passwordHash, String firstName, String lastName, String email,
			Role role) {
		super(username, firstName, lastName, email, passwordHash, role);
	}

	

	
	
}
