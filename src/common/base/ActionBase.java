package common.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public String id;
	public ActionBase(String id){
		this.id=id;
	}

	public String conditionId;

	public boolean runAction=true;
	public boolean run(HttpServletRequest request,HttpServletResponse response,Map<String, Object> dataMap, Map<String, ConditionBase> conditionMap) throws IOException{

		if(conditionId!=null){
			if(conditionMap.get(conditionId).check(request,response,dataMap))
				trueAction(request, response, dataMap);
			else
				falseAction(request,response,dataMap);
		}else{
			trueAction(request, response, dataMap);
		}

		return runAction;
	}

	public abstract void trueAction(HttpServletRequest request,HttpServletResponse response,Map<String, Object> dataMap) throws IOException;
	public abstract void falseAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap);

	public Object toValue(){
		return id;
	}

	public String getClassName(){
		String[] name=this.getClass().getName().split(".");
		return name[name.length-1];
	}
}
