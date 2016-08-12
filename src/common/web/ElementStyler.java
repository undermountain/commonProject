package common.web;

import java.io.Serializable;

public class ElementStyler implements Serializable {
	private static final long serialVersionUID = 1L;

	public String height;
	public String width;

	public String toStyle(){
		StringBuilder sb=new StringBuilder();

		if(height!=null){
			sb.append("height:");
			sb.append(height);
			sb.append(";");
		}
		if(width!=null){
			sb.append("width:");
			sb.append(width);
			sb.append(";");
		}


		return sb.toString();
	}
}
