package common.data;

import java.sql.SQLException;

public abstract class TableBase {

	public TableBase() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public abstract DBBase db();
	public abstract String tableName();
	public abstract String[] columns();

	public DataTable Select() throws SQLException{
		DBManager dbm=new DBManager(db());
		return dbm.Select(String.format("select * from %s", tableName()));
	}
}
