package common.type;

import java.io.Serializable;

public class KeyValue implements Serializable {
	private static final long serialVersionUID = 1L;
	public String key;
	public Object value;

	public KeyValue(String key, Object value) {
		this.key=key;
		this.value=value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(){
		return (T)value;
	}
}
