package common.type;

import java.io.Serializable;
import java.text.DateFormat;
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
	public DateEx(String date ,String formt) throws ParseException{
		super(convert(date,formt).getTime());
	}
	public DateEx(Object date) throws ParseException{
		super(convert(date.toString()).getTime());
	}
	public static Date convert(String date) throws ParseException{
		String pattern;
		if(date.contains("/")){
			pattern="yyyy/MM/dd HH:mm:ss.SSS".substring(0, date.length());
		}else if(date.contains("-")){
			pattern="yyyy-MM-dd HH:mm:ss.SSS".substring(0, date.length());
		}else{
			pattern="yyyyMMddHHmmssfff".substring(0, date.length());
		}
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}
	public static Date convert(String date,String format) throws ParseException{
		String pattern;

		pattern=format.substring(0, date.length());

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

	public Date toDate() {
		// TODO 自動生成されたメソッド・スタブ
		return this;
	}

	@Override
	public String toString() {
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return df.format(this);
	}

	public String toString(String format) {
		DateFormat df=new SimpleDateFormat(format);
		return df.format(this);
	}

}
