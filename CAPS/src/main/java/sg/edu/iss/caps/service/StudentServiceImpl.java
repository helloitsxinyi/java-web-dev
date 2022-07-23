package sg.edu.iss.caps.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Role;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.UserStatus;
import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.util.HashUtil;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Resource
	StudentRepository studentRepo;

	@Transactional
	@Override
	public List<Student> findAllActiveStudents() {
		List<Student> studentList = studentRepo.findAllActiveStudents();
		return studentList;
	}
	
	@Transactional
	@Override
	public List<Student> findAllStudentsByName(String name) {
		List<Student> studentListByName = studentRepo.findStudentByFirstName(name);
		return studentListByName;
	}

	@Transactional
	@Override
	public Student findStudentById(Integer id) {
		Student studentById = studentRepo.findById(id).get();
		return studentById;
	}
	
	@Transactional
	@Override
	public void editStudent(Student s) {
		Student newStudent = studentRepo.findById(s.getStudentId()).get();
		newStudent.setFirstName(s.getFirstName());
		newStudent.setLastName(s.getLastName());
		newStudent.setUsername(s.getUsername());
		newStudent.setEmail(s.getEmail());
		studentRepo.save(newStudent);
	}

	@Transactional
	@Override
	public void createStudent(Student s) {
		//Setting Date 
		Date date = new Date();
	    String strDateFormat = "dd-MM-yyyy";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		try {
			s.setEnrolledDate(dateFormat.parse(formattedDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s.setRole(Role.STUDENT);
		s.setUserStatus(UserStatus.ACTIVE);
		String defaultPwd = "123456";
		s.setPasswordHash(HashUtil.getHash(s.getUsername(),defaultPwd));
		studentRepo.save(s);
	}

	@Transactional
	@Override
	public void deleteStudent(Integer id) {
		Student s = studentRepo.findById(id).get();
        if(s.getStudentId() != null)
        {
        	s.setUserStatus(UserStatus.INACTIVE);
        	studentRepo.save(s);
        }
	}

	@Transactional
	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Student> allActiveStudent = studentRepo.findAllActiveStudents();
		
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > allActiveStudent.size() ? allActiveStudent.size() : (start + pageable.getPageSize()));
		Page<Student> page = new PageImpl<Student>(allActiveStudent.subList(start, end), pageable, allActiveStudent.size());
		return page;
	}
	
	@Transactional
	@Override
	public Student changeStudentProfile(Student student) {
		Student s = studentRepo.findById(student.getStudentId()).get();
		s.setFirstName(student.getFirstName());
		s.setLastName(student.getLastName());
		s.setEmail(student.getEmail());
		return studentRepo.saveAndFlush(s);	
	}


}
