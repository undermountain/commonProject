package common.tag;

import java.io.Serializable;

import common.base.FieldBase;
import common.field.Button;
import common.field.Hidden;
import common.field.Submit;
import common.web.Elementer;

public class InputTable extends Elementer implements Serializable {
	private static final long serialVersionUID = 1L;

	public InputTable(String buttonCaption,FieldBase...field) {
		super("div");

		init(field);

		Elementer tr=new Elementer("div");
		tr.addCssClass("submitdiv");

		Submit submit=new Submit(buttonCaption,buttonCaption);
		tr.addChild(submit);


		this.addChild(tr);

	}


	public InputTable(FieldBase...field) {
		super("div");
		init(field);
	}

	private void init(FieldBase...field) {
		for(int i=0;i<field.length;i++){
			if(field[i].getClass().getSimpleName().equals(Hidden.class.getSimpleName())){
				this.addChild(field[i]);
				continue;
			}else if(field[i].getClass().getSimpleName().equals(Button.class.getSimpleName())){
				Elementer tr=new Elementer("div");
				tr.addCssClass("submitdiv");
				tr.addChild(field[i]);
				this.addChild(tr);
			}else{
				field[i].addCssClass("form-control");

				Elementer tr=new Elementer("div");
				tr.addCssClass("form-group");

				Elementer label=new Elementer("label");
				label.addCssClass("control-label");
				label.inner=field[i].displayName;

				tr.addChild(label);
				if(field[i].description!=null){
					Elementer pre=new Elementer("p");
					pre.inner= field[i].description;
					tr.addChild(pre);
				}
				tr.addChild(field[i]);

				tr.addChild(new Elementer("div",field[i].toErrorMessage()));

				this.addChild(tr);
			}

		}
	}

}
