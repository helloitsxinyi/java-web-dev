package sg.edu.iss.caps.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.repo.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	
	@Override
	@Transactional(readOnly = true) 
	public List<Course> findAvailableCourseForStudent(Student s) {
		// Find the courses that the student have not taken
		List<Course> courseNotTaken = courseRepo.findCourseNotTaken(s);
		// Filter out the full courses
		List<Course> courseAvailable = getCourseAvailable(courseNotTaken);
		return courseAvailable;
	}

	@Override
	public List<Course> findSearchCourseForStudent(Student s, String searchStr) {
		// Find the courses that the student have not taken that meets the search string
		List<Course> courseNotTaken = courseRepo.findCourseNotTakenSearch(s, searchStr);
		// Filter out the full courses
		List<Course> courseAvailable = getCourseAvailable(courseNotTaken);
		return courseAvailable;
	}
	

	@Override
	public List<Course> findAllCourses() {
		// Find all courses
		return courseRepo.findAll();
	}

	@Override
	public Course findCourseById(String courseId) {
		// Find course By Id
		return courseRepo.findById(courseId).get();
	}

	@Override
	public List<Lecturer> findLecturersByCourseId(String courseId) {
		// Find all lecturers assigned to course
		return courseRepo.findCourseLecturers(courseId);
	}

	@Override
	public void SaveCourse(Course c) {
		// To verify course and save
		SaveCourseAddLecturer(c,null);
	}

	@Override
	public void SaveCourseAddLecturer(Course c, Lecturer l) {
		// To verify course and save

		// Find that the course exist in repo. If not, create new course
		Course c2 = courseRepo.findById(c.getCourseCode()).isPresent() ? courseRepo.findById(c.getCourseCode()).get()
				: new Course();

		c2.setCourseCode(c.getCourseCode());
		c2.setCourseTitle(c.getCourseTitle());
		c2.setCourseDescription(c.getCourseDescription());
		c2.setCourseCredits(c.getCourseCredits());
		c2.setCourseCapacity(c.getCourseCapacity());
		c2.setCourseStatus(c.getCourseStatus());
		if (l != null) {
			c2.getCourseLecturers().add(l);
		}
		courseRepo.save(c2);
	}

	@Override
	public void AddLecturerToCourse(String courseId, Lecturer l) {
		//Find course and add lecturer
		Course c = courseRepo.findById(courseId).get();
		if(c.getCourseLecturers().contains(l)) {
			return;
		}
		c.getCourseLecturers().add(l);
        courseRepo.save(c);
	}

	// Other private methods
	private List<Course> getCourseAvailable(List<Course> courses) {
		// Filter away full courses and those that are closed
		return courses.stream().filter(
				c -> (c.getCourseStudents().size() < c.getCourseCapacity()) && c.getCourseStatus() == CourseStatus.OPEN)
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public Page<Course> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		return this.courseRepo.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Course> findAvailableCourseForStudentPage(Student s,int pageNo, int pageSize, String searchStr){
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		//Find the courses that the student have not taken
		List<Course> courseNotTaken = (searchStr == null)?
				courseRepo.findCourseNotTaken(s):
					courseRepo.findCourseNotTakenSearch(s, searchStr);
		
		
		// Filter out the full courses
		List<Course> courseAvailable = getCourseAvailable(courseNotTaken);
		
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > courseAvailable.size() ? courseAvailable.size()
				  : (start + pageable.getPageSize()));
		
		Page<Course> coursepage = new PageImpl<Course>(courseAvailable.subList(start, end), pageable, courseAvailable.size());
		
//		Page<Course> coursepage = (searchStr == null)?
//				courseRepo.findCourseNotTakenPage(s, pageable) :
//					courseRepo.findCourseNotTakenSearchPage(s,searchStr, pageable);
		
		return coursepage;
	}

	@Transactional
	@Override
	public Course findCourseByCourseCode(String courseCode) {
		return courseRepo.findCourseCode(courseCode);
	}

	@Override
	public void deleteCourse(String courseCode) {
		// To delete courses without students
		Course course = courseRepo.findCourseCode(courseCode);
		if(course == null) {
			return;
		}
		if(course.getCourseStudents().isEmpty()) {
			//Delete only if empty
			courseRepo.delete(course);
			
		}
		
	}


}
