package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;

public class Checkbox extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;


	public Checkbox(String id, String displayName) {
		super("input", id, displayName);
		// TODO 自動生成されたコンストラクター・スタブ
		this.setId(id);
		this.displayName=displayName;

		this.setAttribute("type", "checkbox");

		this.setValue("true");
	}

	@Override
	public boolean setValueByRequest(HttpServletRequest request) {
		if(request.getParameterMap().containsKey(getId())){
			this.setAttribute("selected", "selected");
		}
		return true;
	}

	@Override
	public void setValue(Object value) {
		if(value!=null && value.toString().equals("true")){
			this.setAttribute("selected", "selected");
		}
	}

	@Override
	public Object getValue() {
		return this.getAttribute("selected")!=null;
	}

	@Override
	public String getStrValue() {
		// TODO 自動生成されたメソッド・スタブ
		return getValue().toString();
	}



}
