package common.tag;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import common.data.DataTable;
import common.data.DataTableInfo;
import common.lib.Util;
import common.web.Elementer;

public class TableList extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;

	public DataTable dataTable;
	public boolean search=false;

	public List<ColumnInfo> columnInfos;
	public boolean paging=false;
	public Integer scrollY=null;

	public List<TableListLink> addLinkList;
	public void addAddLinkList(TableListLink a){
		if(addLinkList==null)addLinkList=new ArrayList<TableListLink>();
		addLinkList.add(a);
	}


	public void setBorder(int border){
		this.setAttribute("border", String.valueOf(border));
	}

	public TableList(DataTableInfo dti){
		super("table");
		this.dataTable=dti.dataTable;
		this.search=dti.doSearch();
		this.columnInfos=dti.columnInfos;
		this.paging=dti.paging;
	}

	public TableList(DataTable dataTable){
		super("table");
		this.dataTable=dataTable;
	}
	public TableList(DataTable dataTable,boolean search){
		super("table");
		this.dataTable=dataTable;
		this.search=search;
	}


	public void init(){

		Elementer thead=new Elementer("thead");

		int rowsize=dataTable.rows.size();
		Elementer headtr=new Elementer("tr");
		for(int ii=0;ii<dataTable.columns.length;ii++){
			Elementer th=new Elementer("th");
			th.inner=dataTable.columns[ii];
			headtr.addChild(th);
		}

		if(addLinkList!=null){
			Elementer td=new Elementer("th");
			int size=addLinkList.size();
			for(int i1=0;i1<size;i1++){
				headtr.addChild(td);
			}
		}
		thead.addChild(headtr);
		this.addChild(thead);

		Elementer tbody=new Elementer("tbody");
		for(int i=0;i<rowsize;i++){

			Elementer tr=new Elementer("tr");

			for(int ii=0;ii<dataTable.columns.length;ii++){

				Elementer td=new Elementer("td");

					if(columnInfos==null){
						if(dataTable.get(i, ii)!=null){
							String val=dataTable.get(i, ii).toString();
							td.inner=common.web.Util.htmlEncode(val).replaceAll("\r\n", "<br>");
						}
					}else{
						if(columnInfos.get(ii)==null){
							if(dataTable.get(i, ii)!=null){
								String val=dataTable.get(i, ii).toString();
								td.inner=common.web.Util.htmlEncode(val).replaceAll("\r\n", "<br>");
							}
						}else{
							td.inner=columnInfos.get(ii).getDisplay(dataTable.get(i, ii));
						}
					}


				tr.addChild(td);

			}

			if(addLinkList!=null){

				int size=addLinkList.size();
				for(int i1=0;i1<size;i1++){
					Elementer td=new Elementer("td");
					if(addLinkList.get(i1).colIndex>=0){
						try {
							addLinkList.get(i1).urlParamList.get(0).value=URLEncoder.encode(dataTable.get(i, addLinkList.get(i1).colIndex).toString(),"utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
							addLinkList.get(i1).urlParamList.get(0).value=dataTable.get(i, addLinkList.get(i1).colIndex).toString();
						}

					}else if(addLinkList.get(i1).colName!=null){
						try {
							addLinkList.get(i1).urlParamList.get(0).value=URLEncoder.encode(dataTable.get(i, addLinkList.get(i1).colName).toString(),"utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
							addLinkList.get(i1).urlParamList.get(0).value=dataTable.get(i, addLinkList.get(i1).colName).toString();
						}
					}else{
						addLinkList.get(i1).urlParamList.get(0).value=i;
					}

					td.inner=addLinkList.get(i1).toHtml();
					tr.addChild(td);
				}
			}


			tbody.addChild(tr);
		}
		this.addChild(tbody);
	}

	public void setFormat(int colIndex,String format){
		if(columnInfos==null)columnInfos=Util.newList(colIndex+1, ColumnInfo.class);
		if(columnInfos.get(colIndex)==null)columnInfos.set(colIndex,new ColumnInfo());
		columnInfos.get(colIndex).format=format;
	}
	public void setFormat(String colName,String format){
		for(int i=0;i<dataTable.columns.length;i++){
			if(dataTable.columns[i].equals(colName)){
				setFormat(i,format);
				return;
			}
		}
	}

	public void setRaw(int colIndex,boolean flag){
		if(columnInfos==null)columnInfos=common.lib.Util.newList(colIndex+1, ColumnInfo.class);
		if(columnInfos.get(colIndex)==null)columnInfos.set(colIndex, new ColumnInfo());
		columnInfos.get(colIndex).raw=flag;
	}
	public void setRaw(String colName,boolean flag){
		for(int i=0;i<dataTable.columns.length;i++){
			if(dataTable.columns[i].equals(colName)){
				setRaw(i,flag);
				return;
			}
		}
	}


	@Override
	public void toHtml(StringBuilder sb) {
		init();

		super.toHtml(sb);

		if(search)search(sb);
	}


	private void search(StringBuilder sb) {
		sb.append("<link href='/um/contents/DataTables/css/jquery.dataTables.min.css' rel='stylesheet'/>");
		sb.append("<script src='/um/scripts/DataTables/jquery.dataTables.min.js'></script>");
		sb.append("<script type='text/javascript'>");
		sb.append(String.format("$('#%s').dataTable({", getId()));
		sb.append(String.format("searching:%s,paging:%s,", search,paging));

		if(scrollY!=null){
			sb.append(String.format("scrollY:%s,",scrollY));
		}
		if(columnInfos!=null){
			sb.append("\"columnDefs\":[");

			StringBuilder sb2=new StringBuilder();
			for(int i=0;i<dataTable.columns.length;i++){

				if(columnInfos.get(i)!=null){
					if(sb2.length()>0)sb2.append(",");
					sb2.append(String.format("{ \"targets\": %s,\"searchable\": %s, \"orderable\": %s,\"visible\": %s }"
							, i,columnInfos.get(i).searchable,columnInfos.get(i).orderable,columnInfos.get(i).visible));

				}
			}
			sb2.append("]");
			sb.append(sb2.toString());
		}

		sb.append("});");
		sb.append("</script>");
	}


}
