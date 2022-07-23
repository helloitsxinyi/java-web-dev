package sg.edu.iss.caps.model;


import javax.persistence.Column;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public class User {

	@NotEmpty
	@JsonIgnore
	private String username;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@JsonIgnore
	private String email;
	//private String password;
	@Column(columnDefinition="BINARY(32) NOT NULL")
	@JsonIgnore
	private byte[] passwordHash;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private UserStatus userStatus;
	
	public User(String username, String firstName, String lastName, String email, byte[] passwordHash, Role role) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
	}
}
