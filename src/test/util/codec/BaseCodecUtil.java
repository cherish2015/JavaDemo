package test.util.codec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseCodecUtil {
	
	private static Logger log = LoggerFactory.getLogger(BaseCodecUtil.class);
	
	private static String charset = "UTF-8";
	
	private static Base32 base32 = new Base32(true);
	
	public static void main(String[] args) throws Exception {
		String content = "Liu Hongyan";
		content = BaseCodecUtil.encodeB64(content);
		log.info(content);
		content = BaseCodecUtil.encodeB64(content);
		log.info(content);
		content = BaseCodecUtil.encodeB64(content);
		log.info(content);
		
		log.error("--------------");
		content = BaseCodecUtil.decodeB64(content);
		log.info(content);
		content = BaseCodecUtil.decodeB64(content);
		log.info(content);
		content = BaseCodecUtil.decodeB64(content);
		log.info(content);
		
		log.error("--------------");
		content = BaseCodecUtil.encodeB32(content);
		log.info(content);
		content = BaseCodecUtil.encodeB32(content);
		log.info(content);
		content = BaseCodecUtil.encodeB32(content);
		log.info(content);
	}
	
	public static String encodeB64(String content) throws Exception{
		String re = "";
		byte[] result = Base64.encodeBase64(content.getBytes(charset));
		re = new String(result,charset);
		return re;
	}
	
	public static String decodeB64(String content) throws Exception{
		String re = "";
		byte[] result = Base64.decodeBase64(content.getBytes(charset));
		re = new String(result,charset);
		return re;
	}
	
	public static String encodeB32(String content) throws Exception{
		String re = "";
		re = base32.encodeAsString(content.getBytes(charset));
		return re;
	}
	
	public static String decodeB32(String content) throws Exception{
		byte[] result = base32.decode(content);
		String re = new String(result,charset);
		return re;
	}

}
