package common.pub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ConditionBase;

public class AndConditions extends ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public AndConditions(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public List<ConditionBase> conditionsList;
	@Override
	public boolean checkCondition(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> data) {
		if(conditionsList!=null){
			int size=conditionsList.size();
			for(int i=0;i<size;i++){
				if(!conditionsList.get(i).check(request, response, data)){
					return false;
				}
			}
			return true;
		}
		return true;
	}

}
