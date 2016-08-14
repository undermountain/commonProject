package common.field;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import common.base.FieldBase;

public class FileUpload extends FieldBase implements Serializable {
	private static final long serialVersionUID = 1L;
	public Integer maxByte;

	public Part file;
	public String filename;

	public List<String> okExtension;

	public String extension;

	public FileUpload(String id, String displayName) {
		super("input", id, displayName);
		this.setAttribute("type", "file");
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
					if(filename.endsWith(okExtension.get(i))){
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
		return true;
	}

	@Override
	public String getValue() {
		// TODO 自動生成されたメソッド・スタブ
		return filename;
	}




}
