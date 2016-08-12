package common.validation;

import common.base.FieldBase;
import common.web.Model;

public interface IChecker {
	public boolean check(FieldBase field, Model model);
}
