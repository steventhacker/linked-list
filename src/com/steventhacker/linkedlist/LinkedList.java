package com.steventhacker.linkedlist;

import java.util.*;

/**
 * Custom implementation of a Linked List. Uses an abstract Node to hold all data, each with
 * their own reference to the next node in the list.
 */
public class LinkedList<E> implements List<E> {
	private Node firstNode;
	private int size;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}
		Node temp = firstNode;
		while (temp != null) {
			if (o.equals(temp.getData())) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return (index < size) && (get(index) != null);
			}

			@Override
			public E next() {
				return get(index++);
			}

			@Override
			public void remove() {
				LinkedList.this.remove(this.index);
			}
		};
		return iterator;
	}

	@Override
	public Object[] toArray() {
		Object[] returnedArray = new Object[size];

		Node temp = firstNode;
		for (int i = 0; i < size; i++) {
			returnedArray[i] = temp.getData();
			temp = temp.getNext();
		}

		return returnedArray;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		//TODO: this
		return null;
	}

	@Override
	public boolean add(E e) {
		// Build a node out of given data
		Node<E> node = new Node<>(e);

		// Find first available location to link node
		if (firstNode == null) {
			firstNode = node;
			size++;
		} else {
			Node temp = firstNode.getNext();
			Node prev = firstNode;
			while (temp != null) {
				prev = temp;
				temp = temp.getNext();
			}
			temp = node;
			prev.setNext(temp);
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {

		if (firstNode == null)
		{
			// Do nothing, return success
			return true;
		}

		if (firstNode == o)
		{
			firstNode = firstNode.getNext();
			size--;
			return true;
		}

		Node temp = firstNode;
		Node prev = firstNode;
		while (temp != null)
		{

			if (temp.getData().equals(o))
			{
				prev.setNext(temp.getNext());
				size--;
				return true;
			}

			prev = temp;
			temp = temp.getNext();
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object item : c) {
			if (!this.contains(item)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (Object item : c) {
			this.add((E) item);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		for (Object item : c) {
			this.add(index, (E) item);
			index++;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object item : c) {
			this.remove(item);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean removed = false;
		Iterator<E> iterator = this.iterator();
		while (iterator.hasNext()) {
			try {
				E val = iterator.next();
				if (!c.contains(val)) {
					iterator.remove();
					removed = true;
				}
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("err");
			}

		}
		return removed;
	}

	@Override
	public void clear() {
		while (firstNode != null) {
			Node temp = firstNode.getNext();

			firstNode.clear();

			firstNode = temp;
		}

		size = 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (firstNode == null) {
			return null;
		}
		if (index == 0) {
			return (E) firstNode.getData();
		}

		if (index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}

		Node temp = firstNode.getNext();

		int count = 1;
		while (count <= index) {
			if (temp == null) {
				return null;
			}
			else if (count == index) {
				return (E) temp.getData();
			}
			temp = temp.getNext();
			count++;
		}
		return null;
	}

	@Override
	public E set(int index, E element) {
		verifyListIntegrity();

		if (index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}

		if (index == 0) {
			firstNode.setData(element);
			return null;
		}

		int count = 1;
		Node temp = firstNode.getNext();
		while (count <= index) {
			if (temp == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
			if (count == index) {
				temp.setData(element);
				return null;
			}
			temp = temp.getNext();
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		if (firstNode == null) {
			throw new ArrayIndexOutOfBoundsException("List not large enough");
		}
		if (!element.getClass().equals(firstNode.getData().getClass())) {
			throw new InputMismatchException("Types do not match");
		}
		Node node = firstNode;

		for (int i = 0; i < size; i++) {
			if (i == index) {
				Node newNode = new Node(element);
				newNode.setNext(node.getNext());
				node.setNext(newNode);
				size++;
				break;
			}
			node = node.getNext();
		}

	}

	@Override
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException("Must be a valid index");
		}

		if (index == 0) {
			firstNode = firstNode.getNext();
			size--;
		} else {
			int count = 0;
			Node node = firstNode.getNext();
			for (int i = 0; i < index; i++) {
				if (i == index) {

					// TODO: this

					size--;
				} else {
					node = node.getNext();
				}
			}
		}

		return null;
	}

	@Override
	public int indexOf(Object o) {
		int index = -1;

		Node node = firstNode;

		for (int i = 0; i < size; i++) {
			if (node.getData().equals(o)) {
				index = i;
				break;
			}
			node = node.getNext();
		}

		return index;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = -1;

		Node node = firstNode;

		for (int i = 0; i < size; i++) {
			if (node.getData().equals(o)) {
				index = i;
			}
			node = node.getNext();
		}

		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		//TODO: this
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		//TODO: this
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex > toIndex) {
			try {
				throw new Exception("Start must come before end");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Node node = firstNode;

		List<E> returnedList = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			if (i >= fromIndex && i <= toIndex) {
				returnedList.add((E) node.getData());
			}
			node = node.getNext();
		}

		return returnedList;
	}

	private void verifyListIntegrity() {
		if (firstNode == null) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
