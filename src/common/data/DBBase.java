package common.data;

public abstract class DBBase {

	public DBBase() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public abstract String address();
	public abstract String dbName();
	public abstract String user();
	public abstract String password();

	public SelectDBInfo1 select(String...columns){
		SelectDBInfo1 dbinfo1=new SelectDBInfo1(this,columns);
		return dbinfo1;
	}

	public UpdateDBInfo1 update(String tableName){
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE ");
		sb.append(tableName);
		UpdateDBInfo1 dbinfo=new UpdateDBInfo1(this,sb);
		return dbinfo;
	}
}
