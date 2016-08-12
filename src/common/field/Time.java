package common.field;

import java.io.Serializable;

import common.base.FieldBase;

public class Time extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Time(String id,String displayName) {
		super("input", id);
		this.displayName=displayName;
		setAttribute("type", "time");
		setImeModeOff();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void setImeModeOff(){
		setStyle("ime-mode", "mode:disabled");
	}

}
