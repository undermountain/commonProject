package common.tag;

import java.io.Serializable;

import common.web.Elementer;

public class IFrame extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;
	public IFrame(String url) {
		super("iframe");

		setAttribute("src", url);
	}
}
