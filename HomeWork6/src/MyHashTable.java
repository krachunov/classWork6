import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyHashTable<TKey, TValue> implements
		Enumeration<KeyValue<TKey, TValue>> {

	private List<KeyValue<TKey, TValue>>[] slot;
	public int count;
	public int capacity;
	public final static int initialCapacity = 16;

	@SuppressWarnings("unchecked")
	public MyHashTable(int capacity) {
		this.slot = new LinkedList[capacity];
		setCount(0);
		
	}

	public MyHashTable() {
		this(initialCapacity);
	}

	public List<KeyValue<TKey, TValue>>[] getSlot() {
		return slot;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCapacity() {
		return slot.length;
	}

	@Override
	public boolean hasMoreElements() {
		if (nextElement() != null) {
			return true;
		}
		// TODO
		return false;
	}

	@Override
	public KeyValue<TKey, TValue> nextElement() {
		for (List<KeyValue<TKey, TValue>> element : getSlot()) {
			if (element != null) {
				for (KeyValue<TKey, TValue> keyValue : element) {
					System.out.println("key: " + keyValue.getKey() + " -> "
							+ keyValue.getValue());
					return keyValue;
				}
			}
		}

		return null;
	}

	/**
	 * Add pair key and value if key exist throw exception
	 * 
	 * @param key
	 * @param value
	 */
	public void add(TKey key, TValue value) {
		growIfNeeded();
		int slotNumber = this.findSlotNumber(key);
		if (getSlot()[slotNumber] == null) {
			getSlot()[slotNumber] = new LinkedList<KeyValue<TKey, TValue>>();
		}
		for (KeyValue<TKey, TValue> element : slot[slotNumber]) {
			if (element.key.equals(key)) {
				throw new ArithmeticException("This key exists " + key);
			}
		}
		KeyValue<TKey, TValue> newElement = new KeyValue<TKey, TValue>(key,
				value);
		this.slot[slotNumber].add(newElement);
		setCount(getCount() + 1);
	}

	/**
	 * Add new element, if element exist change his value
	 * 
	 * @param key
	 * @param value
	 */
	public void addOrReplace(TKey key, TValue value) {
		growIfNeeded();
		int slotNumber = this.findSlotNumber(key);
		if (getSlot()[slotNumber] == null) {
			getSlot()[slotNumber] = new LinkedList<KeyValue<TKey, TValue>>();
		}
		for (KeyValue<TKey, TValue> element : slot[slotNumber]) {
			if (element.key.equals(key)) {
				element.setValue(value);
				return;
			}
		}
		KeyValue<TKey, TValue> newElement = new KeyValue<TKey, TValue>(key,
				value);
		this.slot[slotNumber].add(newElement);
		setCount(getCount() + 1);
	}

	private int findSlotNumber(TKey key) {
		int slotNumber = Math.abs((key.hashCode()) % slot.length);
		return slotNumber;
		// TODO how to use getHashCode() from KeyValue class
	}

	private static final double loadFactor = 0.75d;

	@SuppressWarnings({ "unchecked", "unused" })
	/**
	 * 	 Verify whether the array is filled to 75%
	 */
	private void growIfNeeded() {
		if ((double) (getCount() + 1) / getCapacity() > loadFactor) {
			this.grow();
		}
	}

	/**
	 * doubles the size of the array
	 */
	private void grow() {
		MyHashTable<TKey, TValue> newHashTable = new MyHashTable<TKey, TValue>(
				slot.length * 2);
		for (List<KeyValue<TKey, TValue>> element : this.slot) {
			if (element != null) {
				for (KeyValue<TKey, TValue> keyValue : element) {
					newHashTable.addOrReplace(keyValue.getKey(),
							keyValue.getValue());
				}
			}
		}
		this.slot = newHashTable.slot;
		this.count = newHashTable.count;

	}

	/**
	 * 
	 * @param key
	 *            - we are looking for key
	 * @return the pair key-value or null
	 */
	public KeyValue<TKey, TValue> find(TKey key) {
		int slotNumber = this.findSlotNumber(key);
		List<KeyValue<TKey, TValue>> element = getSlot()[slotNumber];
		if (element != null) {
			for (KeyValue<TKey, TValue> keyValue : element) {
				if (keyValue.key.equals(key)) {
					return keyValue;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 *            - searching key
	 * @return - returns the value of the pair key value or throw exception
	 *         their is no element
	 */
	public TValue get(TKey key) {
		if (find(key) == null) {
			throw new NoSuchElementException("There is no such element");
		}
		return find(key).getValue();
	}

	/**
	 * If the key is contained return true
	 * 
	 * @param key
	 * @return true or false
	 */
	public boolean containsKey(TKey key) {
		KeyValue<TKey, TValue> searchingkey = find(key);
		return ((searchingkey != null ? true : false));
	}

	public TValue tryGetValue(TKey key, TValue outValue) {
		if (find(key) != null) {
			return find(key).getValue();
		} else {
			return outValue;
		}
	}
/**
 * 
 * @param key
 * @return Value or null if do not exist
 */
	public TValue tryGetValue(TKey key) {
		return tryGetValue(key, null);
	}

	public boolean remove(TKey key) {
		int slotNumber = findSlotNumber(key);
		List<KeyValue<TKey, TValue>> element = slot[slotNumber];
		if (element != null) {
			for (KeyValue<TKey, TValue> keyValue : element) {
				if (keyValue.getKey().equals(key)) {
					element.remove(keyValue);
					this.count--;
					return true;
				}
			}
		}
		return false;
	}

	public void clear() {
		this.slot = null;
		setCount(0);
	}

	public void printAllElement() {
		for (List<KeyValue<TKey, TValue>> element : getSlot()) {
			if (element != null) {
				for (KeyValue<TKey, TValue> keyValue : element) {
					System.out.println(keyValue.getKey() + ": -> "
							+ keyValue.getValue());
				}
			}
		}
	}
}
