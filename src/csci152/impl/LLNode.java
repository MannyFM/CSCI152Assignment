/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

/**
 *
 * @author manny
 * @param <T>
 */
public class LLNode<T> {
  private LLNode<T> link;
  private T value;
  
  public LLNode(T value) {
	this.link = null;
	this.value = value;
  }
  
  public LLNode<T> getNext() {
	return this.link;
  }
  
  public void setNext(LLNode<T> link) {
	this.link = link;
  }
  
  public T getValue() {
	return this.value;
  }
  
  public void setValue(T value) {
	this.value = value;
  }
  
  @Override
  public String toString() {
	return this.value.toString();
  }
  
}
