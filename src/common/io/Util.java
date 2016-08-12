package common.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import common.consts.EDev;
import common.consts.Path;

public class Util {
	/*public static void modelSave(String siteName,Model model){

		ClassSerializer.serialize(model, Path.getPubPath("_test",EPub.model,model.name));
	}*/

	public static void errorLog(String error,String siteName,String pagename){
		File file=new File(Path.getCommonSavePath(siteName, EDev.err, pagename));
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);

			fileWriter.write(error);
			fileWriter.close();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
}
