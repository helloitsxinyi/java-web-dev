package sg.edu.iss.caps.util;

import org.springframework.ui.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.caps.model.User;

@Data
@NoArgsConstructor
public class MenuNavBarUtil {
	
	private static final String navbarVarName = "NavMenu";
	private static final String reactlink = "http://localhost:3000";
	private static final String reactVarName = "ReactLink";
	
	private User user;
	private String role;
	private String name;
	
	public static void generateNavBar(User user, Model model) {
		if(user == null) {
			return;
		}
		MenuNavBarUtil navbar = new MenuNavBarUtil();
		navbar.setUser(user);
		navbar.setRole(user.getRole().name());
		navbar.setName(user.getFirstName() + " " + user.getLastName());
		model.addAttribute(navbarVarName, navbar);
		model.addAttribute(reactVarName, reactlink);
	}
	
}
