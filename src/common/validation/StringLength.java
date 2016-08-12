package common.validation;

import java.io.Serializable;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.web.Model;

public class StringLength extends ValidationBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public StringLength(Integer minlength,Integer maxlength) {
		this.maxlength=maxlength;
		this.minlength=minlength;
		this.errorMessage="「%s」は";
		if(minlength!=null){
			this.errorMessage+=minlength.toString()+"文字以上";
		}
		if(maxlength!=null){
			this.errorMessage+=maxlength.toString()+"文字以下";
		}
		this.errorMessage+="で入力してください。";
	}

	public Integer maxlength;
	public Integer minlength;

	@Override
	public boolean check(FieldBase field,Model model) {
		if(maxlength==null){
			if(minlength==null){
				return true;
			}else{
				if(field.getValue().length()<minlength){
					return false;
				}
			}

		}else{
			if(minlength==null){
				if(field.getValue().length()>maxlength){
					return false;
				}
			}else{
				if(field.getValue().length()<minlength||field.getValue().length()>maxlength){
					return false;
				}
			}
		}
		return true;
	}

}
