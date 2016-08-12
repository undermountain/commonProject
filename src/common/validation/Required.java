package common.validation;

import java.io.Serializable;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.web.Model;

public class Required extends ValidationBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Required() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.errorMessage="%sは必須です";
	}

	@Override
	public boolean check(FieldBase field,Model model) {
		if(field.getValue()==null)return false;
		if(field.getValue().equals(""))return false;
		return true;
	}


}
