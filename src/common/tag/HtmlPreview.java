package common.tag;

import java.io.Serializable;

import common.web.Elementer;

public class HtmlPreview extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;

	public HtmlPreview(String html) {
		super("iframe");
		this.setAttribute("allowfullscreen", "true");
		this.setAttribute("onload", String.format("this.contentWindow.document.body.innerHTML=\"%s\";", html.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r\n","")));
	}

}
