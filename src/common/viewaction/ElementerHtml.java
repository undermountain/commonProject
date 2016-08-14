package common.viewaction;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ActionBase;
import common.web.Elementer;

public class ElementerHtml extends ActionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Elementer> elementList;

	public ElementerHtml(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}



	@Override
	public void trueAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap)
			throws IOException {
		StringBuilder sb=new StringBuilder();
		int size=elementList.size();
		for(int i=0;i<size;i++){
			elementList.get(i).toHtml(sb);
		}
		response.getWriter().append(sb.toString());

	}

	@Override
	public void falseAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
