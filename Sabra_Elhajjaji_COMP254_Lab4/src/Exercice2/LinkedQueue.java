package Exercice2;

/**
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedQueue<E> implements Queue<E> {

    /** The primary storage for elements of the queue */
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty  list

    /** Constructs an initially empty queue. */
    public LinkedQueue() { }                  // new queue relies on the initially empty list

    /**
     * Returns the number of elements in the queue.
     * @return number of elements in the queue
     */
    @Override
    public int size() { return list.size(); }

    /**
     * Tests whether the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    /**
     * Inserts an element at the rear of the queue.
     * @param element  the element to be inserted
     */
    @Override
    public void enqueue(E element) { list.addLast(element); }

    /**
     * Returns, but does not remove, the first element of the queue.
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() { return list.first(); }

    /**
     * Removes and returns the first element of the queue.
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() { return list.removeFirst(); }

    /** Produces a string representation of the contents of the queue.
     *  (from front to back). This exists for debugging purposes only.
     */
    public String toString() {
        return list.toString();
    }

    public static <E> void concatenate(LinkedQueue<E> sourceQ,LinkedQueue<E> targertQ){

        while(!sourceQ.isEmpty()) {
            targertQ.enqueue(sourceQ.dequeue());
        }
    }

    public static void main(String arges[]){
        LinkedQueue<Integer> sourceQ = new LinkedQueue<>();
        LinkedQueue<Integer> targetQ = new LinkedQueue<>();

        // Enqueue elements into sourceQ
        sourceQ.enqueue(1);
        sourceQ.enqueue(2);
        sourceQ.enqueue(3);
        sourceQ.enqueue(4);
        sourceQ.enqueue(5);

        // Enqueue elements into targetQ
        targetQ.enqueue(10);
        targetQ.enqueue(20);

        System.out.println("Source Queue before concatenation: " + sourceQ);
        System.out.println("Target Queue before concatenation: " + targetQ);

        // Perform concatenation
        concatenate(sourceQ, targetQ);

        System.out.println("Source Queue after concatenation: " + sourceQ);
        System.out.println("Target Queue after concatenation: " + targetQ);
    }

}


