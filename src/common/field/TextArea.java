package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;

public class TextArea extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public TextArea(String id, String displayName) {
		super("textarea", id);
		this.displayName=displayName;
	}
	@Override
	public boolean setValue(HttpServletRequest request) {
		String value=request.getParameter(getId());
		if(value==null){
			inner="";
			return false;
		}else{
			inner=value;
			return true;
		}
	}
	@Override
	public void setValue(String value) {
		inner=value;
	}
	@Override
	public String getValue() {
		return inner;
	}


}
