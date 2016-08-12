package common.conditions;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ConditionBase;

public class Parameter extends ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Parameter(String id) {
		super(id);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String name;
	public String setId;

	@Override
	public boolean checkCondition(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> dataMap) {
		String val=request.getParameter(name);
		if(val==null)return false;

		if(setId!=null){
			dataMap.put(setId, val);
		}
		return true;
	}

}
