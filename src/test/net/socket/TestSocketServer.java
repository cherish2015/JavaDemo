package test.net.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TestSocketServer implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(TestSocketServer.class);

	@Test
	@Override
	public void test() {
		testSocketObject();
	}
	
	public void testSocketObject(){
		log.info("testSocketObject");
		ServerSocket server = null;
		try {
			server = new ServerSocket();
			server.setReuseAddress(true);
			server.bind(new InetSocketAddress("localhost", 10086));
			log.info("socket server start,listenning port 10086");
			while(true){
				Socket client = server.accept();
				invoke(client);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private static void invoke(final Socket client){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ObjectInputStream is = null;
				ObjectOutputStream os = null;
				try {
					is = new ObjectInputStream(client.getInputStream());
					os = new ObjectOutputStream(client.getOutputStream());
					
					Object obj = is.readObject();
					User user = (User)obj;
					System.err.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
					
					user.setUsername(user.getUsername()+"_new");
					user.setPassword(user.getPassword()+"_new");
					
					os.writeObject(user);
					os.flush();
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
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}
				}
			}
		}).start();
	}

}



