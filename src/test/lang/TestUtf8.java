package test.lang;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

/**
 * 读取含有中文的ISO-8859-1编码的配置文件
 * @author Administrator
 */
public class TestUtf8 implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(TestUtf8.class);

	@Test
	@Override
	public void test() {
	}
	
	public static void main(String[] args) {
		try {
			getAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
					account[0]=new String(username.getBytes("ISO-8859-1"),"gbk");
					account[1]=new String(password.getBytes("ISO-8859-1"),"gbk");
					System.err.println(account[0]+"@"+account[1]);
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
