package test.lang;

public class StringUtils {
	
	public static String IntToString(String value){
		String temp = ""+value;
		if (temp.length() >= 4) {
			temp = temp.substring(0, 4);
		}else{
			temp = "    "+temp;
			if (temp.length() >=4) {
				temp = temp.substring(temp.length()-4, temp.length());
			}
		}
		return temp;
	}
	
	public static String IntToString8(String value){
		String temp = ""+value;
		if (temp.length() >= 8) {
			temp = temp.substring(0, 8);
		}else{
			temp = "    "+temp;
			if (temp.length() >=8) {
				temp = temp.substring(temp.length()-8, temp.length());
			}
		}
		return temp;
	}
	
	

}