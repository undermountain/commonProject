package common.web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.consts.Path;

/**
 * Servlet implementation class Servlet
 */


public abstract class ServletBase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public abstract String globalSiteName();
	protected abstract String siteName();
	protected abstract String defaultKindName();
	protected abstract String defaultPageName();

	protected abstract String loginUrl();

	protected abstract String defaultUseHeader();

	protected abstract boolean authCheck();

	private static final String encode="UTF-8";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);

		String kindName="";
		String pageName="";
		try{
			HttpSession session=request.getSession();
			if(authCheck()){
				if(session.getAttribute("id")==null){
					response.sendRedirect(loginUrl()+"?to="+URLEncoder.encode(request.getRequestURI()+"?"+request.getQueryString(), encode) );
				}
			}
			String[] requestURI=request.getPathInfo().split("/");

			if(requestURI.length>1){
				//kindName=requestURI[3].toLowerCase();
				kindName=requestURI[1].toLowerCase();
			}else{
				kindName=defaultKindName();
			}
			if(requestURI.length>2){
				pageName=fiastUpper(requestURI[2]);
			}else{
				pageName=defaultPageName();
			}


			session.setAttribute("_header", defaultUseHeader());


			session.setAttribute("_sitename", siteName());
			session.setAttribute("_globalsitename", globalSiteName());

			Class<?> _class;
			ControllerBase controller = null;
			try {
				_class = Class.forName(String.format("%s.%s.%sCR",siteName().toLowerCase(), kindName,pageName));
				controller=(ControllerBase)_class.newInstance();
				controller.init(request, response,siteName(), kindName, pageName,loginUrl(),globalSiteName());


			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new IOException("ClassNotFoundException:"+e.getMessage());
			} catch (InstantiationException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new IOException("InstantiationException:"+e.getMessage());
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new IOException("IllegalAccessException:"+e.getMessage());
			}


			controller.run();

		}catch(Exception ex){
			String strdate=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			File file=new File(Path.ROOT+siteName()+File.separator+"_error"+File.separator+kindName+"."+pageName+"_"+strdate+".log");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(ex.toString());
			fileWriter.close();
			throw ex;
		}
	}

	private String fiastUpper(String str){
		return String.format("%s%s", str.substring(0,1).toUpperCase(),str.substring(1).toLowerCase());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
