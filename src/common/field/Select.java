package common.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;
import common.web.Elementer;

public class Select extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
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
	public boolean setValueByRequest(HttpServletRequest request) {
		setValue(request.getParameter(getId()));

		return getValue()!=null;
	}

	@Override
	public void setValue(Object value) {

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
		if(this.childElementer==null)return null;
		int size=this.childElementer.size();
		for(int i=0;i<size;i++){

			if(this.childElementer.get(i).getAttribute("selected")!=null){
				return this.childElementer.get(i).getAttribute("value");
			}
		}
		return null;
	}

	public String[] getOptionItems() {
		List<String> list=new ArrayList<String>();
		if(this.childElementer==null)return null;
		int size=this.childElementer.size();
		for(int i=0;i<size;i++){
			list.add(this.childElementer.get(i).getAttribute("value"));

		}
		return list.toArray(new String[list.size()]);
	}


}
