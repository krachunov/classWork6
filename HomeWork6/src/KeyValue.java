public class KeyValue<TKey, TValue> {
	public TKey key;
	public TValue value;

	public KeyValue(TKey key, TValue value) {
		setKey(key);
		setValue(value);
	}

	public TKey getKey() {
		return key;
	}

	public void setKey(TKey key) {
		this.key = key;
	}

	public TValue getValue() {
		return value;
	}

	public void setValue(TValue value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		KeyValue<TKey, TValue> element = (KeyValue<TKey, TValue>) other;
		boolean equals = this.getKey().equals(element.key)
				&& this.getValue().equals(element.value);
		return equals;
	}

	public int getHashCode() {
		return combineHashCodes(this.getKey().hashCode(), this.getValue()
				.hashCode());

	}

	public int combineHashCodes(int h1, int h2) {
		return ((h1 >> 5) + h1) ^ h2;
	}

	public String toString() {
		return "[" + getKey() + " -> " + getValue() + "]";
	}

}
