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

		//プレフィックス
		sb.append(prefix);

		//NULL
		if(value==null){
			sb.append(nullString);
		}else{

			String result="";

			//フォーマット
			if(format!=null){
				result=String.format(format, value);
			}else{
				result=value.toString();
			}

			//エンコード
			result=rawString(result);

			//表示文字数
			if(displayMaxLength>0 && result.length()>displayMaxLength){
				result="<div style=\"cursor:default;\" title=\""+value.toString().replaceAll("\"", "\\\"")+"\">"+result.substring(0, displayMaxLength)+"…"+"</div>";
			}

			//文字列フォーマット
			if(stringFormat!=null){
				sb.append(String.format(stringFormat, result));
			}else{
				sb.append(result);
			}
		}

		//語尾
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
