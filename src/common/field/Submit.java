/**
 *
 */
package common.field;

import java.io.Serializable;

import common.base.FieldBase;

/**
 * @author under_000
 *
 */
public class Submit extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * @param tagName
	 * @param id
	 */
	public Submit(String id,String caption) {
		super("input", id);
		setAttribute("type", "submit");
		setValue(caption);
	}

}
