/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Queue;
import csci152.impl.LLNode;

/**
 *
 * @author manny
 * @param <T>
 */
public class LinkedListQueue<T> implements Queue<T>{

  private LLNode<T> head, tail;
  private int size;
  
  public LinkedListQueue() {
	head = tail = null;
	size = 0;
  }
  
  @Override
  public void enqueue(T value) {
	LLNode<T> newNode = new LLNode<>(value);
	if (size > 0) {
	  tail.setNext(newNode);
	}
	tail = newNode;
	size++;
	if (size == 1) {
	  head = newNode;
	}
  }

  @Override
  public T dequeue() throws Exception {
	if (size <= 0)
	  throw new Exception("Queue is empty");
	T result = head.getValue();
	head = head.getNext();
	size--;
	if (size == 0) {
	  tail = null;
	}
	return result;
  }

  @Override
  public int getSize() {
	return this.size;
  }

  @Override
  public void clear() {
	head = tail = null;
	size = 0;
  }
  
  @Override
  public String toString() {
	String res = "";
	LLNode<T> tmp = this.head;
	boolean first = true;
	while (tmp != null) {
	  if (!first) {
		res += ", ";
	  } else
		first = false;
	  res += tmp.getValue().toString();
	  tmp = tmp.getNext();
	}
	return "[" + res + "]";
  }
}
