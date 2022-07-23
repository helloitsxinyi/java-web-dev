package sg.edu.iss.caps.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.logging.Logger;

import sg.edu.iss.caps.CapsApplication;

public class HashUtil {
	
	private static final Logger LOGGER = Logger.getLogger(CapsApplication.class.getName());
	
	public static byte[] getHash(String username, String password) {
		byte[] hashedpw = null;
		String combiString = username + password;
		try {
			MessageDigest mg = MessageDigest.getInstance("SHA-256");
			hashedpw = mg.digest(combiString.getBytes(StandardCharsets.UTF_8));
		}
		catch(NoSuchAlgorithmException e) {
			LOGGER.error("No SHA-256 algorithm");
			LOGGER.error(e);
		}
		return hashedpw;
	}
	
	public static String convertByteToHex(byte[] hashedpw) {
	    StringBuilder hexDisplay = new StringBuilder(2 * hashedpw.length);
	    for (int i = 0; i < hashedpw.length; i++) {
	        String hex = Integer.toHexString(0xff & hashedpw[i]);
	        if(hex.length() == 1) {
	        	hexDisplay.append('0');
	        }
	        hexDisplay.append(hex);
	    }
	    return hexDisplay.toString();
	}
}
