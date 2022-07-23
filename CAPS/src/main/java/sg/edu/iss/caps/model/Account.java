package sg.edu.iss.caps.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
	private String domain;
	private String username;
	private String password;
}
