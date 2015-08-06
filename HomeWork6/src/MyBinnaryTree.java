import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyBinnaryTree<T> {
	private Node<T> root;

	public MyBinnaryTree() {
		setRoot(null);
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
		private Node leftChild;
		private Node rightChild;
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

		@SuppressWarnings("unchecked")
		protected Node getLeftChild() {
			return leftChild;
		}

		protected void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		protected Node getRightChild() {
			return rightChild;
		}

		protected void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		protected Node getParent() {
			return parent;
		}

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

		@SuppressWarnings("unchecked")
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
		 * @param 41
		 * @return - min value from right child, starting from given node
		 */
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
					min=currentElement;
				}

			}

			return min;
		}

	}

	/**
	 * Check whether there are root if it does set
	 * 
	 * @param value
	 *            - new element
	 */
	public void add(T value) {
		if (this.getRoot() == null) {
			Node newNode = new Node<T>(value);
			setRoot(newNode);
		} else {
			getRoot().addChild(value);
		}
	}

	/**
	 * 
	 * @param value
	 *            - searching element
	 * @return - true or false
	 */
	public boolean contains(T value) {
		Node currecntNode = root.search(value);
		if (currecntNode != null) {
			return true;
		}
		return false;
	}

	public boolean remove(T element) {
		Node elementToRemove = root.search(element);
		Node elementParent = elementToRemove.getParent();

		if (elementToRemove != null) {
			// if left and right child or only right child
			if ((elementToRemove.getLeftChild() != null)
					&& (elementToRemove.getRightChild() != null)
					|| (elementToRemove.getRightChild() != null)
					&& (elementToRemove.getLeftChild() == null)) {

				Node substituteElement = findMin(element);
				elementToRemove.setValue(substituteElement.getValue());

				Node elementWhoReplaceParent = substituteElement.getParent();
				if (substituteElement.isLeft()) {
					elementWhoReplaceParent.setLeftChild(null);
					substituteElement.clear();
				} else {
					elementWhoReplaceParent.setRightChild(null);
					substituteElement.clear();
				}
				return true;
			}
			// only left child
			if ((elementToRemove.getLeftChild() != null)
					&& (elementToRemove.getRightChild() == null)) {
				// new element that will replace it
				Node substituteElement = elementToRemove.getLeftChild();
				// parent element that delete
				Node elementWhoReplaceParent = elementToRemove.getParent();

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

	public Node findMin(T element) {
		return root.findMinimumValue(element);
	}

}
