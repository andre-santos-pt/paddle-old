package pt.iscte.paddle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMapList<K, V> {

	private Map<K, List<V>> map = new HashMap<>();
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	public List<V> get(K key) {
		return map.get(key);
	}

	public void put(K key, V value) {
		List<V> list = map.get(key);
		if(list == null) {
			list = new ArrayList<>();
			map.put(key, list);
		}
		list.add(value);
	}

	public void remove(K key) {
		map.remove(key);	
	}


	public void clear() {
		map.clear();
	}
}
