package common.conditions;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ConditionBase;
import common.type.DateEx;

public class DateComparison extends ConditionBase implements Serializable {
	private static final long serialVersionUID = 1L;

	//比較する値のデータID
	public String mainId;
	public String targetId;
	public DateEx targetValue;

	public EComparison conparison;

	public DateComparison(String id) {
		super(id);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean checkCondition(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

		DateEx main=null;
		DateEx target = null;
		try {
			main = new DateEx(dataMap.get(mainId));
			if(targetId==null){
				target=targetValue;
			}else{
				target = new DateEx(dataMap.get(targetId));
			}
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
