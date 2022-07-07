import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;


public class DequeExp<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 12;

    private Item[] d;
    private int head;
    private int tail;
    private int size; 

    // construct an empty deque
    public DequeExp() {
        d = (Item[]) new Object[INIT_CAPACITY];
        tail = 4;
        head = 4;
        size = 0;
    }

    public boolean isEmpty()    {   return size == 0;            }    
    public int size()           {   return size;                 }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException(); 
        checkAndResize();
        d[--head] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException(); 
        checkAndResize();
        d[tail++] = item;
        size++;          
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = d[head];
        d[head++] = null;
        size--;
        checkAndResize(); 
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = d[--tail];
        d[tail] = null;
        size--;
        checkAndResize();
        return item;
    }

    public Iterator<Item> iterator() { return new ArrayIterator(); }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext()    {   return i < size;                            }
        public void remove()        {   throw new UnsupportedOperationException();  }
        
        public Item next() { 
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = d[(i + head) % d.length];
            i++;
            return item;
        }
    }

    // resize the underlaying array, 
    private void resizeExp(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i + (capacity / 3)] = d[head + i];
        }
        d = copy;
        head = capacity / 3;
        tail = (capacity / 3) + size;
    }

    private void checkAndResize() {
        if (head == 0 || tail == d.length && size > 0)
            resizeExp(2 * d.length);      
        else if (size == d.length / 6)
            resizeExp(d.length / 2);
        else if (isEmpty()) {
            head = 1;
            tail = 1;
        };
    }

    public static void main(String[] args) {
        DequeExp<String> deque = new DequeExp<String>();

        StdOut.println(deque.isEmpty());
        deque.addFirst("item0");
        deque.addLast("item1");        
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        StdOut.println(deque.iterator().hasNext());
    }
}
