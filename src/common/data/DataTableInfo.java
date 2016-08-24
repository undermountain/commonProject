package common.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.base.FieldBase;
import common.tag.ColumnInfo;

public class DataTableInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public DataTableInfo(){
		dataTable=new DataTable();
	}

	public String name;

	public List<FieldBase> fieldList;
	public List<ColumnInfo> columnInfos;
	public String[] formats;
	public boolean paging=false;

	public boolean display=true;

	public DataTable dataTable;

	public void addField(FieldBase field,ColumnInfo columnInfo){
		if(fieldList==null)fieldList=new ArrayList<FieldBase>();
		fieldList.add(field);
		if(dataTable.columns==null){
			dataTable.columns=new String[]{field.displayName};
		}else{
			String[] tmp=new String[dataTable.columns.length+1];
			System.arraycopy(dataTable.columns, 0, tmp, 0, dataTable.columns.length);
			tmp[dataTable.columns.length]=field.displayName;
			dataTable.columns=tmp;
		}
		if(columnInfos==null){
			columnInfos=new ArrayList<ColumnInfo>();;
		}
		columnInfos.add(columnInfo);


		//現データに追加列のデータを追加
        for(int i=0;i<dataTable.rows.size();i++){
        	dataTable.rows.set(i, (Object[]) common.lib.Util.addObjectToArray(dataTable.rows.get(i), new Object[]{null}));
        }
	}
	public void addColumn(String... colNames){
		if(dataTable.columns==null){
			dataTable.columns=colNames;
		}else{
			String[] tmp=new String[dataTable.columns.length+colNames.length];
			System.arraycopy(dataTable.columns, 0, tmp, 0, dataTable.columns.length);
			for(int i=0;i<colNames.length;i++){
				tmp[dataTable.columns.length+i]=colNames[i];
			}

			dataTable.columns=tmp;
		}
		if(columnInfos==null){
			columnInfos=new ArrayList<ColumnInfo>();;
		}

		for(int i=0;i<colNames.length;i++){
			columnInfos.add(new ColumnInfo());
		}

		Object[] addobj=new Object[colNames.length];
		//現データに追加列のデータを追加
        for(int i=0;i<dataTable.rows.size();i++){
        	dataTable.rows.set(i, (Object[]) common.lib.Util.addObjectToArray(dataTable.rows.get(i), addobj));
        }
	}
	public void editField(String displayName,FieldBase field, ColumnInfo columnInfo){

        int size=this.fieldList.size();

        for(int i=0;i<size;i++){
        	if(this.fieldList.get(i).displayName.equals(displayName)){
        		this.fieldList.set(i,field);
        		dataTable.columns[i]=field.displayName;
        		columnInfos.set(i, columnInfo);
        		break;
        	}
        }
	}

	private ColumnInfo newColumnInfo(FieldBase field) {
		ColumnInfo columnInfo=new ColumnInfo();
		if(field.getClass().getName().equals(common.field.Image.class.getName())){

			columnInfo.format="<img src='data:image;base64,%s'/>";
			columnInfo.raw=true;
		}
		return columnInfo;
	}

	public void removeField(String name){
		int size=fieldList.size();
		int delIndex=0;

		for(int i=0;i<size;i++){
			if(fieldList.get(i).displayName.equals(name)){
				fieldList.remove(i);
				delIndex=i;
				break;
			}
		}
		size--;

		String[] newColumns=new String[size];
		for(int i=0;i<size;i++){
			newColumns[i]=fieldList.get(i).displayName;
		}

		dataTable.columns=newColumns;

		columnInfos.remove(delIndex);

		for(int i=0;i<dataTable.rows.size();i++){
			dataTable.rows.set(i, common.lib.Util.removeObjectFromArray(dataTable.rows.get(i),delIndex));
		}
	}

	public boolean doSearch(){
		if(columnInfos==null)return false;

		return columnInfos.stream().filter(s->s.searchable).count()>0;

	}
	public ColumnInfo getColumnInfo(int i) {
		if(columnInfos==null)columnInfos=new ArrayList<ColumnInfo>();
		return columnInfos.get(i);
	}
	public ColumnInfo getColumnInfo(String name) {
		if(columnInfos==null)columnInfos=new ArrayList<ColumnInfo>();

		for(int i=0;i<dataTable.columns.length;i++){
			if(name.equals(dataTable.columns[i])){
				return columnInfos.get(i);
			}
		}
		return null;
	}
	public void setColumnInfo(int i,ColumnInfo columnInfo) {
		if(columnInfos==null)columnInfos=new ArrayList<ColumnInfo>();
		columnInfos.set(i, columnInfo);
	}
}
