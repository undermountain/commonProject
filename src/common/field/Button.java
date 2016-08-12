package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;

public class Button extends FieldBase implements Serializable {

	public Button( String id,String caption) {
		super("button", id);
		inner=caption;

		this.setAttribute("type", "submit");
	}
	public Button( String id,String caption,String value) {
		super("button", id);
		inner=caption;

		this.setAttribute("type", "submit");

		this.setValue(value);
	}
	@Override
	public boolean setValue(HttpServletRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}


}
