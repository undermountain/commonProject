package common.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;
import common.web.Elementer;

public class MultiSelect extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;

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
		checkItem.setAttribute("name", getId());

		label.addChild(checkItem,caption);
		liItem.addChild(label);

		this.addChild(liItem);

	}


	@Override
	public boolean setValueByRequest(HttpServletRequest request) {

		setValue(request.getParameterValues(getId()));
		return true;
	}

	/**
	 * 一つの要素をチェックを切り替える
	 */
	@Override
	public void setValue(Object value) {
		if(value==null)return;
		if(value.getClass().isArray()){
			List<Object> obj = Arrays.asList((Object[])value);
			for(int i=0;i<this.childElementer.size();i++){
				Elementer element=this.childElementer.get(i).childElementer.get(0).childElementer.get(0);
				if(obj.contains(element.getAttribute("value"))){
					element.setAttribute("checked", "checked");
				}else{
					element.removeAttribute("checked");
				}
			}
		}else{
			if(this.childElementer==null)return;
			for(int i=0;i<this.childElementer.size();i++){
				Elementer element=this.childElementer.get(i).childElementer.get(0).childElementer.get(0);
				if(element.getAttribute("value").equals(value)){
					if(element.getAttribute("checked")==null){
						element.setAttribute("checked", "checked");
					}else{
						element.removeAttribute("checked");
					}
				}
			}
		}
	}

	@Override
	public String getStrValue() {
		if(getValueToArray()==null)return null;
		StringBuilder sb = new StringBuilder();
		boolean first=true;
		for(Object str : getValueToArray()) {
			if(first){
				first=false;
			}else{
				sb.append("\r\n");
			}
			sb.append(str);
		}

		return sb.toString();
	}

	@Override
	public Object getValue() {
		if(getValueToArray()==null)return null;
		return getValueToArray();
	}

	@Override
	public Object[] getValueToArray() {
		// TODO 自動生成されたメソッド・スタブ
		List<Object> list=new ArrayList<Object>();

		int i=0;
		while(true){

			Elementer element=getChildById(getId()+String.valueOf(i++));
			if(element==null)break;
			if(element.getAttribute("checked")!=null)
				list.add(element.getAttribute("value"));
		}

		return list.toArray();
	}

	public String[] getOptionItems() {
		List<String> list=new ArrayList<String>();

		int i=0;
		while(true){

			Elementer element=getChildById(getId()+String.valueOf(i++));
			if(element==null)break;
			list.add(element.getAttribute("value"));
		}

		return list.toArray(new String[list.size()]);
	}



}
