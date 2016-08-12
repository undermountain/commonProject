package common.data;

public class SelectDBInfo1 {

	public SelectDBInfo1(String[] columns) {

	}
	public SelectDBInfo1(DBBase db, String[] columns) {
		this.db=db;
		sql=new StringBuilder();
		sql.append("select ");
		if(columns.length==0){
			sql.append("*");
		}else{
			for(int i=0;i<columns.length;i++){
				if(i>0)sql.append(",");
				sql.append(columns[i]);
			}
		}
	}
	public StringBuilder sql;
	public DBBase db;

	public SelectDBInfo2 from(String tableName){
		sql.append(" from ");
		sql.append(tableName);
		SelectDBInfo2 dbinfo2=new SelectDBInfo2(db,sql);
		return dbinfo2;
	}
}
