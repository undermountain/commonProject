package common.lib;

public class Check {
	public static boolean isInteger(String val){
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
}
