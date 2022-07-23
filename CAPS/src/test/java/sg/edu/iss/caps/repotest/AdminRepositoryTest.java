package sg.edu.iss.caps.repotest;

import java.util.Arrays;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.repo.AdministratorRepository;
import sg.edu.iss.caps.util.HashUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AdminRepositoryTest {

	private static final Logger LOGGER = Logger.getLogger(CapsApplication.class.getName());
	@Autowired
	AdministratorRepository arepo;
	
	@Test
	@Order(1)
	public void testFindAdmin() {
		Administrator a = arepo.findFirstByUsername("capslbj");
		
		Assertions.assertTrue(Arrays.equals(HashUtil.getHash("capslbj","password-4"), a.getPasswordHash()));
		
	}
	
}
