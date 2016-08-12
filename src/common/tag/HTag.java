package common.tag;

import java.io.Serializable;

import common.web.Elementer;

public class HTag extends Elementer implements Serializable {
	private static final long serialVersionUID =1L;

	public HTag(int number,String inner) {
		super(String.format("h%d", number));
		this.inner=inner;
	}

}
