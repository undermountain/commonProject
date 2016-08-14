package common.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KeyList<T> implements Serializable,List<T> {
	private static final long serialVersionUID = 1L;
	public List<KeyValueEx<T>> list;

	public KeyList() {
		list=new ArrayList<KeyValueEx<T>>();
	}

	@Override
	public int size() {
		// TODO 自動生成されたメソッド・スタブ
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return list.contains(o);
	}


	@Override
	public Iterator<T> iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return this.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO 自動生成されたメソッド・スタブ
		return list.stream().map(KeyValueEx::getValue).toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(Object[] a) {
		// TODO 自動生成されたメソッド・スタブ
		return (T[]) list.toArray(a);
	}

	@Override
	public boolean add(Object e) {
		// TODO 自動生成されたメソッド・スタブ
		return add("",e);
	}

	@SuppressWarnings("unchecked")
	public boolean add(Object key,Object e){
		return list.add(new KeyValueEx<T>(key,(T)e));
	}

	@Override
	public boolean remove(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO 自動生成されたメソッド・スタブ
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO 自動生成されたメソッド・スタブ
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ
		list.clear();
	}

	@Override
	public T get(int index) {
		// TODO 自動生成されたメソッド・スタブ
		return list.get(index).value;
	}
	public T get(Object key) {
		for(KeyValueEx<T> kv:list){
			if(kv.key.equals(key)){
				return kv.value;
			}
		}
		return list.get(-1).value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object set(int index, Object element) {
		// TODO 自動生成されたメソッド・スタブ
		KeyValueEx<T> kv=list.get(index);
		kv.value=(T) element;
		return list.set(index, kv);
	}
	@SuppressWarnings("unchecked")
	public Object set(String key, Object element) {
		for(KeyValueEx<T> kv:list){
			if(kv.key.equals(key)){
				return kv.value=(T)element;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) {
		// TODO 自動生成されたメソッド・スタブ
		list.add(index, new KeyValueEx<T>("",(T)element));
	}
	@SuppressWarnings("unchecked")
	public void addAfter(String key, Object element) {
		for(int i=0;i<this.size();i++){
			if(list.get(i).key.equals(key)){
				list.add(i+1,new KeyValueEx<T>(key,(T)element));
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void addBefore(String key, Object element) {
		for(int i=0;i<this.size();i++){
			if(list.get(i).key.equals(key)){
				list.add(i,new KeyValueEx<T>(key,(T)element));
			}
		}
	}

	@Override
	public T remove(int index) {
		// TODO 自動生成されたメソッド・スタブ
		T value = list.remove(index).value;
		return value;
	}

	public T remove(String key) {
		for(int i=0;i<this.size();i++){
			if(list.get(i).key.equals(key)){
				return list.remove(i).value;
			}
		}
		return list.remove(-1).value;
	}

	@Override
	public int indexOf(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO 自動生成されたメソッド・スタブ
		return this.listIterator();
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO 自動生成されたメソッド・スタブ
		return this.listIterator(index);
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO 自動生成されたメソッド・スタブ
		return list.subList(fromIndex, toIndex);
	}

}
