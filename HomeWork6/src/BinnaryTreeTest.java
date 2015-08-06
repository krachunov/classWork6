import java.util.ArrayList;

public class BinnaryTreeTest {

	public static void main(String[] args) {
		MyBinnaryTree<Integer> myTree = new MyBinnaryTree<Integer>();
		myTree.add(19);
		myTree.add(10);
		myTree.add(9);
		myTree.add(12);
		myTree.add(11);
		myTree.add(30);
		myTree.add(25);
		myTree.add(20);
		myTree.add(27);
		myTree.add(26);
		myTree.add(28);
		myTree.add(32);
		myTree.add(31);
		myTree.add(40);
		myTree.add(8);
		myTree.add(7);
		myTree.remove(9);

		for (Object object : myTree) {
			System.out.println(object);
		}
		System.out.println("Count " + myTree.getCount());

	}

}
