package sg.edu.iss.caps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Role;
import sg.edu.iss.caps.model.UserStatus;
import sg.edu.iss.caps.repo.LecturerRepository;
import sg.edu.iss.caps.util.HashUtil;

@Service
public class LecturerServiceImpl implements LecturerService {
	
	@Autowired
	LecturerRepository lecturerRepo;

	@Transactional
	@Override
	public List<Lecturer> findAllLecturers() {
		//Find all lecturers
		return lecturerRepo.findAll();
	}

	@Transactional
	@Override
	public Lecturer findLecturerById(Integer lectId) {
		// Find lecturer by Id
		if(lectId == null) {
			return null;
		}
		return lecturerRepo.findById(lectId).get();
	}
	
	@Transactional
	@Override
	public void saveLecturer(Lecturer l) {
		l.setRole(Role.LECTURER);
		l.setUserStatus(UserStatus.ACTIVE);
		String defaultPwd = "123456";
		l.setPasswordHash(HashUtil.getHash(l.getUsername(),defaultPwd));
		lecturerRepo.save(l);
	}
	
	@Transactional
	@Override
	public void saveEditLecturer(Lecturer l) {
		Lecturer l2 = lecturerRepo.findById(l.getLecturerId() ).get();
		l2.setFirstName(l.getFirstName());
		l2.setLastName(l.getLastName());
		l2.setUsername( l.getUsername());
		l2.setEmail(l.getEmail());
		lecturerRepo.save(l2);
	}
	
	@Transactional
	@Override
	public List<Lecturer> findAllActiveLecturers() {
		List<Lecturer> lecturerList = lecturerRepo.findAllActiveLecturers();
		return lecturerList;
	}

	@Transactional
	@Override
	public void deleteLecturer(Lecturer l) {
		if (l.getLecturerId() != null) {
			l.setUserStatus(UserStatus.INACTIVE);
			lecturerRepo.save(l);
		}
	}

	@Transactional
	@Override
	public boolean checkIfEmailExist(Lecturer l) {
		if(lecturerRepo.findFirstByEmail(l.getEmail()) != null) {
			return true;
		} return false;
	}
	
	@Transactional
	@Override
	public boolean checkIfUsernameExist(Lecturer l) {
		if(lecturerRepo.findFirstByUsername(l.getUsername()) != null) {
			return true;
		} return false;
	}

	@Override
	public Page<Lecturer> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Lecturer> allActiveStudent = lecturerRepo.findAllActiveLecturers();
		
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > allActiveStudent.size() ? allActiveStudent.size() : (start + pageable.getPageSize()));
		Page<Lecturer> page = new PageImpl<Lecturer>(allActiveStudent.subList(start, end), pageable, allActiveStudent.size());
		return page;
	}
	
//	@Transactional
//	@Override
//	public Lecturer findLecturerById(Integer lecturerId) {
//		return lecturerRepo.findById(lecturerId).get();
//	}
	
//	@Transactional
//	@Override
//	public List<Lecturer> findAllLecturers(){
//		List<Lecturer> lecturerList = lecturerRepo.findAll();
//		return lecturerList;
//	}
	
}
