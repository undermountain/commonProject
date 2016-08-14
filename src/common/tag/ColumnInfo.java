package common.tag;

import java.io.Serializable;

public class ColumnInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean searchable=true;
	public boolean visible=true;
	public boolean orderable=true;
	public String format;
	public String stringFormat;
	public String prefix="";
	public String suffix="";
	public boolean raw;
	public String nullString="";
	public int displayMaxLength=0;
	public Integer maxheight;
	public Integer maxwidth;


	public String getDisplay(Object value){
		if(value==null){
			return nullString;
		}
		StringBuilder sb=new StringBuilder();

		if(value.getClass().isArray()){
			sb.append("<ul>");
			for(Object o:(Object[])value){
				sb.append("<li>");
				display(sb,o);
				sb.append("</li>");
			}
			sb.append("</ul>");
		}else{
			display(sb,value);
		}

		return sb.toString();
	}

	private void display(StringBuilder sb,Object value){
		if(value==null){
			sb.append(nullString);
			return;
		}
		sb.append(prefix);
		String result="";
		if(format!=null){
			result=String.format(format, value);
		}else{
			result=value.toString();
		}
		result=rawString(result);
		if(displayMaxLength>0 && result.length()>displayMaxLength){
			result=result.substring(0, displayMaxLength)+"…";
		}
		if(stringFormat!=null){
			sb.append(String.format(stringFormat, result));
		}else{
			sb.append(result);
		}

		sb.append(suffix);
	}

	private String rawString(String value) {
		// TODO 自動生成されたメソッド・スタブ
		if(raw){
			return value;
		}else{
			return common.web.Util.htmlEncode(value).replaceAll("\r\n", "<br>");
		}
	}
}
