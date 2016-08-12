package common.lib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Util {

	public Util() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] toArrayFromMap(HashMap<String,?> hashMap){
		int size=hashMap.size();
		Object[] result=new Object[size];

		int i=0;

        for(Map.Entry<String, ?> map : hashMap.entrySet()) {
            result[i]=map.getValue();

            i++;
        }


        return result==null ? null:(T[])result;


	}

	public static String getClassName(Object object){
		String[] name=object.getClass().getName().split("\\.");
		return name[name.length-1];
	}

	public static String createToken(){
		//UID uid=new UID();
		//return uid.toString();
		//UUID uuid=UUID.randomUUID();
		return String.valueOf(Math.abs(UUID.randomUUID().hashCode()));

	}

	public static String fillInZero(int num,int digit){
		return String.format("%1$0"+String.valueOf(digit)+"d", num);
	}


	@SuppressWarnings("unchecked")
	public static <T> T addObjectToArray(Object[] objArray,Object... addObj){
		Object[] newArray=new Object[objArray.length+addObj.length];
		System.arraycopy(objArray, 0, newArray, 0, objArray.length);
		for(int i=0;i<addObj.length;i++){
			newArray[objArray.length+i]=addObj[i];
		}
		return (T)newArray;
	}
	@SuppressWarnings("unchecked")
	public static <T> T removeObjectFromArray(Object[] objArray,int index){
		List<Object> newList=new ArrayList<Object>();
		newList.addAll(Arrays.asList(objArray));
		newList.remove(index);
		return (T)newList.toArray();
	}

	public static byte[] readAllByteOfInputStream(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte [] buffer = new byte[1024];
	    while(true) {
	        int len = inputStream.read(buffer);
	        if(len < 0) {
	            break;
	        }
	        bout.write(buffer, 0, len);
	    }
	    return bout.toByteArray();
	}

	public static String fillInZero(long num, int digit) {
		return String.format("%1$0"+String.valueOf(digit)+"d", num);
	}

	public static String fillInZero(Number num, int digit) {
		return String.format("%1$0"+String.valueOf(digit)+"d", num);
	}

	public static <T> List<T> newList(int size,Class<T> c){
		List<T> result =new ArrayList<T>();
		for(int i=0;i<size;i++){
			try {
				result.add(c.newInstance());
			} catch (InstantiationException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return result;

	}
}
