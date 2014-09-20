package test.util.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import test.util.codec.BaseCodecUtil;


public class PropertiesUtil {
	

	public static String getLastDateTime() throws Exception{
		InputStream input = new FileInputStream("config/config.ini");
		Properties props = new Properties();
		props.load(input);
		String lastDateTime = "";
		if (props.containsKey("lastDateTime")) {
			lastDateTime = props.getProperty("lastDateTime");
		}else{
			lastDateTime = "0";
		}
		return lastDateTime;
	}
	
	
	public static void setLastDateTime(String lastDateTime) throws Exception{
		OutputStream out = new FileOutputStream("config/config.ini");
		Properties props = new Properties();
		props.put("lastDateTime", lastDateTime);
		props.store(out, "");
	}
	
	public static void main(String[] args) {
		try {
			setLastDateTime("2014-99-22 14:58:35");
			String lastDateTime = getLastDateTime();
			System.err.println(lastDateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void storeProperties(Map<String, String> kvmap, String file, boolean append) throws Exception{
		Map<String, String> kvmapCodec = new HashMap<String, String>();
		Set<String> keys = kvmap.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = kvmap.get(key);
			kvmapCodec.put(keyEncode(key), valueEncode(value));
		}
		Properties props = new Properties();
		props.putAll(kvmapCodec);
		FileWriter fw = new FileWriter(file,append);
		props.store(fw, "");
	}
		
	public static String keyEncode(String content) throws Exception{
		String key = BaseCodecUtil.encodeB32(content);
		key = BaseCodecUtil.encodeB32(key);
		key = BaseCodecUtil.encodeB32(key);
		return key;
	}
		
	public static String valueEncode(String content) throws Exception{
		String value = BaseCodecUtil.encodeB64(content);
		value = BaseCodecUtil.encodeB64(value);
		value = BaseCodecUtil.encodeB64(value);
		return value;
	}
		
	public static String valueDecode(String content) throws Exception{
		String value = BaseCodecUtil.decodeB64(content);
		value = BaseCodecUtil.decodeB64(value);
		value = BaseCodecUtil.decodeB64(value);
		return value;
	}
	
	public static Map<String, String> getProperties(List<String> keys,String fileName) throws Exception{
		InputStream input = new FileInputStream(fileName);
		Properties props = new Properties();
		props.load(input);
		Map<String, String> kvmap = new HashMap<String, String>();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String keyCodec = keyEncode(key);
			if (props.containsKey(keyCodec)) {
				String valueCodec = props.getProperty(keyCodec);
				String value = valueDecode(valueCodec);
				kvmap.put(key, value);
			}else{
				return null;
			}
		}
		return kvmap;
	}
	
	public static void saveCookies(List<String> cookieKeyValues, String propertiesFile, boolean append) throws Exception{

		Map<String, String> mapCookies = new HashMap<String, String>();
		for (int i = 0; i < cookieKeyValues.size(); i++) {
			int index = cookieKeyValues.get(i).indexOf(";");
			if (index < 0) {
				continue;
			}
			String kvString = cookieKeyValues.get(i).substring(0, index);
			System.out.println(kvString);
			String[] kvItem = kvString.trim().split("=",2);
			mapCookies.put(kvItem[0], kvItem[1]);
		}
		
		storeProperties(mapCookies,propertiesFile,append);
	}
  
}
