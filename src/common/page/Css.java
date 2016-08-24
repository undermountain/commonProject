package common.page;

public class Css {

	public static final String FONTFAMIRY_G="ゴシック体";
	public static final String FONTFAMIRY_M="明朝体";
	public static final String[] fontFamiryList=new String[]{FONTFAMIRY_G,FONTFAMIRY_M};

	public Css() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public String fontFamiry(String name){
		if(name.equals(FONTFAMIRY_G)){
			return "font-family:Avenir , \"Open Sans\" , \"Helvetica Neue\" , Helvetica , Arial , Verdana , Roboto , \"游ゴシック\" , \"Yu Gothic\" , \"游ゴシック体\" , \"YuGothic\" , \"ヒラギノ角ゴ Pro W3\" , \"Hiragino Kaku Gothic Pro\" , \"Meiryo UI\" , \"メイリオ\" , Meiryo , \"ＭＳ Ｐゴシック\" , \"MS PGothic\" , sans-serif;";
		}else if(name.equals(FONTFAMIRY_M)){
			return "font-family:\"Roboto Slab\" , Garamond , \"Times New Roman\" , \"游明朝\" , \"Yu Mincho\" , \"游明朝体\" , \"YuMincho\" , \"ヒラギノ明朝 Pro W3\" , \"Hiragino Mincho Pro\" , \"HiraMinProN-W3\" , \"HGS明朝E\" , \"ＭＳ Ｐ明朝\" , \"MS PMincho\" , serif;";
		}else{
			return "";
		}
	}
}
