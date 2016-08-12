package common.field;

import common.base.FieldBase;

public class Util {
	public static String getFieldDisplayName(FieldBase field){
		if(common.lib.Util.getClassName(field).equals("Text")){
			return "テキスト(短)";
		}else if(common.lib.Util.getClassName(field).equals("TextArea")){
			return "テキスト(長)";
		}else if(common.lib.Util.getClassName(field).equals("Number")){
			return "数値";
		}else if(common.lib.Util.getClassName(field).equals("DateTime")){
			return "日時";
		}else if(common.lib.Util.getClassName(field).equals("Date")){
			return "日付";
		}else if(common.lib.Util.getClassName(field).equals("Time")){
			return "時間";
		}else if(common.lib.Util.getClassName(field).equals("Image")){
			return "画像";
		}else if(common.lib.Util.getClassName(field).equals("Select")){
			return "選択";
		}else if(common.lib.Util.getClassName(field).equals("MultiSelect")){
			return "複数選択";
		}else{
			return null;
		}
	}

	public static FieldBase getFieldInstance(String name, String type) {
		if(type.equals("Text")){
			return new Text(name,name);
		}else if(type.equals("TextArea")){
			return new TextArea(name, name);
		}else if(type.equals("Number")){
			return new Number(name,name);
		}else if(type.equals("DateTime")){
			return new DateTime(name,name);
		}else if(type.equals("Date")){
			return new Date(name,name);
		}else if(type.equals("Time")){
			return new Time(name,name);
		}else if(type.equals("Image")){
			return new Image(name,name);
		}else if(type.equals("Select")){
			return new Select(name,name);
		}else if(type.equals("MultiSelect")){
			return new MultiSelect(name,name);
		}else{
			return null;
		}

	}

	public static void setFieldOptionItem(Select type) {
		type.addOptionItem("Text", "テキスト(短)");
		type.addOptionItem("TextArea", "テキスト(長)");
		type.addOptionItem("Number", "数値");
		//type.addOptionItem("DateTime", "日時");
		type.addOptionItem("Date", "日付");
		type.addOptionItem("Time", "時間");
		type.addOptionItem("Image", "画像");
		type.addOptionItem("Select", "選択");
		type.addOptionItem("MultiSelect", "複数選択");
	}
}
