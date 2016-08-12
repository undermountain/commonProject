package common.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Elementer implements Serializable {
	private static final long serialVersionUID = 1L;

	public Elementer(String tagName) {
		this.tagName=tagName;
	}
	public Elementer(String tagName,String inner){
		this.tagName=tagName;
		this.inner=inner;
	}

	public String tagName;

	public HashMap<String,String> attribute;
	public HashMap<String,String> style;

	public List<Elementer> childElementer;

	public String inner="";

	public boolean single;

	public void setId(String id){
		setAttribute("id", id);
		setAttribute("name",id);
	}
	public String getId(){
		return getAttribute("id");
	}

	public String toHtml(){
		StringBuilder sb=new StringBuilder();
		toHtml(sb);
		return sb.toString();
	}

	public void toHtml(StringBuilder sb){
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
	}

	protected void writeElementer(StringBuilder sb) {
		int size=childElementer.size();
		for(int i=0;i<size;i++){
			childElementer.get(i).toHtml(sb);
		}
	}

	protected void writeAttribute(StringBuilder sb) {

		for(Map.Entry<String, String> entry : attribute.entrySet()){
			sb.append(" ");
			sb.append(entry.getKey());
			sb.append("=\"");
			sb.append(Util.htmlEncode(entry.getValue(),false));
			sb.append("\"");
		}

	}

	protected void writeStyle(StringBuilder sb) {

		for(Map.Entry<String, String> entry : style.entrySet()){
			sb.append(" ");
			sb.append(entry.getKey());
			sb.append("=\"");
			sb.append(Util.htmlEncode(entry.getValue(),false));
			sb.append("\"");
		}

	}


	public void addChild(Elementer... elementer){
		if(childElementer==null)childElementer=new ArrayList<Elementer>();
		for(int i=0;i<elementer.length;i++)
			childElementer.add(elementer[i]);
	}


	public Elementer getChildById(String id){
		int size=childElementer.size();
		for(int i=0;i<size;i++){
			if(childElementer.get(i).getAttribute("id").equals(id))return childElementer.get(i);
		}
		return null;
	}

	public void setAttribute(String key,String value){
		if(attribute==null)attribute=new HashMap<String,String>();
		attribute.put(key,value);
	}
	public String getAttribute(String key){
		if(attribute==null)return null;
		return attribute.get(key);
	}

	public void setStyle(String key,String... value){
		if(style==null)style=new HashMap<String,String>();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<value.length;i++){
			if(i>0)sb.append(" ");
			sb.append(value[i]);
		}
		style.put(key,sb.toString());
	}
	public String[] getStyle(String key){
		if(style==null)return null;
		return style.get(key).split(" ");
	}

	public void setClass(String... value){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<value.length;i++){
			if(i>0)sb.append(" ");
			sb.append(value[i]);
		}
		setAttribute("class", sb.toString());
	}
	public void removeAttribute(String key) {
		attribute.remove(key);

	}

}
