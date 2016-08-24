package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;

public class Button extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * デフォルトタイプ：サブミット
	 * @param id
	 * @param caption
	 */
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
	public boolean setValueByRequest(HttpServletRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}


}
