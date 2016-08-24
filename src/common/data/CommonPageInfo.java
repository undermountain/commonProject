package common.data;

import java.io.Serializable;
import java.util.Map;

public class CommonPageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	public CommonPageInfo() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String siteName;
	
	public String TemplateName;
	
	public String defaultFontfamiry;
	
	public String defaultFontSize;
	
	public String defaultFontColor;
	
	public String defaultHeaderBackcolor;
	
	public String defaultTopPage;
	
	public Map<Object,Object> data;
}
