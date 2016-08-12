package common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class Util {

	/**
	* HTMLエンコード処理。
	*   &,",<,>の置換
	**/
	public static String htmlEncode(String strIn){
		return htmlEncode(strIn,true);
	}
	public static String htmlEncode (String strIn,boolean nbsp) {

		/** HTMLエンコードが必要な文字 **/
		final char[] htmlEncChar = {'&', '"', '<', '>',' '};
		/** HTMLエンコードした文字列 **/
		final String[] htmlEncStr = {"&amp;", "&quot;", "&lt;", "&gt;","&nbsp;"};

		if (strIn == null) {
			return(null);
		}

		// HTMLエンコード処理
		StringBuffer strOut = new StringBuffer(strIn);

		int len=htmlEncChar.length+(nbsp ? 0:-1);

		// エンコードが必要な文字を順番に処理
		for (int i = 0; i < len; i++) {
			// エンコードが必要な文字の検索
			int idx = strOut.toString().indexOf(htmlEncChar[i]);

			while (idx != -1) {
				// エンコードが必要な文字の置換
				strOut.setCharAt(idx, htmlEncStr[i].charAt(0));
				strOut.insert(idx + 1, htmlEncStr[i].substring(1));

				// 次のエンコードが必要な文字の検索
				idx = idx + htmlEncStr[i].length();
				idx = strOut.toString().indexOf(htmlEncChar[i], idx);
			}
		}
		return(strOut.toString());
	}

	public static String urlEncode(String url) throws UnsupportedEncodingException{
		return URLEncoder.encode(url,"UTF-8");
	}

	public static String setToken(HttpServletRequest request){
		String token=common.lib.Util.createToken();
		request.getSession().setAttribute("_token", token);
		return token;
	}
	public static boolean checkToken(HttpServletRequest request){
		String paramToken=request.getParameter("_token");
		String sessionToken=request.getSession().getAttribute("_token").toString();
		return paramToken.equals(sessionToken);
	}
	public static String getDomain(HttpServletRequest request){
		StringBuilder sb=new StringBuilder();
		sb.append(request.getServerName());
		if(request.getServerPort()!=80){
			sb.append(":");
			sb.append(request.getServerPort());
		}
		return sb.toString();
	}

	public static String isActive(HttpServletRequest request,String url){
		if(request.getRequestURI().toLowerCase().indexOf(url)>-1){
			return "active";
		}else{
			return "";
		}
	}
}
