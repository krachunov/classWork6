public class BinnaryTreeTest {

	public static void main(String[] args) {
		MyBinnaryTree<Integer> myTree = new MyBinnaryTree<Integer>();
		myTree.add(17);
		myTree.add(9);
		myTree.add(12);
		myTree.add(19);
		myTree.add(6);
		myTree.add(25);
		
	
	System.out.println(myTree.contains(17));
	System.out.println(myTree.contains(9));
	System.out.println(myTree.contains(12));
	System.out.println(myTree.contains(19));
	System.out.println(myTree.contains(6));
	System.out.println(myTree.contains(25));
	myTree.remove(9);
	System.out.println("-------------");
	System.out.println(myTree.contains(17));
	System.out.println(myTree.contains(9));
	System.out.println(myTree.contains(12));
	System.out.println(myTree.contains(19));
	System.out.println(myTree.contains(6));
	System.out.println(myTree.contains(25));
		
	}

}
