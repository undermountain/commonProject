package common.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.ActionBase;

public class FieldTable extends ActionBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public FieldTable(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	@Override
	public void trueAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap)
			throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void falseAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
