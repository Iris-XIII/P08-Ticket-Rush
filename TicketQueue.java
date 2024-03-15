///////////////////////////////////////////////////////////////////////////////
// Title: Course Enrollment Program
// Course: CS 300 Fall 2023
//
// Author: Iris Xu
// Email: jxu595@wisc.edu
// Lecturer: Mark Mansi

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a ticket queue which is a capacity based linked-list queue for
 * TicketSiteUsers
 * 
 * @author Iris Xu
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {

  // the linked node at the back of the queue
  private LinkedNode<TicketSiteUser> back;

  // the MAXIMUM number of TicketSiteUsers that the queue can hold
  private int capacity;

  // the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> front;

  // the number of TicketSiteUsers in the queue
  private int size;

  /**
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * 
   * @param capacity - the MAXIMUM number of TicketSiteUsers that the queue can hold
   */
  public TicketQueue(int capacity) {
    // exception if capacity less than one
    if (capacity < 1) {
      throw new IllegalArgumentException("Wrong capacity");
    }
    // initialize the private fields
    this.capacity = capacity;
    back = null;
    front = null;
    size = 0;
  }

  /**
   * Reports the capacity of the queue.
   * 
   * @return
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Creates and returns a deep copy (not the deepest copy) of this TicketQueue.
   */
  public TicketQueue deepCopy() {
    // create a new queue and copy the back, front, and size
    TicketQueue newQueue = new TicketQueue(this.capacity);
    newQueue.size = this.size;
    // create new ticket site user
    LinkedNode<TicketSiteUser> current = this.front;
    LinkedNode<TicketSiteUser> newCurrent = null;

    // make a deep copy for each node
    while (current != null) {
      LinkedNode<TicketSiteUser> newNode = new LinkedNode<TicketSiteUser>(current.getData());
      // set front and back if it current is the first element
      if (newCurrent == null) {
        newQueue.front = newNode;
        newQueue.back = newNode;
        newCurrent = newNode;
      }
      // reset back when copying the following nodes
      else {
        newCurrent.setNext(newNode);
        newQueue.back = newNode;
        newCurrent = newCurrent.getNext();
      }
      current = current.getNext();
    }
    return newQueue;
  }

  /**
   * Removes and returns the TicketSiteUser from the front of the queue.
   */
  @Override
  public TicketSiteUser dequeue() {
    // exception if queue if empty
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queue is empty, can't perform dequeue()");
    }

    // remove the front TicketSiteUser
    TicketSiteUser removed = front.getData();

    // set front and back to null if queue is empty after remove
    if (size == 1) {
      front = null;
      back = null;
    } else {
      // set the front to the next node
      front = front.getNext();

      // set front and back to the same node if only one left after remove
      if (size == 2)
        back = front;
    }
    // decrement size
    size--;
    return removed;
  }

  /**
   * Adds the given TicketSiteUser to the back of the queue.
   * 
   * @param newObject - given ticketSiteUser
   */
  @Override
  public void enqueue(TicketSiteUser newObject) {
    // exception if queue is full or user can't buy ticket
    if (size == capacity) {
      throw new IllegalStateException("Queue is full, can't enqueue more");
    }
    if (!newObject.canBuyTicket()) {
      throw new IllegalStateException("The User has a ticket/isn't logged in, can't buy a ticket");
    }

    // if the original queue if empty
    LinkedNode<TicketSiteUser> newBack = new LinkedNode<TicketSiteUser>(newObject);
    if (size == 0) {
      front = newBack;
      back = newBack;
    }
    // if the queue if not empty, enqueue to the back
    else {
      back.setNext(newBack);
      back = newBack;
    }
    // increment size
    size++;
  }

  /**
   * Reports if this queue is empty.
   * 
   * @return true if empty and false otherwise
   */
  @Override
  public boolean isEmpty() {
    return back == null && front == null && size == 0;
  }

  /**
   * Reports whether or not this queue is full.
   * 
   * @return true if full and false otherwise
   */
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Creates and returns and instance of a TicketQueueIterator for this queue.
   */
  @Override
  public Iterator<TicketSiteUser> iterator() {
    Iterator<TicketSiteUser> newIterator = new TicketQueueIterator(this);
    return newIterator;
  }

  /**
   * Returns the TicketSiteUser from the front of the queue without removing it.
   */
  @Override
  public TicketSiteUser peek() {
    // exception if queue is empty
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty, can't call peek()");
    }
    return front.getData();
  }

  /**
   * Changes the capacity of the queue to the new capacity.
   * 
   * @param newCapacity - new capacity of the queue
   */
  public void setCapacity(int newCapacity) {
    // exception if capacity less than one
    if (capacity < 1) {
      throw new IllegalArgumentException("Wrong capacity");
    }
    capacity = newCapacity;
  }

  /**
   * Reports the current size of the queue.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * String representation of the ticketQueue
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }

}
