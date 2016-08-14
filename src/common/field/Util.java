package common.field;

import common.base.FieldBase;
import common.base.MultiFieldBase;
import common.data.DataTableInfo;
import common.tag.ColumnInfo;
import common.web.Model;

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

	public static final String DATE_FORMAT1="%1$tY/%1$tm/%1$td";
	public static final String DATE_FORMAT2="%1$tY年%1$tm月%1$td日";

	public static final String TIME_FORMAT1="%1$tH:%1$tM";
	public static final String TIME_FORMAT2="%1$tH時%1$tM分";

	public static void setFieldInfoInputs(String multi, String type, Model model, FieldBase field,ColumnInfo columnInfo) {

		Text prefix=new Text("prefix","語頭");
		prefix.description="　データを表示時に先頭に追加される文字列です。";
		model.addField(prefix);
		Text suffix=new Text("suffix","語尾");
		suffix.description="　データを表示時に語尾に追加される文字列です。";
		model.addField(suffix);

		if(columnInfo!=null){
			prefix.setValue(columnInfo.prefix);
			suffix.setValue(columnInfo.suffix);
		}

		if(type.equals("Text")){
			//model.addField(new Text("pattern","正規表現"));
			Number maxdisplay=new Number("maxdisplay","テーブル表示上限文字数");
			model.addField(maxdisplay);

			if(columnInfo!=null){
				maxdisplay.setValue(columnInfo.displayMaxLength);
			}
		}else if(type.equals("TextArea")){
			Number maxdisplay=new Number("maxdisplay","テーブル表示上限文字数");
			model.addField(maxdisplay);

			if(columnInfo!=null){
				maxdisplay.setValue(columnInfo.displayMaxLength);
			}
		}else if(type.equals("Number")){
			Number decimal=new Number("decimal","小数点以下桁数");
			model.addField(decimal);

			if(columnInfo!=null){
				//decimal.setValue(columnInfo.decimalLength);
			}

		}else if(type.equals("DateTime")){

		}else if(type.equals("Date")){
			Select dateformat=new Select("dateformat","フォーマット");
			dateformat.addOptionItem("0", "YYYY/MM/DD");
			dateformat.addOptionItem("1", "YYYY年MM月DD");
			model.addField(dateformat);

			if(columnInfo!=null){
				if(columnInfo.format.equals(DATE_FORMAT1)){
					dateformat.setValue("0");
				}else if(columnInfo.format.equals(DATE_FORMAT2)){
					dateformat.setValue("1");
				}
			}

		}else if(type.equals("Time")){
			Select dateformat=new Select("dateformat","フォーマット");
			dateformat.addOptionItem("0", "HH:MM");
			dateformat.addOptionItem("1", "HH時MM分");
			model.addField(dateformat);

			if(columnInfo!=null){
				if(columnInfo.format.equals(TIME_FORMAT1)){
					dateformat.setValue("0");
				}else if(columnInfo.format.equals(TIME_FORMAT2)){
					dateformat.setValue("1");
				}
			}

		}else if(type.equals("Image")){
			Number maxheight=new Number("maxheight","最大縦幅");
			model.addField(maxheight);

			Number maxwidth=new Number("maxwidth","最大横幅");
			model.addField(maxwidth);

			if(columnInfo!=null){
				maxheight.setValue(columnInfo.maxheight);
				maxwidth.setValue(columnInfo.maxwidth);
			}
		}else if(type.equals("Select")){
			Text listitem=new Text("listitem", "選択要素");
			listitem.description="　選択できる要素を設定します。";
			MultiFieldBase mField=new MultiFieldBase(listitem);
			model.addField(mField);

			if(field!=null){
				String[] items=((Select)field).getOptionItems();
				for(Object o:items){

					mField.valueList.add(o.toString());
				}
			}
		}else if(type.equals("MultiSelect")){
			Text checkitem=new Text("checkitem", "選択要素");
			checkitem.description="　選択できる要素を設定します。";
			MultiFieldBase mField=new MultiFieldBase(checkitem);
			model.addField(mField);

			if(field!=null){
				String[] items=((MultiSelect)field).getOptionItems();
				for(Object o:items){

					mField.valueList.add(o.toString());
				}
			}
		}else{

		}

	}

	public static void editFieldInfoInputs(String array, String type, Model model, DataTableInfo dti,String fieldName) {
		// TODO 自動生成されたメソッド・スタブ
        FieldBase field=common.field.Util.getFieldInstance(model.getField("name").getStrValue(),model.getField("type").getStrValue());

        ColumnInfo columnInfo=new ColumnInfo();



        columnInfo.prefix= model.getField("prefix").getStrValue();

        columnInfo.suffix=model.getField("suffix").getStrValue();

		if(type.equals("Text")){
			//model.getField(new Text("pattern","正規表現"));
			if(common.lib.Check.isInteger(model.getField("maxdisplay").getStrValue()))
				columnInfo.displayMaxLength= Integer.valueOf(model.getField("maxdisplay").getStrValue());
		}else if(type.equals("TextArea")){
			if(common.lib.Check.isInteger(model.getField("maxdisplay").getStrValue()))
				columnInfo.displayMaxLength= Integer.valueOf(model.getField("maxdisplay").getStrValue());
		}else if(type.equals("Number")){
			model.getField("decimal");
		}else if(type.equals("DateTime")){

		}else if(type.equals("Date")){

			if(model.getField("dateformat").getStrValue().equals("0")){
				columnInfo.format=DATE_FORMAT1;
			}else if(model.getField("dateformat").getStrValue().equals("1")){
				columnInfo.format=DATE_FORMAT2;
			}else{
				columnInfo.format="%1$tY-%1$tm-%1$td";
			}

		}else if(type.equals("Time")){

			if(model.getField("dateformat").getStrValue().equals("0")){
				columnInfo.format=TIME_FORMAT1;
			}else if(model.getField("dateformat").getStrValue().equals("1")){
				columnInfo.format=TIME_FORMAT2;
			}else{
				columnInfo.format="%1$tH:%1$tM";
			}


		}else if(type.equals("Image")){
			String style = null;
			if(model.getField("maxheight").getIntValue()!=null){
				style+=String.format("max-height:%s;", model.getField("maxheight").getIntValue());
				columnInfo.maxheight=model.getField("maxheight").getIntValue();
			}

			if(model.getField("maxwidth").getIntValue()!=null){
				style+=String.format("max-width:%s;", model.getField("maxwidth").getIntValue());
				columnInfo.maxwidth=model.getField("maxwidth").getIntValue();
			}



			columnInfo.format="<img src='data:image;base64,%s' style='" + style + "'/>";
			columnInfo.raw=true;

		}else if(type.equals("Select")){
			Object[] objArray=model.getField("listitem").getValueToArray();
			for(Object obj:objArray){
				if(obj!=null && !obj.toString().equals(""))
					((Select)field).addOptionItem(obj.toString(), obj.toString());
			}

		}else if(type.equals("MultiSelect")){

			Object[] objArray=model.getField("checkitem").getValueToArray();
			for(Object obj:objArray){
				if(obj!=null && !obj.toString().equals(""))
					((MultiSelect)field).addOptionItem(obj.toString(), obj.toString());
			}
		}else{

		}

		if(fieldName==null){
			dti.addField(field,columnInfo);
		}else{
			dti.editField(fieldName, field,columnInfo);
		}


	}
}
