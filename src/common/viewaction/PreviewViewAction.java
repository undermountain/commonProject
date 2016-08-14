package common.viewaction;

import java.io.Serializable;

public class PreviewViewAction implements Serializable {
	private static final long serialVersionUID = 1L;
	public PreviewViewAction() {

	}

	/*public static String Preview(ActionBase action){
		String path=Path.getPreviewViewAction(action.getClass().getName());
		StringBuilder sb=new StringBuilder();
		try{
			File file=new File(path);
		  BufferedReader br = new BufferedReader(new FileReader(file));

		  String str;
		  while((str = br.readLine()) != null){
		    sb.append(str);
		  }

		  br.close();
		}catch(FileNotFoundException e){
		  System.out.println(e);
		}catch(IOException e){
		  System.out.println(e);
		}
		return sb.toString();
	}*/

}
