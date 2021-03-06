package common.data;

public class SelectDBInfo2 extends DBInfoBase{



	public SelectDBInfo2(DBBase db, StringBuilder sql) {
		super(db, sql);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public SelectDBInfo3 where(String where){
		sql.append(" where ");
		sql.append(where);
		SelectDBInfo3 dbinfo3=new SelectDBInfo3(db,sql);
		return dbinfo3;
	}

	public SelectDBInfo4 groupby(String... columns){
		sql.append(" group by ");
		for(int i=0;i<columns.length;i++){
			if(i!=0)sql.append(",");
			sql.append(columns[i]);
		}
		SelectDBInfo4 dbinfo=new SelectDBInfo4(db,sql);
		return dbinfo;
	}
	public SelectDBInfo5 orderby(String... columns){
		sql.append(" order by ");
		for(int i=0;i<columns.length;i++){
			if(i!=0)sql.append(",");
			sql.append(columns[i]);
		}
		SelectDBInfo5 dbinfo=new SelectDBInfo5(db,sql);
		return dbinfo;
	}
}
