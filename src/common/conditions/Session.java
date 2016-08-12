package common.conditions;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ConditionBase;

public class Session extends ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	//検査するセッション名を設定
	public String sessionName;

	//値のチェックをするか設定
	public boolean valueCheck;

	//セッションの値と比較する
	public Object value;

	public Session(String name,String sessionName) {
		super(name);

		this.sessionName=sessionName;
	}
	public Session(String name,String sessionName,Object value){
		super(name);

		this.sessionName=sessionName;
		this.value=value;
		this.valueCheck=true;
	}

	@Override
	public boolean checkCondition(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
		boolean result;
		Object val=request.getSession().getAttribute(sessionName);
		result=val!=null;
		if(result&&this.valueCheck){
			result=val==value;
		}
		return result;

	}



}
