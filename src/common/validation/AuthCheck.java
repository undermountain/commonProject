package common.validation;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.consts.EDir;
import common.consts.Path;
import common.field.IdPassword;
import common.io.PropertiesLib;
import common.io.Util;
import common.web.Model;

public class AuthCheck extends ValidationBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public AuthCheck() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean check(FieldBase field,Model model) {
		IdPassword idpass=(IdPassword)field;

		String path=idpass.isPub ? Path.getSavePath(idpass.siteName, EDir.auth, "login"):Path.getSavePath(idpass.siteName, EDir.auth_dev, "login");
		Properties properties = null;
		try {
			properties = PropertiesLib.getProperties(path);
		} catch (IOException e) {
			Util.errorLog(e.toString(), idpass.siteName, "ログインページ");
			return false;
		}

		return properties.getProperty(idpass.idId).equals(idpass.passwordId);
	}


}
