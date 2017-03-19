/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.SortedQueue;
import csci152.impl.LLNode;

/**
 *
 * @author manny
 * @param <T>
 */
public class LinkedListSortedQueue<T extends Comparable<? super T>> implements SortedQueue<T> {

  private LLNode<T> head;
  private int size;

  public LinkedListSortedQueue() {
	head = null;
	size = 0;
  }

  @Override
  public void insert(T value) {
	size++;
	LLNode<T> newNode = new LLNode<T>(value);
	if (size == 1) {
	  head = newNode;
	  return;
	}
	if (value.compareTo(head.getValue()) <= 0) {
	  System.out.println("!");
	  newNode.setNext(head);
	  head = newNode;
	  return;
	}
	LLNode<T> tmp = head;
	while (tmp.getNext() != null) {
	  if (value.compareTo(tmp.getNext().getValue()) <= 0) {
		newNode.setNext(tmp.getNext());
		tmp.setNext(newNode);
		return;
	  }
	  tmp = tmp.getNext();
	}
	tmp.setNext(newNode);
//	throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public T dequeue() throws Exception {
	if (size == 0) {
	  throw new Exception("SortedQueue is empty");
	}
	size--;
	T value = this.head.getValue();
	head = head.getNext();
	return value;
  }

  @Override
  public int getSize() {
	return this.size;
  }

  @Override
  public void clear() {
	head = null;
	size = 0;
  }

  @Override
  public String toString() {
	String res = "";
	LLNode<T> tmp = this.head;
	while (tmp != null) {
	  res += tmp.getValue();
	  if (tmp.getNext() != null)
		res += ", ";
	  tmp = tmp.getNext();
	}
	return "[" + res + "]";
  }
}
