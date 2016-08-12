package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;
import common.web.Elementer;

public class MultiSelect extends FieldBase implements Serializable {

	public String[] value;

	public MultiSelect( String id) {
		super("ul", id);

	}

	public MultiSelect(String id, String displayName) {
		super("ul", id, displayName);

	}

	public void addOptionItem(String value,String disp){
		Elementer liItem=new Elementer("li");
		Elementer label=new Elementer("label");
		Elementer checkItem=new Elementer("input");
		Elementer caption=new Elementer("span",disp);

		checkItem.setAttribute("type", "checkbox");
		checkItem.setAttribute("value", value);
		checkItem.setId(String.format("%s%s",getId(),this.childElementer==null?0:this.childElementer.size()));
		checkItem.setAttribute(getId(), value);

		label.addChild(checkItem,caption);
		liItem.addChild(label);

		this.addChild(liItem);

	}


	@Override
	public boolean setValue(HttpServletRequest request) {
		this.value=request.getParameterValues(getId());
		return true;
	}

	/**
	 * 一つの要素をチェックを切り替える
	 */
	@Override
	public void setValue(String value) {
		if(this.childElementer==null)return;
		for(int i=0;i<this.childElementer.size();i++){
			if(this.childElementer.get(i).childElementer.get(0).getAttribute("value").equals(value)){
				if(this.childElementer.get(i).childElementer.get(0).getAttribute("selected")==null){
					this.childElementer.get(i).childElementer.get(0).setAttribute("selected", "selected");
				}else{
					this.childElementer.get(i).childElementer.get(0).removeAttribute("selected");
				}
			}
		}
	}

	@Override
	public String getValue() {
		if(value==null)return null;
		StringBuilder sb = new StringBuilder();
		boolean first=true;
		for(String str : value) {
			if(first){
				first=false;
			}else{
				sb.append("\r\n");
			}
			sb.append(str);
		}

		return sb.toString();
	}


}
