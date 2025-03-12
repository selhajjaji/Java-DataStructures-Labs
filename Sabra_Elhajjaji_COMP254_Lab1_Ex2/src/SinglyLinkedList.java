/**
 * A basic singly linked list implementation.
 *
 * Update: In this update the clone() method was corrected
 * by Arindam Das (the one in the texbook has the error)
 * and sent to one of the authors (Dr. Goldwasser) of the textbook.
 */
//There are 2 categories of type in java: primitive type and class type.
//The generic parameter E below can never be primitive type.
//(Note: Even String is a class type. Thus, the sentence
//String str = "abc"; is a shorthand notation. The java compiler,
//during compilation process, first creates an String object, and then
//stores the a, b, c characters inside that String object.
public class SinglyLinkedList<E> implements Cloneable {
    //---------------- nested Node class ----------------
    /**
     * Node of a singly linked list, which stores a reference to its
     * element and to the subsequent node in the list (or null if this
     * is the last node).
     */
    private static class Node<E> {

        /** The element stored at this node */
        private E element;            // reference (i.e. pointer) to the element stored at this node

        /** A reference to the subsequent node in the list */
        private Node<E> next;         // reference (i.e. pointer) to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        /**
         * Returns the element stored at the node.
         * @return the element stored at the node
         */
        public E getElement() { return element; }

        /**
         * Returns the node that follows this one (or null if no such node).
         * @return the following node
         */
        public Node<E> getNext() { return next; }

        // Modifier methods
        /**
         * Sets the node's next reference to point to Node n.
         * @param n    the node that should follow this one
         */
        public void setNext(Node<E> n) { next = n; }

    } //----------- end of nested Node class -----------

    // instance variables of the SinglyLinkedList
    /** The head node of the list */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /** The last node of the list */
    private Node<E> tail = null;               // last node of the list (or null if empty)

    /** Number of nodes in the list */
    private int size = 0;                      // number of nodes in the list

    /** Constructs an initially empty list. */
    public SinglyLinkedList() { }              // constructs an initially empty list

    // access methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Returns (but does not remove) the first element of the list
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        if (isEmpty()) return null;
        return head.getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // update methods
    /**
     * Adds an element to the front of the list.
     * @param e  the new element to add
     */
    public void addFirst(E e) {                // adds element e to the front of the list
        head = new Node<>(e, head);              // create and link a new node
        if (size == 0)
            tail = head;                           // special case: new node becomes tail also
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param e  the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
        Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
        if (isEmpty())
            head = newest;                         // special case: previously empty list
        else
            tail.setNext(newest);                  // new node after existing tail
        tail = newest;                           // new node becomes the tail
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
        if (isEmpty()) return null;              // nothing to remove
        E answer = head.getElement();
        head = head.getNext();                   // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null;                           // special case as list is now empty
        return answer;
    }



    @SuppressWarnings({"unchecked"})
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
        if (size != other.size) return false;
        Node walkA = head;                               // traverse the primary list
        Node walkB = other.head;                         // traverse the secondary list
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;   // if we reach this, everything matched successfully
    }

    @SuppressWarnings({"unchecked"})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // always use inherited Object.clone() to create the initial copy
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
        if (size > 0) {                    // we need independent chain of nodes
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext();      // walk through remainder of original list
            Node<E> otherTail = other.head;     // remember most recently created node
            while (walk != null) {              // make a new node storing same element
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest);     // link previous node to this one
                otherTail = newest;
                walk = walk.getNext();
            }
            other.tail = otherTail;  //This line is missing in textbook's code. If absent,
            //other.tail will have incorrect reference.
        }
        return other;
    }

    public int hashCode() {
        int h = 0;
        for (Node walk=head; walk != null; walk = walk.getNext()) {
            h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
            h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
        }
        return h;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    //traverses each node and displays its element on the screen
    public void traverse() {
        Node<E> walk = head;
        while (walk != null) {
            E e = walk.getElement();
            System.out.println( e );
            walk = walk.getNext();
        }
    }

    public void swapNodes(int node1Index, int node2Index){

        if(node1Index<1 ||node2Index <1 || node1Index> size || node2Index> size){
            throw new IndexOutOfBoundsException("Invalide index");
        }
        if(node1Index==node2Index) return;
        Node<E> node1=head;
        Node<E> node2=head;

        for(int i=1;i<node1Index;i++) node1=node1.getNext();
        for(int i=1;i<node2Index;i++) node2=node2.getNext();
        E temp=node1.getElement();
        node1.element=node2.getElement();
        node2.element=temp;

    }

    //main method
    public static void main(String[] args)
            throws CloneNotSupportedException
    {

        SinglyLinkedList<String> list = new SinglyLinkedList<String>();

        list.addFirst("MSP");

        list.addLast("ATL");

        list.addLast("BOS");
        list.addLast("LAX");

        System.out.println( list );

        SinglyLinkedList<String> list2 = list.clone();
        //System.out.println(list2);
        if( list != list2)
            System.out.println( "list points to an object that is not pointed to by list2");

        System.out.println( list2 );
        list.traverse();
        System.out.println( "list before swapping nodes :"+list );
        list.swapNodes(1,3);
        System.out.println( "list after swapping nodes at indices 1 and 3:"+list );
        list.swapNodes(2,4);
        System.out.println( "list after swapping nodes at indices 2 and 4:"+list );
    }
}
