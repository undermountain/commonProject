package common.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTable implements Serializable {
	private static final long serialVersionUID = 1L;

	public String[] columns;
	public List<Object[]> rows;
	public String tableName;

	public DataTable(String... colNames){
		rows=new ArrayList<Object[]>();
		this.columns=colNames;
	}
	public DataTable(){
		rows=new ArrayList<Object[]>();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(int row,int col){
		return (T)rows.get(row)[col];
	}

	@SuppressWarnings("unchecked")
	public <T> T get(int row,String colName){
		int col=-1;
		for(int i=0;i<columns.length;i++){
			if(columns[i].equals(colName)){
				col=i;
				break;
			}
		}
		return (T)rows.get(row)[col];
	}

	public void set(int row,int col,Object value){
		rows.get(row)[col]=value;
	}

	public void set(int row,String colName,Object value){
		for(int i=0;i<columns.length;i++){
			if(columns[i].equals(colName)){
				rows.get(row)[i]=value;
				break;
			}
		}
	}

	public void addRow(Object... row){
		rows.add(row);
	}

}
