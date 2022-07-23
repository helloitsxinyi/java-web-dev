package sg.edu.iss.caps.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ChangePWRequest {
	@Id
	@Type(type="uuid-char")
	private UUID id;
	
	private long expiredTime;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private Lecturer lecturer;

	public ChangePWRequest(UUID id, long expiredTime, Student student) {
		super();
		this.id = id;
		this.expiredTime = expiredTime;
		this.student = student;
	}

	public ChangePWRequest(UUID id, long expiredTime, Lecturer lecturer) {
		super();
		this.id = id;
		this.expiredTime = expiredTime;
		this.lecturer = lecturer;
	}
	
	
}
