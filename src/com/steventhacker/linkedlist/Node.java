package com.steventhacker.linkedlist;

/**
 * Container to hold data in a singly linked list format, with a reference to the next node in succession.
 */
public class Node<E> {
	private E data;
	private Node next;

	public Node(E data) {
		this.data = data;
	}

	public Node(E data, Node next) {
		this.data = data;
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public void clear() {
		this.data = null;
		this.next = null;
	}
}
