package common.field;

import java.io.Serializable;

import common.base.FieldBase;

public class Range extends FieldBase implements Serializable {

	public void setMin(int min){
		setAttribute("min", String.valueOf(min));
	}
	public int getMin(){
		return Integer.valueOf(getAttribute("min"));
	}
	public void setMax(int max){
		setAttribute("max", String.valueOf(max));
	}
	public int getMax(){
		return Integer.valueOf(getAttribute("max"));
	}


	public Range(String id,int min,int max) {
		super("input", id);
		init(min,max);
	}

	public Range(String id, String displayName,int min,int max) {
		super("input", id, displayName);
		init(min,max);
	}
	private void init(int min,int max){
		setAttribute("type", "range");
		setMin(min);
		setMax(max);

		setAttribute("onchange", "this.nextElementSibling.innerHTML=this.value;");

	}

	@Override
	public void toHtml(StringBuilder sb) {
		// TODO 自動生成されたメソッド・スタブ
		super.toHtml(sb);
		sb.append("<span>");
		sb.append(this.getValue());
		sb.append("</span>");
	}


}
