package common.field;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;

import common.base.FieldBase;

public class Image extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Integer maxByte;

	public Part file;
	public String filename;

	public List<String> okExtension;

	public String extension;

	public String base64String;

	public byte[] data;

	public Image(String id, String displayName) {
		super("input", id, displayName);
		this.setAttribute("type", "file");
		okExtension=new ArrayList<String>();
		okExtension.add("gif");
		okExtension.add("jpg");
		okExtension.add("jpeg");
		okExtension.add("png");
	}

	@Override
	public boolean setValueByRequest(HttpServletRequest request) {
		try{
			file=request.getPart(getId());

			String contentDisposition = file.getHeader("content-disposition");
			int filenamePosition = contentDisposition.indexOf("filename=")+9;
			filename = contentDisposition.substring(filenamePosition);

			String contentType=file.getContentType();
			if(!contentType.startsWith("image"))return false;

			extension=contentType.substring(6);

			if(maxByte!=null){
				if(file.getSize()>maxByte)valid=false;
				return false;
			}

			if(okExtension!=null){

				int size=okExtension.size();
				boolean ok=false;
				for(int i=0;i<size;i++){
					if(filename.endsWith(okExtension.get(i)+"\"")){
						ok=true;
						break;
					}
				}
				if(!ok){
					valid=false;
					return false;
				}


			}
		}catch(Exception ex){
			valid=false;
		}


		try {
			data = common.lib.Util.readAllByteOfInputStream(file.getInputStream());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public String getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return Base64.encodeBase64String(data);
	}

	public String getDisplay(){
		return "<img src='data:image/"+extension+";base64,"+getValue()+"'/>";
	}



}
