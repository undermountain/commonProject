package common.field;

import java.io.Serializable;

import common.base.FieldBase;

public class Hidden extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Hidden(String id,String value) {
		super("input",id);
		setAttribute("type", "hidden");
		setAttribute("id", id);
		setAttribute("name", id);
		setAttribute("value",value);
	}


}
