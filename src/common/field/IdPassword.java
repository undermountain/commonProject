package common.field;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import common.base.FieldBase;
import common.consts.EDir;
import common.consts.Path;
import common.io.PropertiesLib;

public class IdPassword extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public int idmaxlength;
	public int idminlength;
	public int passwordminlength;
	public int passwordmaxlength;

	public int nameWidth;
	public int inputWidth;

	public String idName;
	public String passwordName;

	public String idId;
	public String passwordId;

	public String siteName;
	public boolean isPub;

	public IdPassword(String id, String idName,String passwordName) {
		super("table", id);
	}

	@Override
	public void toHtml(StringBuilder sb) {

		sb.append("<table>");

		sb.append("<tr><th");
		if(nameWidth!=0){
			sb.append(" style='width:");
			sb.append(nameWidth);
			sb.append("px'");
		}
		sb.append(">");
		sb.append(idName);
		sb.append("</th><td");
		if(inputWidth!=0){
			sb.append(" style='width:");
			sb.append(inputWidth);
			sb.append("px'");
		}
		sb.append(">");
		sb.append("<input type='text' name='");
		sb.append(getId());
		sb.append("id' id='");
		sb.append(getId());
		sb.append("id' ");

		if(idId!=null){
			sb.append("value='");
			sb.append(idId);
			sb.append("' ");
		}
		if(idmaxlength!=0){
			sb.append("maxlength='");
			sb.append(idmaxlength);
			sb.append("' ");
		}
		if(idminlength!=0){
			sb.append("minlength='");
			sb.append(idminlength);
			sb.append("' ");
		}

		sb.append("required='required' ");


		sb.append("pattern='");
		sb.append("[a-zA-Z0-9!#$%&'*+-/=?^_`.{|}~@]");
		sb.append("' ");


		sb.append("style='ime-mode:disabled;'");

		sb.append("/></td></tr><tr><th>");
		sb.append(passwordName);
		sb.append("</th><td>");

		sb.append("<input type='password' name='");
		sb.append(getId());
		sb.append("password' id='");
		sb.append(getId());
		sb.append("password' ");


		if(passwordmaxlength!=0){
			sb.append("maxlength='");
			sb.append(passwordmaxlength);
			sb.append("' ");
		}
		if(passwordminlength!=0){
			sb.append("minlength='");
			sb.append(passwordminlength);
			sb.append("' ");
		}

		sb.append("required='required' ");


		sb.append("pattern='");
		sb.append("[a-zA-Z0-9!#$%&'*+-/=?^_`.{|}~@]");
		sb.append("' ");


		sb.append("style='ime-mode:disabled;'");

		sb.append("/>");
		sb.append("</td></tr></table>");

	}

	public boolean idpassCheck(HttpServletRequest request) throws IOException{
		return idpassCheck(request,true);
	}
	public boolean idpassCheck(HttpServletRequest request,boolean pub) throws IOException{
		String path=pub ? Path.getSavePath(siteName, EDir.auth, "login"):Path.getSavePath(idId, EDir.auth_dev, "login");

		Properties properties=PropertiesLib.getProperties(path);

		return properties.getProperty(idId)==passwordId;
	}

	@Override
	public boolean setValue(HttpServletRequest request) {
		idId=request.getParameter(getId()+"id");
		passwordId=request.getParameter(getId()+"password");
		siteName=request.getParameter("site");
		return idId!=null & passwordId!=null & siteName!=null;
	}


}
