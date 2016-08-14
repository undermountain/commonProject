package common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.base.ActionBase;
import common.base.FieldBase;
import common.tag.InputTable;
import common.type.KeyList;
import common.type.KeyValue;
import common.type.KeyValueEx;

public class Model {


	public static final String MESSAGE="_msg";

	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public Model(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.request=request;
		this.session=session;
		this.response=response;

		this.login=session.getAttribute("id")==null;
	}

	public boolean login;

	public boolean checkToken=false;

	public String title;
	public String heading;

	public KeyList<Object> dataList;
	public Object getData(String id){
		if(dataList==null)return null;
		return dataList.get(id);
	}
	public void addData(String id,Object obj){
		if(dataList==null)dataList=new KeyList<Object>();
		dataList.add(id, obj);
	}

	public KeyList<Elementer> elementList;
	public Elementer getElement(String id){
		if(elementList==null)return null;
		return elementList.get(id);
	}
	public void addElement(String id,Elementer element){
		if(elementList==null)elementList=new KeyList<Elementer>();
		elementList.add(id, element);
	}
	public void addElement(Elementer element){
		if(elementList==null)elementList=new KeyList<Elementer>();
		elementList.add(element.getId(), element);
	}


	public KeyList<FieldBase> fieldList;

	public FieldBase getField(String id){
		if(fieldList==null)return null;
		return (FieldBase)fieldList.get(id);
	}
	public FieldBase getField(int index){
		if(fieldList==null)return null;
		return (FieldBase)fieldList.get(index);
	}
	public void addField(FieldBase field){
		if(fieldList==null)fieldList=new KeyList<FieldBase>();
		fieldList.add(field.getId(), field);
	}

	public void addFieldAll(FieldBase... field){
		if(fieldList==null)fieldList=new KeyList<FieldBase>();
		for(int i=0;i<field.length;i++){

			fieldList.add(field[i].getId(), field[i]);
		}
	}

	public boolean setValue(HttpServletRequest request){
		boolean result=true;
		for(KeyValueEx<FieldBase> kv : fieldList.list) {
			if(!kv.value.setValueByRequest(request))result=false;
        }
		return result;
	}

	public boolean runValidation(HttpServletRequest request){
		boolean result=true;
		for(KeyValueEx<FieldBase> kv : fieldList.list) {
			if(!kv.value.runValidation(this)){
				if(errorMessageList==null)errorMessageList=new ArrayList<String>();

				errorMessageList.addAll(kv.value.errorMessageList);
				result=false;
			}
        }
		return result;
	}

	public List<String> errorMessageList;
	public void addErrorMessage(String message){
		if(errorMessageList==null){
			errorMessageList=new ArrayList<String>();
		}
		errorMessageList.add(message);
	}
	public String errorMessageHtml(){
		if(errorMessageList==null)return "";
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


	public KeyList<ActionBase> actionList;
	public ActionBase getAction(String id){
		if(actionList==null)return null;
		return actionList.get(id);
	}
	public void addAction(String id,ActionBase action){
		if(actionList==null)actionList=new KeyList<ActionBase>();
		actionList.add(id, action);
	}

	public String writeTokenInput(){
		String token=Util.setToken(request);

		return String.format("<input type='hidden' name='_token' value='%s'/>", token);
	}
	public boolean checkToken(){
		return Util.checkToken(request);
	}

	public void setAuth(String id,String mail,String name){
		session.setAttribute("id", id);
		session.setAttribute("mail", mail);
		session.setAttribute("name", name);
	}
	public void removeAuth(){
		session.removeAttribute("id");
		session.removeAttribute("mail");
		session.removeAttribute("name");
	}

	public String getUserId() {
		return session.getAttribute("id").toString();
	}
	public String getUserIdFillInZero(){
		return common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6);
	}

	public String getUserMail() {
		return session.getAttribute("mail").toString();
	}
	public String getUserName() {
		return session.getAttribute("name").toString();
	}

	private int msgCount=0;
	public void setMessage(String message) throws UnsupportedEncodingException{
		//session.setAttribute("msg", message);

		Cookie msg = new Cookie(String.format("%s%s", MESSAGE,msgCount++),URLEncoder.encode(message,"UTF-8"));
		msg.setMaxAge(120);
		response.addCookie(msg);
		session.setAttribute("_setMsg", true);
	}

	public void setConfirmPopup(String message,String yesUrl,String noUrl){
		session.setAttribute("confirm", message);
		if(yesUrl!=null){
			session.setAttribute("confirm_yes", yesUrl);
		}
		if(noUrl!=null){
			session.setAttribute("confirm_no", noUrl);
		}
	}
	public KeyValue createPToken() {
		session.setAttribute("_ptoken", common.lib.Util.createToken());
		return new KeyValue("_ptoken", session.getAttribute("_ptoken"));
	}

	public String InputTableToHtml(){
		Object[] fields=this.fieldList.toArray();
		return new InputTable(Arrays.asList(fields).toArray(new FieldBase[fields.length])).toHtml();
	}
}
