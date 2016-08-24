package common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.web.Elementer;
import common.web.Model;

public abstract class FieldBase extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;


	public String displayName;

	public boolean valid=true;

	public boolean required=false;
	public boolean visible=true;

	public List<ValidationBase> validationList;
	public List<String> errorMessageList;

	public String description;

	public int height;
	public int width;

	public String style;

	public FieldBase(String tagName,String id){
		super(tagName);

		setAttribute("id", id);
		setAttribute("name",id);
	}
	public FieldBase(String tagName,String id,String displayName){
		super(tagName);

		this.displayName=displayName;
		setAttribute("id", id);
		setAttribute("name",id);
	}


	public String toErrorMessage(){
		if(errorMessageList==null)return "";
		if(errorMessageList.size()==0)return "";

		StringBuilder sb=new StringBuilder();
		sb.append("<ul class='errorlist'>");
		int size=errorMessageList.size();
		for(int i=0;i<size;i++){
			sb.append("<li>");
			sb.append(errorMessageList.get(i));
			sb.append("</li>");
		}
		sb.append("</ul>");

		return sb.toString();
	}

	public boolean setValueByRequest(HttpServletRequest request) {
		String value=request.getParameter(getId()).trim();
		setValue(value);
		return value!=null;
	}
	public void setValue(Object value){
		setAttribute("value",value);
	}

	public Object getValue(){
		return getAttribute("value");
	}
	public String getStrValue(){
		if(getValue()!=null)
			return getValue().toString();
		else
			return "";
	}

	public Integer getIntValue(){
		if(getValue()!=null && common.lib.Check.isInteger(getStrValue()))
			return Integer.valueOf(getStrValue());
		else
			return null;
	}

	public Object[] getValueToArray(){
		return null;
	}

	public boolean runValidation(Model model) {

		if(validationList==null)return true;

		boolean result=true;
		int size=validationList.size();
		for(int i=0;i<size;i++){
			String errMsg=validationList.get(i).validate(this,model);
			if(errMsg!=null){
				result=false;
				if(errorMessageList==null){
					errorMessageList=new ArrayList<String>();
				}
				errorMessageList.add(errMsg);
				if(!validationList.get(i).SubsequentValid)break;
			}
		}
		return result;
	}

	public void addValidation(ValidationBase validation){
		if(validationList==null){
			validationList=new ArrayList<ValidationBase>();
		}
		validationList.add(validation);
	}
	@Override
	public void toHtml(StringBuilder sb) {
		sb.append("<");
		sb.append(tagName);

		if(attribute!=null)writeAttribute(sb);
		if(style!=null)writeStyle(sb);

		if(single){
			sb.append("/>");
			return;
		}else{
			sb.append(">");
		}
		if(inner!=null)sb.append(inner);
		if(childElementer!=null)
			writeElementer(sb);

		sb.append("</");
		sb.append(tagName);
		sb.append(">");

		/*if(errorMessageList!=null){
			sb.append("<ul class='errorlist'>");
			int size=errorMessageList.size();
			for(int i=0;i<size;i++){
				sb.append("<li>");
				sb.append(errorMessageList.get(i));
				sb.append("</li>");
			}
			sb.append("</ul>");
		}*/
	}


}
