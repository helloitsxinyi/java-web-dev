package sg.edu.iss.caps.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Student extends User{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentId;
	//@DateTimeFormat(pattern = "dd-mm-yyyy")
	//private LocalDateTime enrolledDate;
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	private Date enrolledDate;

	@OneToMany(mappedBy="student",cascade=CascadeType.ALL)
	private List<CourseStudent> courseAttended;

	public Student(String username, byte[] passwordHash, String firstName, String lastName, String email, Role role,
			Date enrolledDate) {
		super(username, firstName, lastName, email, passwordHash, role);
		this.enrolledDate = enrolledDate;
	}
}
