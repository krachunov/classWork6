import java.util.ArrayList;

public class BinnaryTreeTest {

	public static void main(String[] args) {
		MyBinnaryTree<Integer> myTree = new MyBinnaryTree<Integer>();
		myTree.add(6);
		myTree.add(2);
		myTree.add(1);
		myTree.add(4);
		myTree.add(3);
		myTree.add(5);
		myTree.add(7);
		myTree.add(9);
		myTree.add(8);


		for (Object object : myTree) {
			System.out.println(object);
		}
		System.out.println("Count " + myTree.getCount());

	}

}
