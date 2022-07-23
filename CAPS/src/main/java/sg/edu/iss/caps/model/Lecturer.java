package sg.edu.iss.caps.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Lecturer extends User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lecturerId;
	
	@ManyToMany(mappedBy="courseLecturers", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Course> teachCourses;
	
	public Lecturer(String username,  byte[] passwordHash, String firstName, String lastName, String email,  Role role) {
		super(username, firstName, lastName, email, passwordHash, role);
	}


	
}
