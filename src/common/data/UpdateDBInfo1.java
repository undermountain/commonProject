package common.data;

public class UpdateDBInfo1 extends DBInfoBase {

	public UpdateDBInfo1(DBBase db, StringBuilder sql) {
		super(db, sql);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public UpdateDBInfo3 set(String where){
		sql.append(" where ");
		sql.append(where);
		UpdateDBInfo3 dbinfo=new UpdateDBInfo3(db,sql);
		return dbinfo;
	}

}
