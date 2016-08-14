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
	public boolean setValueByRequest(HttpServletRequest request) {
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
	public void setValue(Object value) {
		if(value!=null)
			inner=value.toString();
	}
	@Override
	public String getValue() {
		return inner;
	}


}
