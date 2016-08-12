package common.validation;

import java.io.File;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.consts.EDir;
import common.web.Model;

public class FileExist extends ValidationBase {

	public EDir epub;
	public boolean not=false;;

	public String path;

	public FileExist(String dirPath) {
		path=dirPath;
		this.errorMessage="%sは存在します";
	}

	@Override
	public boolean check(FieldBase field,Model model) {
		File file=new File(path + File.separator + field.getValue());
		return file.exists()==not;
	}

}
