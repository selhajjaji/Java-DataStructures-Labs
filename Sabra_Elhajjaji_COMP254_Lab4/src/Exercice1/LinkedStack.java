package Exercice1;
/**
 * Realization of a stack as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 * Adapter Pattern
 * The adapter design pattern applies to any context where we effectively want to
 * modify an existing class so that its methods match those of a related, but different,
 * class or interface. One general way to apply the adapter pattern is to define a new
 * class in such a way that it contains an instance of the existing class as a hidden
 * field, and then to implement each method of the new class using methods of this
 * hidden instance variable. By applying the adapter pattern in this way, we have
 * created a new class that performs some of the same functions as an existing class,
 * but repackaged in a more convenient way.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedStack<E> implements Stack<E> {

    /** The primary storage for elements of the stack */
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty list
    //new SinglyLinkedList<>() cane be replaced by new SinglyLinkedList<E>()

    /** Constructs an initially empty stack. */
    public LinkedStack() { }                   // new stack relies on the initially empty list

    /**
     * Returns the number of elements in the stack.
     * @return number of elements in the stack
     */
    @Override
    public int size() { return list.size(); }

    /**
     * Tests whether the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    /**
     * Inserts an element at the top of the stack.
     * @param element   the element to be inserted
     */
    @Override
    public void push(E element) { list.addFirst(element); }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() { return list.first(); }

    /**
     * Removes and returns the top element from the stack.
     * @return element removed (or null if empty)
     */
    @Override
    public E pop() { return list.removeFirst(); }


    public  static <E> void transfer(LinkedStack<E> sourceS,LinkedStack<E> targertS ){

     while(!sourceS.isEmpty()){
         targertS.push(sourceS.pop());
     }

    }

    /** Produces a string representation of the contents of the stack.
     *  (ordered from top to bottom)
     *
     * This exists for debugging purposes only.
     *
     * @return textual representation of the stack
     */
    public String toString() {
        return list.toString();
    }

    //main method
    public static void main(String[] args)
            throws CloneNotSupportedException
    {

        LinkedStack<String> ls = new LinkedStack<String>();
        ls.push("Apple");
        ls.push("Kiwi");
        ls.push("Lime");
        System.out.println( ls.toString() );
        LinkedStack<String> sourceS = ls;
        LinkedStack<String> targetS = new LinkedStack<>();

        System.out.println("Source Stack before transfer: " + sourceS);
        System.out.println("Target Stack before transfer: " + targetS);

        // Perform transfer
        transfer(sourceS, targetS);

        System.out.println("Source Stack after transfer: " + sourceS);
        System.out.println("Target Stack after transfer: " + targetS);

    }
}
