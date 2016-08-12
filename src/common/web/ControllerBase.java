package common.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class ControllerBase {

	public String globalSiteName;
	public String siteName;
	public String loginUrl;

	public HttpServletRequest request;
	public HttpServletResponse response;
	public String kindName;
	public String pageName;

	public HttpSession session;


	public ControllerBase(){

	}

	public void init(HttpServletRequest request, HttpServletResponse response,String siteName,String kindName,String pageName,String loginUrl,String globalSiteName){

		this.request=request;
		this.response=response;
		this.siteName=siteName;
		this.kindName=kindName;
		this.pageName=pageName;
		this.loginUrl=loginUrl;
		this.globalSiteName=globalSiteName;

		session=request.getSession();

		this.model=new Model(request,response,session);


	}

	protected boolean checkAuth() throws IOException{
		if(session.getAttribute("id")==null){
			response.sendRedirect(loginUrl);
			return false;
		}
		return true;
	}
	protected void setAuth(String id,String mail,String name){
		session.setAttribute("id", id);
		session.setAttribute("mail", mail);
		session.setAttribute("name", name);
	}
	protected void removeAuth(){
		session.removeAttribute("id");
		session.removeAttribute("mail");
		session.removeAttribute("name");
	}

	protected String getUserId() {
		return session.getAttribute("id").toString();
	}
	protected String getUserIdFillInZero(){
		return common.lib.Util.fillInZero(Integer.valueOf(getUserId()), 6);
	}
	protected String getUserMail() {
		return session.getAttribute("mail").toString();
	}
	protected String getUserName() {
		return session.getAttribute("name").toString();
	}

	protected void setMessage(String message) throws UnsupportedEncodingException{
		model.setMessage(message);
	}

	protected void setConfirmPopup(String message,String yesUrl,String noUrl){
		session.setAttribute("confirm", message);
		if(yesUrl!=null){
			session.setAttribute("confirm_yes", yesUrl);
		}
		if(noUrl!=null){
			session.setAttribute("confirm_no", noUrl);
		}
	}

	protected boolean checkParameter(String paramName){
		return request.getParameterMap().containsKey(paramName);
	}

	protected boolean checkPToken(){
		if(session.getAttribute("_ptoken").equals(request.getParameter("_ptoken"))){
			session.removeAttribute("_ptoken");
			return true;
		}else{
			return false;
		}
	}
	public void run() throws ServletException, IOException {
		if(response.isCommitted())return;
		doBefore();
		if(response.isCommitted())return;
		if(request.getMethod().equals("POST")){
			if(model.checkToken){
				if(!model.checkToken(request)){
					response.sendRedirect(pageName);
					return;
				}
			}
			model.setValue(request);
			if(model.runValidation(request))
			doPost();
		}else{
			doGet();
		}

		doAfter();

		if(response.isCommitted())return;

		request.setAttribute("model", model);
		request.getRequestDispatcher(String.format("/_view/%s/%s/%s.jsp",siteName.toLowerCase(),kindName,pageName)).forward(request, response);
	}
	protected Model model;

	protected abstract void doBefore() throws IOException;

	protected abstract void doGet() throws IOException;

	protected abstract void doPost() throws IOException;

	protected abstract void doAfter() throws IOException;

	protected String UrlEncode(String url) throws UnsupportedEncodingException {
		// TODO 自動生成されたメソッド・スタブ
		return URLEncoder.encode(url, "UTF-8");
	}
}
