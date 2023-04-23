package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head first element of the list
   * @param tail last element of the list
   * @param nodeSize size of the particular node
   * @param size size of the list
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size() {
    return size;
  }
  
  @Override
  public boolean add(E item) {
    if (item == null) {
        throw new IllegalArgumentException();
    }
    if (size == 0) {
        Node newNode = new Node();
        newNode.addItem(item);
        if  (head != null) {
            head.next = newNode;
            newNode.previous = head;
            tail.previous = newNode;
            newNode.next = tail;
        }
    } else {
        Node newNode = tail.previous;
        if (newNode.count < nodeSize) {
            newNode.addItem(item);
        } else {
            Node newNode2 = new Node();
            newNode2.addItem(item);
            if (head != null){
                newNode.next = newNode2;
                newNode2.previous = newNode;
                tail.previous = newNode2;
                newNode2.next = tail;
            }
        }
    }
    size++;
    return true;
  }

  @Override
  public void add(int pos, E item) {
      if (item == null || pos > size) { // check if the item is null or if the position is greater than the size of the list
          throw new RuntimeException();
      }

      if (pos > nodeSize || pos % nodeSize == 0) { // run this if the nodesize is smaller than the position, or if the remainder of the position and the node equals -
          if (head.next.count == nodeSize && pos < nodeSize) {
              Node cur = head.next;
              Node tmp = new Node();
              int index = 0;
              addLoop(cur, tmp, index);
              cur.addItem(pos, item);
              if (head != null) {
                  tmp.next = cur.next;
                  cur.next = tmp;
                  head.next = cur;
              } else{
                  cur = tail.previous;
                  if(cur.count < nodeSize){
                      cur.addItem(pos % nodeSize, item);
                  }else{
                      tmp = new Node();
                      tmp.addItem(pos % nodeSize, item);
                      if(head != null){
                          cur.next = tmp;
                          tmp.previous = cur;
                          tail.previous = tmp;
                          tmp.next = tail;
                      }
                  }
              }
          } else if(size == 0) { // check when size is 0
              Node tmp = new Node();
              tmp.addItem(item);
              if (head != null) {
                  head.next = tmp;
                  tmp.previous = head;
                  tail.previous = tmp;
                  tmp.next = tail;
              }
          } else if (head.next.count == nodeSize) { // check when the node after the head's count is the same as the nodeSize
              if(pos <= nodeSize / 2){
                  Node cur = head.next;
                  Node tmp = new Node();
                  int index = 0;
                  addLoop(cur, tmp, index);
                  cur.addItem(pos, item);
                  if(head != null){
                      tmp.next = cur.next;
                      cur.next = tmp;
                      head.next = cur;
                  }
              } else if (pos > nodeSize / 2) { // Check when the position is greater than half of the nodeSize
                  Node cur = head.next;
                  Node tmp = new Node();
                  int index = 0;
                  addLoop(cur, tmp, index);
                  tmp.addItem(pos - nodeSize / 2, item);
                  if (head != null) {
                      tmp.next = cur.next;
                      cur.next = tmp;
                      head.next = cur;
                  }
              }
          } else {
              head.next.addItem(pos, item); // If not all, just add to the node after the head at the position
          }

      }

      size++;
  }

  @Override
  public E remove(int pos) {

     Node newNode = head.next;
      E removed = null;
     if (pos >= nodeSize) {
         newNode = newNode.next;
         removed = newNode.data[pos % nodeSize];
         newNode.removeItem(pos % nodeSize);
     } else {
         removed = newNode.data[pos];
         newNode.removeItem(pos);
     }
    return removed;
  }

  /**
   * Helper method to insert newNode into list after current
   * @param current
   * @param newNode
   */
  private void link(Node current, Node newNode) {
      newNode.previous = current;
      newNode.next = current.next;
      current.next.previous = newNode;
      current.next = newNode;
  }

  /**
   * Helper method to remove current from the list
   * @param current
  */
  private void unLink(Node current) {
      current.previous.next = current.next;
      current.next.previous = current.previous;
  }

  /**
   * Helper method to find and return the Node at the given index
   * @param pos
   * @return
   */
  private Node findNodeByIndex(int pos) {
      if (pos == -1) {
          return head;
      }
      if (pos == size) {
          return tail;
      }

      int index = pos % nodeSize;
      Node cur = head;
      cur.next = head.next;
      cur.previous = head;
      for (int i = 0; i < index; i++) {
          cur = cur.next;
      }

      return cur;
    }

    private class StoutComparator<E> implements Comparator<E> {
        @Override
        public int compare(E e, E t1) {
            int i = e.toString().compareTo(t1.toString());
            if (i > 0) { return 1; }
            else if (i < 0) { return -1; }
            return 0;
        }
    }

    /**
     * Helper method to add items to a temporary node, then remove them from the actual node.
     * @param cur
     * @param tmp
     * @param index
     */
    private void addLoop(Node cur, Node tmp, int index) {
        for (int i = nodeSize / 2; i < nodeSize; i++) {
            tmp.addItem(index, cur.data[i]);
            index++;
        }
        for (int i = nodeSize / 2; i < nodeSize; i++) {
            cur.removeItem(i);
        }
    }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort() {
      // TODO

      int k = 0;
      E[] allData = (E[]) new Comparable[size * nodeSize];
      StoutListIterator it = (StoutListIterator) this.listIterator();
      StoutComparator<E> comp = new StoutComparator<E>();
      int originalSize = size * nodeSize;
      for (int i = 0; i < size; i++) {
          for (int j = 0; j < nodeSize; j++) {
              allData[k] = it.next();
              it.remove();
              k++;
          }
      }
      insertionSort(allData, comp);
      k = 0;
      it = (StoutListIterator) this.listIterator();
      for (int i = 0; i < size; i++) {
          for (int j = 0; j < originalSize; j++) {
              it.add(allData[j]);
          }
      }

  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() {
      E[] allData = (E[]) new Comparable[size * nodeSize];
      StoutListIterator it = (StoutListIterator) this.listIterator();
      for (int i = 0; i < size; i++) {
          for (int j = 0; j < (nodeSize * size); j++) {
              allData[j] = it.next();
          }
      }
      bubbleSort(allData);
      it = (StoutListIterator) this.listIterator();
      for (int i = 0; i < size; i++) {
          for (int j = 0; j < (nodeSize * size); j++) {
              it.add(allData[j]);
          }
      }
  }
  
  @Override
  public Iterator<E> iterator() {
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator() {
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter)
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item) {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item) {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset) {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }

  }
 
  private class StoutListIterator implements ListIterator<E> {

      private Node cursor;
      private E passed;
      private int index;
      private int indexNext;
      private int called;
	  
    /**
     * Default constructor 
     */
    public StoutListIterator() {
    	this(0);
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos) {

        if (pos > size) {
            throw new IllegalArgumentException();
        }

        cursor = head;
        indexNext = pos;
        if (pos >= nodeSize) {
            index = pos % size;
            cursor = cursor.next;
        } else {
            index = pos;
        }
    }

    @Override
    public boolean hasNext() {
        if (index <= nodeSize - 1) {
            if (cursor.next.data[index] == null && cursor.next.next.count != 0) {
                cursor = cursor.next;
                index = 0;
            }
            return cursor.next.data[index] != null;
        }

        cursor = cursor.next;
        index = 0;
        return cursor.next.data[index] != null;
    }

    @Override
    public E next() {
        if (hasNext()) {
            if (index >= nodeSize) {
                cursor = cursor.next;
                index = 0;
            }
            passed = cursor.next.data[index];
            index++;
            indexNext++;
            called = 1;
            return passed;
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public boolean hasPrevious() {

        boolean bool;
        if (index == 0) {
            if (indexNext == 0) { // check if the index is 0, meaning there is no previous by default
                return false;
            }
            cursor = cursor.previous;
            index = nodeSize;
            if (cursor.data == head.next.data) { // check if the cursor data matches the data of the node after the head
                passed = cursor.data[index - 1];
                bool = cursor.data[index - 1] != null;
                boolCheck(bool, index, indexNext);
                return bool;
            }
            passed = cursor.next.data[index - 1];
            bool = cursor.next.data[index - 1] != null;
            boolCheck(bool, index, indexNext);
            return bool;
        } else if (nodeSize < indexNext) { // check for previous items when the nodesize is less than the next index
            if(cursor.previous.count == 0){
                passed = cursor.next.data[index - 1];
                bool = cursor.next.data[index - 1] != null;
                boolCheck(bool, index, indexNext);
                return bool;
            }
            passed = cursor.data[index - 1];
            bool = cursor.data[index - 1] != null;
            boolCheck(bool, index, indexNext);
            return bool;
        } else if (indexNext == nodeSize && index == nodeSize) {
            if (cursor.data == head.next.data) {
                passed = cursor.data[index - 1];
                bool = cursor.data[index - 1] != null;
                boolCheck(bool, index, indexNext);
                return bool;
            }
            cursor = cursor.next;
            passed = cursor.data[index - 1];
            bool = cursor.data[index - 1] != null;
            boolCheck(bool, index, indexNext);
            return bool;
        }
        passed = cursor.data[index - 1];
        bool = cursor.data[index - 1] != null;
        boolCheck(bool, index, indexNext);
        return bool;

    }

      /**
       * Helper method to decrease the two indexes if the boolean is true
       * @param bool
       * @param index
       * @param indexNext
       */
    private void boolCheck(boolean bool, int index, int indexNext) {
        if(bool){
            index--;
            indexNext--;
        }
    }

    @Override
    public E previous() {

        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        called = -1;
        return passed;
    }

    @Override
    public int nextIndex() {
        return indexNext;
    }

    @Override
    public int previousIndex() {
        indexNext -= 1;
        return indexNext;
    }

    @Override
    public void remove() {
        if(called == 0){
            throw new IllegalStateException();
            //next
        }else if(called == 1){
            cursor.next.removeItem(index - 1);
            indexNext--;
            //previous
        }else if(called == -1){
            if(indexNext > nodeSize){
                cursor = cursor.next;
            }
            cursor.next.removeItem(index -1);
        }
        //collapse
        if(cursor.count < nodeSize / 2 && cursor.count != 0){
            if(cursor.next.count <= nodeSize / 2){
                for(int i = 0; i < nodeSize / 2; i++){
                    if(cursor.data[index - 1] == null){
                        cursor.addItem(index - 1, cursor.next.data[i]);
                    }else{
                        cursor.addItem(index, cursor.next.data[i]);
                    }
                    index++;
                }
                for(int i = 0; i < nodeSize / 2; i++){
                    cursor.next.removeItem(i);
                }
                if(head != null){
                    cursor.next = tail;
                }
            }else{
                if(cursor.data[index - 1] == null){
                    cursor.addItem(index - 1, cursor.next.data[0]);
                }else{
                    cursor.addItem(index, cursor.next.data[0]);
                }
                index++;
                cursor.next.removeItem(0);
            }
        }else if(cursor.count == 0 && cursor.next.count < nodeSize / 2){
            if(cursor.next.next.count > nodeSize / 2){
                if(cursor.next.data[index - 1] == null){
                    cursor.next.addItem(index - 1, cursor.next.next.data[0]);
                }else{
                    cursor.next.addItem(index, cursor.next.next.data[0]);
                }
                cursor.next.next.removeItem(0);
            }else if(cursor.next.next.count == nodeSize / 2){
                if(cursor.next.data[index - 1] == null){
                    cursor.next.addItem(index - 1, cursor.next.next.data[0]);
                }else{
                    cursor.next.addItem(index, cursor.next.next.data[0]);
                }
                index++;
                cursor.next.next.removeItem(0);
            }
            if(cursor.next.next.count < nodeSize / 2){
                if(cursor.next.data[index - 1] == null){
                    cursor.next.addItem(index - 1, cursor.next.next.data[0]);
                }else{
                    cursor.next.addItem(index, cursor.next.next.data[0]);
                }
                cursor.next.next.removeItem(0);
            }
            index++;
            if(cursor.count == 0 & cursor.next.next.count == 0){
                head.next.next = tail;
            }
        }
        if(cursor.count == 0 & cursor.next.count == 0){
            head.next = tail;
        }
        index--;
        size--;
        called = 0;

    }

    @Override
    public void set(E e) {
        if (called == -1) {
            cursor.next.data[index] = e;
        } else if (called == 1) {
            cursor.next.data[index - 1] = e;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void add(E e) {

        if (cursor.next.count == nodeSize) {
            Node cur = head.next;
            Node tmp = new Node();
            int index = 0;
            addLoop(cur, tmp, index);
            cur.addItem(indexNext, e);
            if (head != null) {
                tmp.next = cursor.next;
                cursor.next = tmp;
                head.next = cursor;
            }
        } else {
            cursor.next.addItem(indexNext % nodeSize, e);
        }

        indexNext++;
        size++;
        called = 0;

    }

  }

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp) {
      int n = arr.length;
      for (int i = 1; i < n; ++i) {
          E key = arr[i];
          int j = i - 1;

          while (j >= 0 && arr[j].compareTo(key) > 0) {
              arr[j + 1] = arr[j];
              j = j - 1;
          }
          arr[j + 1] = key;

      }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr) {
      int n = arr.length;
      for(int i = 0; i < n - 1; i++) {
          for (int j = 0; j < n - i - 1; j++) {
              if(arr[j].compareTo(arr[j+1]) < 0) {
                  E temp = arr[j];
                  arr[j] = arr[j+1];
                  arr[j+1] = temp;
              }
          }
      }

  }

  public static void main(String[] args) {

      StoutList<String> list = new StoutList<String>(4);
      list.add("A");
      list.add("B");
      list.add("C");
      list.add("D");

      ListIterator<String> iter = list.listIterator(1);

      iter = list.listIterator();
      iter.next();
      iter.next();
      iter.add("P");
      iter.previous();
      iter.add("Q");
      iter.next();
      iter.remove();
      System.out.println(iter.nextIndex());

      System.out.println(list.toStringInternal(iter));

  }

  /* Used for Testing */
  /*public static void main(String[] args) {
      StoutList<Integer> list = new StoutList<Integer>(4);
      list.add(1);


      ListIterator<Integer> it = list.listIterator(1);
      System.out.println(list.toStringInternal(it));


      list = new StoutList<Integer>(4);

      list.add(1);
      list.add(2);

      it = list.listIterator(1);
      System.out.println(list.toStringInternal(it));

      it = list.listIterator(2);
      System.out.println(list.toStringInternal(it));

      list = new StoutList<Integer>(4);

      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);
      list.add(5);

      it = list.listIterator(5);
      System.out.println(list.toStringInternal(it));

      list = new StoutList<Integer>(4);

      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);
      list.add(5);
      list.add(6);

      it = list.listIterator(6);
      System.out.println(list.toStringInternal(it));

      list = new StoutList<Integer>(4);

      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);
      list.add(5);
      list.add(6);
      list.add(7);

      it = list.listIterator(7);
      System.out.println(list.toStringInternal(it));

      it.previous();

      System.out.println(list.toStringInternal(it));

      it.next();

      System.out.println(list.toStringInternal(it));

      it.remove();

      System.out.println(list.toStringInternal(it));

      it.previous();

      System.out.println(list.toStringInternal(it));

      it.remove();

      System.out.println(list.toStringInternal(it));
  }
   */

}