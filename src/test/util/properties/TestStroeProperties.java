package test.util.properties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import test.ITest;

public class TestStroeProperties implements ITest {

	@Override
	public void test() {

		String file = "config/test.properties";
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(file));
			Properties props = new Properties();
			props.put("now", System.currentTimeMillis()+"");
			props.store(fw, "");
			System.err.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new TestStroeProperties().test();
	}

}
