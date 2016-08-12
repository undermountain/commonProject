package common.base;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;

	//結果を反対にするか設定
	public boolean not=false;

	public String id;
	public ConditionBase(String id) {
		this.id=id;
	}
	public boolean check(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap){
		if(dataMap.containsKey(id)){
			return (boolean)dataMap.get(id);
		}
		return resultSetter(dataMap,checkCondition(request, response, dataMap));
	}

	public abstract boolean checkCondition(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap);

	public boolean resultSetter(Map<String,Object> dataMap,boolean checkResult){
		boolean result=not ? !checkResult:checkResult;
		dataMap.put(id, result);
		return result;
	}
}
