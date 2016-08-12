package common.field;

import java.io.Serializable;

import common.base.FieldBase;
import common.web.Util;

public class Display extends FieldBase implements Serializable {

	public Display(String id,String value) {
		super("span", id);
		inner=Util.htmlEncode(value);
	}

	public Display(String id, String displayName,String value) {
		super("span", id, displayName);
		inner=Util.htmlEncode(value);
	}

	@Override
	public void setValue(String value) {
		inner=Util.htmlEncode(value);
	}
	@Override
	public String getValue() {
		return inner;
	}
}
