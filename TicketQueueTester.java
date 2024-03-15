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

import java.util.NoSuchElementException;

/**
 * This class is a series of static tester methods to check the correctness of the TicketQueue and
 * the TicketQueueIterator.
 * 
 * @author Iris Xu
 */
public class TicketQueueTester {

  public static void main(String[] args) {
    runAllTests();
  }

  /**
   * @return true if and only if all test are true
   */
  private static boolean runAllTests() {
    boolean constructor = testConstructor();
    boolean dequeue = testDequeue();
    boolean enqueue = testEnqueue();
    boolean iterator = testIterator();
    boolean peek = testPeek();

    // print out pass / fail information
    System.out.println("testConstructor: " + (constructor ? "PASS" : "FAIL"));
    System.out.println("testDequeue: " + (dequeue ? "PASS" : "FAIL"));
    System.out.println("testEnqueue: " + (enqueue ? "PASS" : "FAIL"));
    System.out.println("testIterator: " + (iterator ? "PASS" : "FAIL"));
    System.out.println("testPeek: " + (peek ? "PASS" : "FAIL"));

    return constructor && dequeue && enqueue && iterator && peek;
  }

  /**
   * Checks the correctness of the TicketQueue's constructor, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if constructor work correctly
   */
  public static boolean testConstructor() {
    // case 1: throw exception if capacity < 1
    boolean check1 = false;
    try {
      TicketQueue queue1 = new TicketQueue(0);
      // error message
      System.out.println("Fail to throw exception in constructor when capacity < 1");
    } catch (IllegalArgumentException e) {
      check1 = true;
    }

    // case 2: create a ticket queue is capacity >= 1
    boolean check2 = false;
    TicketQueue queue2 = new TicketQueue(4);
    if (queue2.isEmpty() && !queue2.isFull() && queue2.capacity() == 4 && queue2.size() == 0
        && queue2.toString().equals("")) {
      check2 = true;
    } else {
      // error message
      System.out.println("Wrong constructor");
    }

    return check1 && check2;
  }

  /**
   * Checks the correctness of the TicketQueue's dequeue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if the dequeue() work correctly
   */
  public static boolean testDequeue() {
    // create new queue
    TicketQueue queue1 = new TicketQueue(4);
    // create new users
    TicketSiteUser user1 = new TicketSiteUser("Mary", "123456", "1234857294726472");
    TicketSiteUser user2 = new TicketSiteUser("Helen", "654321", "3234857684726002");
    TicketSiteUser user3 = new TicketSiteUser("Kerry", "000123", "5648927684807001");
    TicketSiteUser user4 = new TicketSiteUser("Ken", "143567", "5642347677007001");
    // user login
    user1.login("Mary", "123456");
    user2.login("Helen", "654321");
    user3.login("Kerry", "000123");
    user4.login("Ken", "143567");

    // case 1: exception if queue is empty
    boolean check1 = false;
    try {
      queue1.dequeue();
      // error message
      System.out.println("Fail to throw exception in dequeue when queue is empty");
    } catch (NoSuchElementException e) {
      check1 = true;
    }

    // case 2: dequeue when queue no empty
    boolean check2 = false;
    // enqueue the users
    queue1.enqueue(user1);
    queue1.enqueue(user2);
    queue1.enqueue(user3);
    queue1.enqueue(user4);
    // dequeue the users
    queue1.dequeue();
    queue1.dequeue();
    queue1.dequeue();
    queue1.dequeue();

    if (queue1.capacity() == 4 && queue1.size() == 0 && queue1.isEmpty()
        && queue1.toString().equals("")) {
      check2 = true;
    } else {
      // error message
      System.out.println("Wrong dequeue");
      System.out.println(queue1.toString());
    }
    return check1 && check2;
  }

