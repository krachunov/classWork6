public class CountSymbol {

	public static void countSymbol(String string) {

		String currentString = string;
		MyHashTable<Character, Integer> numSymbol = new MyHashTable<Character, Integer>();

		for (int i = 0; i < currentString.length(); i++) {
			char c = currentString.charAt(i);
			if (!numSymbol.containsKey(c)) {
				numSymbol.addOrReplace(c, 1);
			} else {
				Integer currentElementCount = numSymbol.tryGetValue(c);
				numSymbol.addOrReplace(c, currentElementCount + 1);
			}

		}
		numSymbol.printAllElement();
	}

	public static void main(String[] args) {
		String text = "Did you know Math.Round rounds to the nearest even integer?";
		countSymbol(text);
	}

}
