package common.validation;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.web.Model;

public class Comparison extends ValidationBase {

	public Comparison(EComparison comparison, FieldBase target) {
		this.comparison=comparison;
		this.target=target;

		//デフォルトエラーメッセージ作成
		if(comparison==EComparison.equal){
			this.errorMessage="「%s」が「"+target.displayName+"」と一致しません。";
		}else if(comparison==EComparison.not){
			this.errorMessage="「%s」が「"+target.displayName+"」と一致しています。";
		}
	}
	public EComparison comparison;

	public FieldBase target;

	@Override
	public boolean check(FieldBase field,Model model) {
		if(comparison==EComparison.equal){
			return field.getValue().equals(target.getValue());
		}else if(comparison==EComparison.not){
			return !field.getValue().equals(target.getValue());
		}

		return false;
	}

}
