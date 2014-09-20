package test.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAccount {
	
	private static Logger log = LoggerFactory.getLogger(GetAccount.class);
		
	public static String[] getAccount() throws Exception{
		InputStream input = null;
		String[] account = null;
		try {
			input = new FileInputStream("config/account.ini");
			Properties props = new Properties();
			props.load(input);
			if (props.containsKey("username")
					&& props.containsKey("password")
					) {
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				if (username != null
						&& !username.equals("")
						&& password != null
						&& !password.equals("")) {
					account = new String[2];
					account[0]=username;
					account[1]=password;
					log.info("get account success");
				}
			}
			
		} finally{
			if (input != null) {
				input.close();
			}
		}
		return account;
	}

}
