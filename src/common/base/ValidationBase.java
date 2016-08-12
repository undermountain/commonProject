package common.base;

import common.web.Model;

public abstract class ValidationBase {
	public String errorMessage;
	public boolean SubsequentValid;
	public ValidationBase setErrorMessage(String errorMessage){
		this.errorMessage=errorMessage;
		return this;
	}

	public boolean runValidateCondition(FieldBase field,Model model){
		return true;
	}

	public String validate(FieldBase field,Model model) {
		if(!runValidateCondition(field,model))return null;
		if(!check(field,model)){
			return String.format(errorMessage, field.displayName);
		}else{
			return null;
		}
	}
	public abstract boolean check(FieldBase field,Model model);

}
