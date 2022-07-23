package sg.edu.iss.caps.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {
	@Id
	private String courseCode;
	private String courseTitle;
	private String courseDescription;
	private String courseCredits;
	private Integer courseCapacity;
	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="CourseLecturer")
	private List<Lecturer> courseLecturers;
	
	@JsonIgnore
	@OneToMany(mappedBy="course")
	private List<CourseStudent> courseStudents;

	public Course(String courseCode, String courseTitle, String courseDescription, String courseCredits, Integer courseCapacity,
			CourseStatus courseStatus) {
		super();
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.courseCredits = courseCredits;
		this.courseCapacity = courseCapacity;
		this.courseStatus = courseStatus;
	}
	
}
