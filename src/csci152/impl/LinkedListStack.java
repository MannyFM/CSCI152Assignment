/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Stack;
import csci152.impl.LLNode;

/**
 *
 * @author manny
 * @param <T>
 */
public class LinkedListStack<T> implements Stack<T> {

  private LLNode<T> head;
  private int size;
  
  public LinkedListStack() {
	head = null;
	size = 0;
  }
  
  @Override
  public void push(T value) {
	LLNode<T> node = new LLNode<>(value);
	node.setNext(this.head);
	this.head = node;
	size++;
  }

  @Override
  public T pop() throws Exception {
	if (size == 0)
	  throw new Exception("Stack is empty");
	T result = this.head.getValue();
	this.head = this.head.getNext();
	size--;
	return result;
  }

  @Override
  public int getSize() {
	return this.size;
  }

  @Override
  public void clear() {
	size = 0;
	this.head = null;
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
