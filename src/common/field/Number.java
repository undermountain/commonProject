package common.field;

import java.io.Serializable;

import common.base.FieldBase;

public class Number extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Number(String id, String displayName) {
		super("input", id);
		this.displayName=displayName;
		setAttribute("type", "number");
		setImeModeOff();
	}


	public void setMaxlength(int len){
		setAttribute("maxlength", String.valueOf(len));
	}
	public void setMinlength(int len){
		setAttribute("minlength", String.valueOf(len));
	}
	public void setPattern(String pattern){
		setAttribute("pattern",pattern);
	}
	public void setImeModeOff(){
		setStyle("ime-mode", "mode:disabled");
	}

}
