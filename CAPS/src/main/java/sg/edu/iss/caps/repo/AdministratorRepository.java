package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	Administrator findFirstByUsername(String username);
}
