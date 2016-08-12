package common.type;

import java.io.Serializable;

public class KeyValueEx<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public Object key;
	public T value;

	public KeyValueEx(Object key2, T value) {
		this.key=key2;
		this.value=value;
	}


	public T getValue(){
		return value;
	}
}
