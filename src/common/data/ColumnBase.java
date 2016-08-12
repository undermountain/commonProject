package common.data;

import java.io.Serializable;

import common.base.FieldBase;

public abstract class ColumnBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public String name;
	public abstract String toHtmlValue();
	public abstract String getValue();
	public abstract void setValue(String val);

	public FieldBase inputField;

}
