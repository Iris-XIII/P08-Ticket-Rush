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
 * This class is the iterator for a TicketQueue that keeps the original queue intact. This iterator
 * will return elements in the order of the queue from front to back.
 * 
 * @author Iris Xu
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser> {

  // deep copy of a TicketQueue
  private TicketQueue userQueue;

  /**
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given
   * queue.
   * 
   * @param queue - TicketQueue
   */
  public TicketQueueIterator(TicketQueue queue) {
    // exception if queue if null
    if (queue == null) {
      throw new IllegalArgumentException("Queue is null can't make deep copy");
    }
    // make a deep copy of queue
    userQueue = queue.deepCopy();
  }

  /**
   * Determines whether or not there is another TicketSiteUser in the queue.
   * 
   * @return true if there is another TicketSiteUser and false otherwise
   */
  @Override
  public boolean hasNext() {
    return !userQueue.isEmpty();
  }

  /**
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   */
  @Override
  public TicketSiteUser next() {
    // exception if no next exist
    if (userQueue.isEmpty())
      throw new NoSuchElementException(
          "There are no more TicketSiteUsers in the queue, can't find next");
    // return the next TicketSiteUser in the list
    return userQueue.dequeue();
  }
}
