package common.type;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEx extends Date implements Serializable {
	private static final long serialVersionUID = 1L;
	public DateEx() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public DateEx(long date) {
		super(date);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public DateEx(String date) throws ParseException{
		super(convert(date).getTime());
	}
	public DateEx(Object date) throws ParseException{
		super(convert(date.toString()).getTime());
	}
	public static Date convert(String date) throws ParseException{
		String pattern;
		if(date.contains("/")){
			pattern="yy/MM/dd HH:mm:ss.SSS".substring(0, date.length());
		}else if(date.contains("-")){
			pattern="yy-MM-dd HH:mm:ss.SSS".substring(0, date.length());
		}else{
			pattern="yyMMddHHmmssfff".substring(0, date.length());
		}
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	public java.sql.Date toSqlDate(){
		return new java.sql.Date(this.getTime());
	}

	public boolean equels(Date date){
		return this.compareTo(date)==0;
	}

	public boolean orMore(Date date){
		return this.compareTo(date)>=0;
	}
	public boolean orLesse(Date date){
		return this.compareTo(date)<=0;
	}
	public boolean more(Date date){
		return this.compareTo(date)>0;
	}
	public boolean less(Date date){
		return this.compareTo(date)<0;
	}
}
