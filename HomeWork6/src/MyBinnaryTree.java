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
		if (elementToRemove != null) {
			if (elementToRemove.getLeftChild() != null) {
				elementToRemove.getLeftChild().setParent(elementToRemove.getParent());
				//TODO add a
				elementToRemove.getParent().setLeftChild(elementToRemove.getLeftChild());
				return true;
			}
			if (elementToRemove.getRightChild() != null) {
				elementToRemove.getRightChild().setParent(elementToRemove.getParent());
				elementToRemove.getParent().setRightChild(elementToRemove.getRightChild());
				return true;
			}
		}
		return false;
	}
}
