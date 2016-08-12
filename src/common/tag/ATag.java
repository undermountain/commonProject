package common.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.type.KeyValue;
import common.web.Elementer;

public class ATag extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;
	public ATag(String url, String display) {
		super("a");
		this.url=url;
		inner=display;
	}
	public ATag(String url, String display,KeyValue... attributes) {
		super("a");
		this.url=url;
		inner=display;

		for(int i=0;i<attributes.length;i++){
			setAttribute(attributes[i].key, attributes[i].value.toString());
		}
	}

	public void setUrl(String url){
		this.url=url;
	}
	public void setDisplay(String display){
		inner=display;
	}

	public String url;
	public List<KeyValue> urlParamList;
	public void addUrlParameter(KeyValue parameters){
		if(urlParamList==null)urlParamList=new ArrayList<KeyValue>();
		urlParamList.add(parameters);
	}

	@Override
	public void toHtml(StringBuilder sb) {
		if(urlParamList!=null){
			StringBuilder paramUrl=new StringBuilder();
			paramUrl.append(url);
			paramUrl.append("?");
			int size=urlParamList.size();
			for(int i=0;i<size;i++){
				if(i!=0)paramUrl.append("&");

				paramUrl.append(urlParamList.get(i).key);
				paramUrl.append("=");
				paramUrl.append(urlParamList.get(i).value);
			}

			setAttribute("href", paramUrl.toString());
		}else{
			setAttribute("href", url);
		}

		super.toHtml(sb);
	}


}
