package sg.edu.iss.caps.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CourseStudent {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer courseStudentId;
//	@Min(value = 0, message = "Score should not be less than 0")
//    @Max(value = 100, message = "Score should not be greater than 100")
	private Integer score;
	@Enumerated(EnumType.STRING)
	private Grade grade;
	@Enumerated(EnumType.STRING)
	private CourseStudentStatus courseStudentStatus;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Course course;
	
	public CourseStudent(Integer score, CourseStudentStatus courseStudentStatus, Student student, Course course) {
		super();
		this.score = score;
		this.courseStudentStatus = courseStudentStatus;
		this.student = student;
		this.course = course;
	}

	public CourseStudent(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
		this.courseStudentStatus = CourseStudentStatus.ENROLLED;
	}
	
	
	
	
}
