package common.field;

import java.io.Serializable;
import java.text.ParseException;

import common.base.FieldBase;
import common.type.DateEx;

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


	@Override
	public Object getValue() {

		if(super.getValue()==null)return null;
		DateEx date=null;
		try {
			date=new DateEx(super.getValue().toString(),"HH:mm");
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
		return date;
	}

	@Override
	public void setValue(Object value) {
		if(value==null){
			setAttribute("value", "");
			return;
		}

		if(value.getClass().getSimpleName().equals(String.class.getSimpleName())){
			try {
				setAttribute("value",new DateEx(value.toString(),"HH:mm").toString("HH:mm"));
			} catch (ParseException e) {
				setAttribute("value", "");
				return;
			}
		}else if(value.getClass().getSimpleName().equals(DateEx.class.getSimpleName())){
			setAttribute("value", ((DateEx)value).toString("HH:mm"));
		}
	}

}
