package common.tag;

import common.type.KeyValue;

public class TableListLink extends ATag {

	public TableListLink(String url, String display) {
		super(url, display);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public TableListLink(String url, String display,KeyValue...attributes) {
		super(url, display,attributes);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	public String colName;

	public int colIndex=-1;

}