  /**
   * Checks the correctness of the TicketQueue's enqueue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if enqueue() work correctly
   */
  public static boolean testEnqueue() {
    try {
      // create new user and new queue
      TicketQueue queue1 = new TicketQueue(4);
      TicketSiteUser user1 = new TicketSiteUser("Mary", "123456", "1234857294726472");
      TicketSiteUser user2 = new TicketSiteUser("Helen", "654321", "3234857684726002");
      TicketSiteUser user3 = new TicketSiteUser("Kerry", "000123", "5648927684807001");
      TicketSiteUser user4 = new TicketSiteUser("Ken", "143567", "5642347677007001");
      TicketSiteUser user5 = new TicketSiteUser("Bob", "345612", "8842399977007801");
      // users login
      user1.login("Mary", "123456");
      user2.login("Helen", "654321");
      user3.login("Kerry", "000123");
      user4.login("Ken", "143567");
      user4.login("Bob", "345612");

      // case 1: exception if queue is full
      boolean check1 = false;
      try {
        // enqueue the users
        queue1.enqueue(user1);
        queue1.enqueue(user2);
        queue1.enqueue(user3);
        queue1.enqueue(user4);
        queue1.enqueue(user5);
        // error message
        System.out.println("Fail to throw exception in enqueue when queue is full");
      } catch (IllegalStateException e) {
        check1 = true;
      }

      // case 2: enqueue when queue is not full
      boolean check2 = false;
      if (queue1.capacity() == 4 && queue1.size() == 4 && queue1.isFull()
          && queue1.toString().trim().equals(user1.toString() + "\n" + user2.toString() + "\n"
              + user3.toString() + "\n" + user4.toString())) {
        check2 = true;
      } else {
        // error message
        System.out.println("Wrong enqueue");
        System.out.println(queue1.toString());
      }
      return check1 && check2;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks the correctness of the TicketQueueIterator method(s) and iterating through a
   * TicketQueue.
   * 
   * @return true if enqueue() work correctly
   */
  public static boolean testIterator() {
    // create a new queue
    TicketQueue queue1 = new TicketQueue(3);
    // create new users
    TicketSiteUser user1 = new TicketSiteUser("Mary", "123456", "1234857294726472");
    TicketSiteUser user2 = new TicketSiteUser("Helen", "654321", "3234857684726002");
    TicketSiteUser user3 = new TicketSiteUser("Kerry", "000123", "5648927684807001");

    // users login
    user1.login("Mary", "123456");
    user2.login("Helen", "654321");
    user3.login("Kerry", "000123");
    // enqueue users
    queue1.enqueue(user1);
    queue1.enqueue(user2);
    queue1.enqueue(user3);

    // create new iterator
    TicketQueueIterator iterator = new TicketQueueIterator(queue1);

    // create an array of users the next returned
    String str = "";
    TicketSiteUser[] arr = new TicketSiteUser[3];
    for (TicketSiteUser element : arr) {
      if (iterator.hasNext()) {
        element = iterator.next();
        str += element.toString() + "\n";
      }
    }

    // compare the users return by next and the expect toString value
    if (str.trim().equals(user1.toString() + "\n" + user2.toString() + "\n" + user3.toString()))
      return true;
    else {
      // error message
      System.out.println("Wrong sequence return by next: " + str);
      return false;
    }

  }

  /**
   * Checks the correctness of the TicketQueue's peek() method, including case(s) where it should 
   * throw exceptions.
   * 
   * @return true if peek() work correctly
   */
  public static boolean testPeek() {
    // create new queue
    TicketQueue queue1 = new TicketQueue(4);
    // create new users
    TicketSiteUser user1 = new TicketSiteUser("Mary", "123456", "1234857294726472");
    TicketSiteUser user2 = new TicketSiteUser("Helen", "654321", "3234857684726002");
    TicketSiteUser user3 = new TicketSiteUser("Kerry", "000123", "5648927684807001");
    TicketSiteUser user4 = new TicketSiteUser("Ken", "143567", "5642347677007001");
    // user login
    user1.login("Mary", "123456");
    user2.login("Helen", "654321");
    user3.login("Kerry", "000123");
    user4.login("Ken", "143567");

    // case 1: exception if queue is empty
    boolean check1 = false;
    try {
      queue1.peek();
      // error message
      System.out.println("Fail to throw exception in peek when queue is empty");
    } catch (NoSuchElementException e) {
      check1 = true;
    }

    // case 2: peek when queue no empty
    boolean check2 = false;
    // enqueue the users
    queue1.enqueue(user1);
    queue1.enqueue(user2);
    queue1.enqueue(user3);
    queue1.enqueue(user4);
    // peek the users
    TicketSiteUser peek1 = queue1.peek();
    if (peek1 == user1 && queue1.size() == 4 && queue1.isFull()
        && queue1.toString().trim().equals(user1.toString() + "\n" + user2.toString() + "\n"
            + user3.toString() + "\n" + user4.toString())) {
      check2 = true;
    }
    return check1 && check2;
  }
}
