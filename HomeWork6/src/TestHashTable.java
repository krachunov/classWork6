public class TestHashTable {

	public static void main(String[] args) {
		MyHashTable<String, Integer> staff = new MyHashTable<String, Integer>();
		
		//Check capacity and count
		System.out.println("count: "+ staff.getCount());
		System.out.println("Capacity: "+ staff.getCapacity());
		staff.add("s1", 101);
		staff.add("s2", 102);
		staff.add("s3", 103);
		staff.add("s4", 104);
		staff.add("s5", 105);
		staff.add("s6", 106);
		staff.add("s7", 107);
		staff.add("s8", 108);
		staff.add("s9", 109);
		staff.add("s10", 110);
		staff.add("s11", 111);
		staff.add("s12", 112);
		//Check capacity and count
		System.out.println("count: "+ staff.getCount());
		System.out.println("Capacity: "+ staff.getCapacity());
		staff.add("s13", 113);
		staff.add("s14", 114);
		staff.add("s15", 115);
		staff.add("s16", 116);
		staff.add("s17", 117);
		staff.add("s18", 118);
		staff.add("s19", 119);
		//Check capacity and count
		System.out.println("count: "+ staff.getCount());
		System.out.println("Capacity: "+ staff.getCapacity());
		staff.add("s20", 120);
		staff.add("s21", 121);
		staff.add("s22", 122);
		staff.add("s23", 123);
		staff.add("s24", 124);
		staff.add("s25", 125);
		staff.add("s26", 126);
		System.out.println("Element with key "+staff.find("s26"));
		//replace value to key s26
		staff.addOrReplace("s26", 10);
		//Check value to key s26
		System.out.println("Element with key "+staff.find("s26"));
		//Check capacity and count
		System.out.println("count: "+ staff.getCount());
		System.out.println("Capacity: "+ staff.getCapacity());
		
		System.out.println(staff.remove("s26"));
		System.out.println(staff.remove("s26"));
		//Check capacity and count
		System.out.println("After remove");
		System.out.println("count: "+ staff.getCount());
		System.out.println("Capacity: "+ staff.getCapacity());

		
		

	}

}
