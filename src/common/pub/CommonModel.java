package common.pub;

import java.io.Serializable;
import java.util.List;

public class CommonModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public CommonModel() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String fixedHeader;
	public String fixedFooter;
	public String fixedLeft;
	public String fixedRight;

	public String header;
	public String footer;
	public String left;
	public String right;

	public int fixedHeaderHeight;
	public int fixedFooterHeight;
	public int fixedLeftWidth;
	public int fixedRightWidth;

	public List<String> styleList;

}
