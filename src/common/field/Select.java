package common.field;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;
import common.web.Elementer;

public class Select extends FieldBase implements Serializable {

	public String value;

	public Select( String id) {
		super("select", id);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Select(String id, String displayName) {
		super("select", id, displayName);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	public void addOptionItem(String value,String disp){
		Elementer optionItem=new Elementer("option", disp);
		optionItem.setAttribute("value", value);
		this.addChild(optionItem);

	}

	@Override
	public boolean setValue(HttpServletRequest request) {
		setValue(request.getParameter(getId()));

		return value!=null;
	}

	@Override
	public void setValue(String value) {
		this.value=value;
		if(this.childElementer==null)return;
		int size=this.childElementer.size();
		for(int i=0;i<size;i++){
			if(this.childElementer.get(i).getAttribute("value").equals(value)){
				this.childElementer.get(i).setAttribute("selected", "selected");
				break;
			}
		}
	}

	@Override
	public String getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return value;
	}


}
