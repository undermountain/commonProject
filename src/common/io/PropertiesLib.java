package common.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLib {

	public PropertiesLib() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static Properties getProperties(String path) throws IOException{
		InputStream is=new FileInputStream(path);
		Properties properties=new Properties();
		properties.load(is);
		is.close();
		return properties;
	}
}
