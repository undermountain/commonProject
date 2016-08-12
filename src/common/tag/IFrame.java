package common.tag;

import java.io.Serializable;

import common.web.Elementer;

public class IFrame extends Elementer implements Serializable {

	public IFrame(String url) {
		super("iframe");

		setAttribute("src", url);
	}
}
