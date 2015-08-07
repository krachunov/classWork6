import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLDocument.Iterator;

public class MyBinnaryTree<T> implements Iterable {
	private Node<T> root;
	private int count;

	public MyBinnaryTree() {
		setRoot(null);
		setCount(0);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@SuppressWarnings("rawtypes")
	public Node getRoot() {
		return root;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRoot(Node root) {
		this.root = root;
	}

	@SuppressWarnings("hiding")
	private class Node<T> {
		private T value;
		@SuppressWarnings("rawtypes")
		private Node leftChild;
		@SuppressWarnings("rawtypes")
		private Node rightChild;
		@SuppressWarnings("rawtypes")
		private Node parent;
		private boolean isLeft;

		protected Node(T value) {
			setValue(value);
		}

		protected T getValue() {
			return value;
		}

		protected void setValue(T value) {
			this.value = value;
		}

		@SuppressWarnings("rawtypes")
		protected Node getLeftChild() {
			return leftChild;
		}

		@SuppressWarnings("rawtypes")
		protected void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		@SuppressWarnings("rawtypes")
		protected Node getRightChild() {
			return rightChild;
		}

		@SuppressWarnings("rawtypes")
		protected void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		@SuppressWarnings("rawtypes")
		protected Node getParent() {
			return parent;
		}

		@SuppressWarnings("rawtypes")
		protected void setParent(Node parent) {
			this.parent = parent;
		}

		public boolean isLeft() {
			return isLeft;
		}

		public void setLeft(boolean isLeft) {
			this.isLeft = isLeft;
		}

		public void clear() {
			setParent(null);
			setValue(null);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected void addChild(T value) {
			if ((Integer) value == (Integer) this.getValue()) {
				throw new IllegalArgumentException("This element already exist");
			}
			// Right child
			if ((Integer) value > (Integer) this.getValue()) {
				if (getRightChild() == null) {
					Node newNode = new Node(value);
					this.setRightChild(newNode);
					newNode.setParent(this);
					return;
				} else {
					getRightChild().addChild(value);

				}
				// left child
			} else {
				if (getLeftChild() == null) {
					Node newNode = new Node(value);
					this.setLeftChild(newNode);
					newNode.setParent(this);
					newNode.setLeft(true);
					return;
				} else {
					getLeftChild().addChild(value);

				}
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected Node search(T value) {
			if (value.equals(this.getValue())) {
				Node searchingNode = this;
				return searchingNode;
			}
			if ((Integer) value > (Integer) this.getValue()) {
				if (rightChild != null) {
					return getRightChild().search(value);
				}

			} else {
				if (getLeftChild() != null) {
					return getLeftChild().search(value);
				}
			}
			return null;
		}

		/**
		 * 
		 * @param -
		 * @return - Node with minimum value from right child, starting from
		 *         given node
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected Node findMinimumValue(T value) {
			Node<T> localRoot = search(value);
			Queue<Node> queue = new LinkedList<Node>();
			Node min = new Node((Integer) (Integer.MAX_VALUE));
			queue.add(localRoot.getRightChild());
			while (queue.size() > 0) {
				Node<T> currentElement = queue.poll();
				if (currentElement.getLeftChild() != null) {
					queue.add(currentElement.getLeftChild());
				}
				if (currentElement.getRightChild() != null) {
					queue.add(currentElement.getRightChild());
				}
				if ((Integer) min.getValue() > (Integer) currentElement
						.getValue()) {
					min = currentElement;
				}
			}
			return min;
		}

		/**
		 * crawls all items used for iterator
		 * 
		 * @param startNode
		 * @return
		 */
		public ArrayList<T> travel(Node<T> startNode) {

			ArrayList<T> list = new ArrayList<T>();
			Stack<Node> stack = new Stack<Node>();
			stack.add(startNode);
			while (stack.size() > 0) {

				if (startNode.getRightChild() != null) {
					stack.add(startNode.getRightChild());
				}
				if (startNode.getLeftChild() != null) {
					stack.add(startNode.getLeftChild());
				}
				startNode = stack.pop();
				list.add(startNode.getValue());
			}
			return list;
		}

	}

	public void printInOrder(Node<T> startNode) {

		if (startNode != null) {

			printInOrder(startNode.getLeftChild());
			System.out.println(startNode.getValue());
			printInOrder(startNode.getRightChild());
		}
	}

	/**
	 * Check whether there are root if it does set
	 * 
	 * @param value
	 *            - new element
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(T value) {
		if (this.getRoot() == null) {
			Node newNode = new Node<T>(value);
			setRoot(newNode);
			count++;
		} else {
			getRoot().addChild(value);
			count++;
		}
	}

	/**
	 * 
	 * @param value
	 *            - searching element
	 * @return - true or false
	 */
	@SuppressWarnings("rawtypes")
	public boolean contains(T value) {
		Node currentNode = root.search(value);
		if (currentNode != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param element
	 *            - element who want to remove
	 * @return - true if removal is successful or false if not
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean remove(T element) {
		// Find element who we want to remove
		Node elementToRemove = root.search(element);

		if (elementToRemove != null) {
			// if there are elements that remove the left and right child, or
			// just right child
			if ((elementToRemove.getLeftChild() != null)
					&& (elementToRemove.getRightChild() != null)
					|| (elementToRemove.getRightChild() != null)
					&& (elementToRemove.getLeftChild() == null)) {

				Node substituteElement = findMin(element);
				elementToRemove.setValue(substituteElement.getValue());
				// Remove the replacement element from its parent
				Node substituteElementParent = substituteElement.getParent();
				// If left child
				if (substituteElement.isLeft()) {
					substituteElementParent.setLeftChild(null);
					substituteElement.clear();
					// If right child
				} else {
					substituteElementParent.setRightChild(null);
					substituteElement.clear();
				}
				return true;
			}
			// only left child
			if ((elementToRemove.getLeftChild() != null)
					&& (elementToRemove.getRightChild() == null)) {
				// new element that will replace it
				Node substituteElement = elementToRemove.getLeftChild();
				// parent of elements that remove
				Node elementWhoReplaceParent = elementToRemove.getParent();
				// If the item is left that removes a child substitute in his
				// parent left successor
				if (elementToRemove.isLeft) {
					elementWhoReplaceParent.setLeftChild(substituteElement);
				} else {
					elementWhoReplaceParent.setRightChild(substituteElement);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Auxiliary method for friction element
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Node findMin(T element) {
		return root.findMinimumValue(element);
	}

	@Override
	public java.util.Iterator iterator() {
		ArrayList<T> list = root.travel(root);
		return list.iterator();
	}

}
