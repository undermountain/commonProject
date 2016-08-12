package common.data;

import java.sql.SQLException;

public class DBInfoBase {
	public StringBuilder sql;
	public DBBase db;

	public DBInfoBase(DBBase db,StringBuilder sql){
		this.db=db;
		this.sql=sql;
	}

	public DataTable execute() throws SQLException{
		DBManager dbm=new DBManager(db);
		return dbm.Select(sql.toString());
	}
}
