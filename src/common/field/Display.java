package common.field;

import java.io.Serializable;

import common.base.FieldBase;
import common.web.Util;

public class Display extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Display(String id,String value) {
		super("span", id);
		inner=Util.htmlEncode(value);
	}

	public Display(String id, String displayName,String value) {
		super("span", id, displayName);
		inner=Util.htmlEncode(value);
	}

	@Override
	public void setValue(Object value) {
		if(value!=null)
			inner=Util.htmlEncode(value.toString());
	}
	@Override
	public String getValue() {
		return inner;
	}
}
