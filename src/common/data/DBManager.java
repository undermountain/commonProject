package common.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements Serializable{
	/**
	 * org.postgresql.Driver
	 */
	public static final String PostgreSqlDriver="org.postgresql.Driver";
	public Connection Connection;

	public StringBuilder SqlBuilder;
	public List<Object> ParamList;

	public DBManager(String dbName){
		setDBManager("127.0.0.1", dbName, "postgres", "asdfasdf");
	}
	public DBManager(String server,String dbname,String user,String password){
		setDBManager(server, dbname, user, password);
	}
	public DBManager(DBBase db){
		setDBManager(db.address(), db.dbName(), db.user(), db.password());
	}

	public void setDBManager(String server,String dbname,String user,String password){
		SqlBuilder=new StringBuilder();
		ParamList=new ArrayList<Object>();

        String url = "jdbc:postgresql://" + server + ":5432/" + dbname;

        try {
			Class.forName (PostgreSqlDriver);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        // データベースとの接続
        try {
        	Connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public DataTable Select(String sql) throws SQLException{
		try {
			Statement statement=Connection.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();

			int columnCount=rsmd.getColumnCount();
			String[] colNames=new String[columnCount];
			for(int i=0;i<columnCount;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}

			DataTable dt=new DataTable(colNames);

			dt.rows=new ArrayList<Object[]>();
			//dt.TableName=rsmd.getTableName(0);

			while(rs.next()){
				Object[] row=new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					row[i]= rs.getObject(i+1);
				}
				dt.rows.add(row);
			}
			return dt;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}

	public DataTable Select() throws SQLException{
		if(ParamList==null || ParamList.size()==0){
			return Select(SqlBuilder.toString());
		}
		return select(SqlBuilder.toString(),ParamList);
	}

	public DataTable select(String sql,Object... param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);

			for(int i=1;i<=param.length;i++){
				preStatement.setObject(i, param[i-1]);
			}
			ResultSet rs=preStatement.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();

			int columnCount=rsmd.getColumnCount();
			String[] colNames=new String[columnCount];
			for(int i=0;i<columnCount;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}

			DataTable dt=new DataTable(colNames);
			dt.rows=new ArrayList<Object[]>();

			while(rs.next()){
				Object[] row=new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					row[i]= rs.getObject(i+1);
				}
				dt.rows.add(row);
			}
			return dt;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}

	public DataTable select(String sql,List<Object> param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);
			int size=param.size();
			for(int i=1;i<=size;i++){
				preStatement.setObject(i, param.get(i-1));
			}
			ResultSet rs=preStatement.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();

			int columnCount=rsmd.getColumnCount();
			String[] colNames=new String[columnCount];
			for(int i=0;i<columnCount;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}

			DataTable dt=new DataTable(colNames);

			dt.rows=new ArrayList<Object[]>();

			while(rs.next()){
				Object[] row=new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					row[i]= rs.getObject(i+1);
				}
				dt.rows.add(row);
			}
			return dt;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}

	public int execute(String sql,List<Object> param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);
			int size=param.size();
			for(int i=1;i<=size;i++){
				preStatement.setObject(i, param.get(i-1));
			}
			int rs=preStatement.executeUpdate();

			return rs;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}

	public int execute(String sql,Object... param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);

			for(int i=1;i<=param.length;i++){
				preStatement.setObject(i, param[i-1]);
			}
			int rs=preStatement.executeUpdate();

			return rs;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}
	public int execute(String sql) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);


			int rs=preStatement.executeUpdate();

			return rs;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}


	public int executeReadInt(String sql,Object... param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);

			for(int i=1;i<=param.length;i++){
				preStatement.setObject(i, param[i-1]);
			}
			ResultSet rs=preStatement.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}


	public String executeReadString(String sql,Object... param) throws SQLException{
		try {
			PreparedStatement preStatement=Connection.prepareStatement(sql);

			for(int i=1;i<=param.length;i++){
				preStatement.setObject(i, param[i-1]);
			}
			ResultSet rs=preStatement.executeQuery();

			if(rs.next()){
				return rs.getString(1);
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
	}
	public void close() {
		// TODO 自動生成されたメソッド・スタブ
		try {
			this.Connection.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


}
