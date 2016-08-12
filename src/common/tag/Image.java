package common.tag;

import java.io.Serializable;

import common.web.Elementer;

public class Image  extends Elementer implements Serializable {

	public String src;

	public Image(String title,String src) {
		super("img");
		this.setAttribute("alt", title);
		this.setAttribute("title", title);

		this.setAttribute("src", src);
	}



}
