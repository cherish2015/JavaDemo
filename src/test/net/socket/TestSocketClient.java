package test.net.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TestSocketClient implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(TestSocketClient.class);

	@Test
	@Override
	public void test() {
		for (int i = 0; i < 100; i++) {
			testSocketClient();
		}
	}
	
	private void testSocketClient(){
		Socket client = null;
		ObjectInputStream is = null;
		ObjectOutputStream os = null;
		try {
			client = new Socket("localhost", 10086);
			os = new ObjectOutputStream(client.getOutputStream());
			User user = new User();
			user.setUsername("username");
			user.setPassword("password");
			
			os.writeObject(user);
			os.flush();
			
			is = new ObjectInputStream(client.getInputStream());
			Object obj = is.readObject();
			if (obj != null) {
				user = (User)obj;
				System.err.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally{
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

}
