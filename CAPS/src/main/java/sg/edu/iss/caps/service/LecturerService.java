package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerService {
	
	public void saveLecturer(Lecturer l); 
	public void saveEditLecturer(Lecturer l); 
	public List<Lecturer> findAllActiveLecturers(); 
	public void deleteLecturer(Lecturer l);
	public List<Lecturer> findAllLecturers();
	public Lecturer findLecturerById(Integer lectId);
	public boolean checkIfEmailExist(Lecturer l);
	public boolean checkIfUsernameExist(Lecturer l);
	Page<Lecturer> findPaginated(int pageNo, int pageSize);
//	public Lecturer findLecturerById(Integer lecturerId);
//	public List<Lecturer> findAllLecturers();
}
