import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phonebook {

	private MyHashTable<String, String> book;

	public String[] splitNamePhone(String text) {
		String[] separated = text.trim().split("-");
		return separated;
	}

	/**
	 * Read from console line by line and record
	 * 
	 * @return
	 */
	private MyHashTable<String, String> recordName() {
		book = new MyHashTable<String, String>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the line");
		String inputText = scan.nextLine();
		String stopLine = new String("search");
		while (!inputText.equals(stopLine)) {

			String[] splitetdtext = splitNamePhone(inputText);
			if (splitetdtext.length > 1) {
				book.addOrReplace(splitetdtext[0], splitetdtext[1]);
			}
			// System.out.println("Enter the line");
			inputText = scan.nextLine();
		}
		return book;
	}

	/**
	 * Search record from input string from console
	 * 
	 * @param bookSearch
	 * @return - List with all find and not found
	 */
	private List<String> search(MyHashTable<String, ?> bookSearch) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the searching person");
		String searchingName;
		List<String> result = new ArrayList<String>();
		do {
			searchingName = scan.nextLine();
			KeyValue<String, String> record = book.find(searchingName);
			if (record != null) {
				result.add(record.getKey() + " -> " + record.getValue());
			} else {
				if (!searchingName.equals("stop")) {
					result.add("Contact " + searchingName + " does not exist.");
				}
			}
		} while (!searchingName.equals("stop"));
		return result;
	}

	public void createPhoneBook() {
		MyHashTable<String, String> book = recordName();
		List<String> result = search(book);
		for (String record : result) {
			System.out.println(record);
		}
	}

}
