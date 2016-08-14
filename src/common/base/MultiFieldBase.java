package common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.web.Elementer;
import common.web.Model;

public class MultiFieldBase extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<String> valueList;

	public FieldBase fieldBase;

	public MultiFieldBase(FieldBase field) {
		super("div", field.getId());
		field.setAttribute("id", "");
		this.displayName=field.displayName;
		valueList=new ArrayList<String>();
		this.fieldBase=field;

		addCssClass("_multifield");

		Elementer template=new Elementer("div");
		template.setStyle("display", "none");
		template.inner=field.toHtml();
		this.inner+=template.toHtml();
	}


	@Override
	public boolean setValueByRequest(HttpServletRequest request) {
		String[] param=request.getParameterValues(this.getId());
		if(param==null)return false;
		for(String p:param){
			valueList.add(p.trim());
		}
		return true;
	}

	@Override
	public void setValue(Object value) {
		if(value.getClass().isArray()){
			for(Object o:(Object[])value){
				valueList.add(o==null ? "":o.toString());
			}
		}else{
			valueList.add(value==null ? "":value.toString());
		}
		super.setValue(value);
	}

	@Override
	public Object getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return valueList.toArray();
	}

	@Override
	public String getStrValue() {

		return String.join("\r\n", valueList);
	}

	@Override
	public boolean runValidation(Model model) {
		boolean result=true;
		for(String val:valueList){
			fieldBase.setValue(val);
			if(validationList==null)return true;

			int size=validationList.size();
			for(int i=0;i<size;i++){
				String errMsg=validationList.get(i).validate(fieldBase,model);
				if(errMsg!=null){
					result=false;
					if(errorMessageList==null){
						errorMessageList=new ArrayList<String>();
					}
					errorMessageList.add(errMsg);
					if(!validationList.get(i).SubsequentValid)break;
				}
			}


		}
		return result;
	}


	@Override
	public void toHtml(StringBuilder sb) {

		StringBuilder sb2=new StringBuilder();
		for(int i=0;i<=valueList.size();i++){
			if(i==valueList.size()){
				fieldBase.setAttribute("id",getId()+String.valueOf(i));
				fieldBase.setValue("");
				fieldBase.toHtml(sb2);

			}else{
				fieldBase.setAttribute("id",getId()+String.valueOf(i));
				fieldBase.setValue(valueList.get(i));
				fieldBase.toHtml(sb2);
			}
		}
		this.inner+=sb2.toString();
		super.toHtml(sb);
	}


	@Override
	public Object[] getValueToArray() {
		return valueList.toArray();
	}


}
