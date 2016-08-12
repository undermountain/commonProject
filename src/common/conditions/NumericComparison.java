package common.conditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ConditionBase;

public class NumericComparison extends ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;

	//比較する値のデータID
	public String mainId;
	public String targetId;
	public BigDecimal targetValue;

	public EComparison conparison;

	public NumericComparison(String id) {
		super(id);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean checkCondition(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

		BigDecimal main=new BigDecimal(dataMap.get(mainId).toString());
		BigDecimal target;
		if(targetId==null){
			target=targetValue;
		}else{
			target=new BigDecimal(dataMap.get(targetId).toString());
		}

		if(conparison==EComparison.equal){
			return main.compareTo(target)==0;
		}else if(conparison==EComparison.ormore){//以上
			return main.compareTo(target)>=0;
		}else if(conparison==EComparison.orless){//以下
			return main.compareTo(target)<=0;
		}else if(conparison==EComparison.more){//超える
			return main.compareTo(target)>0;
		}else if(conparison==EComparison.less){//未満
			return main.compareTo(target)<0;
		}else{
			return false;
		}
	}

}
