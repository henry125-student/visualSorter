package main;

class Q<T>{
	public T val; 
	protected Q(T v) {
		val = v;
	}
	public Q<T> next;
}
public class Queue<T> {
	private Q<T> first;
	private Q<T> last;
	
	public Queue() {}
	
	public void add(T val) {
		Q<T> pack = new Q<T>(val);
		if (last != null) {
			last.next = pack;
		} else {
			first = pack;
		}
		last = pack;
	}
	
	public T take() {
		T out = first.val;
		first = first.next;
		if (first == null) {
			last = null;
		}
		return out;
	}
	
	public T getFirst() {
		return first.val;
	}
	
	public T getLast() {
		return last.val;
	}
	
	public void clear() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}
