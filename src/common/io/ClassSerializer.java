package common.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClassSerializer {
		public static void serialize(Object model,String path) throws IOException{
			ObjectOutputStream objectOutputStream = null;
			try {
				objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
				objectOutputStream.writeObject(model);

			}finally {
					objectOutputStream.close();
			}

		}

		@SuppressWarnings({ "unchecked" })
		public static <T> T deserialize(String path) throws ClassNotFoundException, IOException{
			ObjectInputStream objectInputStream = null;
			try {
				objectInputStream = new ObjectInputStream(new FileInputStream(path));
				return (T)objectInputStream.readObject();
			}finally {
					objectInputStream.close();

			}
		}
}
