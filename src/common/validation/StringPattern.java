package common.validation;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.base.FieldBase;
import common.base.ValidationBase;
import common.web.Model;

public class StringPattern extends ValidationBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public StringPattern(String pattern,String errorMessage) {
		this.pattern=pattern;
		this.errorMessage=errorMessage;

	}

	public String pattern;

	@Override
	public boolean check(FieldBase field,Model model) {
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(field.getValue());
		return m.matches();
	}

}
