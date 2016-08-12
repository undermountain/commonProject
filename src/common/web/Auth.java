package common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Auth {
	public static String headerAuthDisp(HttpServletRequest request,String globalSiteName,String loginUrl,String userSettingUrl,String logoffUrl){
		HttpSession session=request.getSession();
		if(session.getAttribute(globalSiteName)==null){
			return String.format("<li><a href='%s'>ログイン</a></li>", loginUrl);
		}
		return String.format("<li><a href='%s'>%s</a></li><li><a href='%s'>ログオフ</a></li>", userSettingUrl,session.getAttribute(globalSiteName),logoffUrl);
	}
}
