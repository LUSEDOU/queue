import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> 
{
    private int size;
    private final Node<Item> first;
    private final Node<Item> last;


    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque()  
    {   
        last = new Node<Item>();
        first = new Node<Item>();
        last.prev = first;
        first.next = last;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() 
    {   return size == 0;   }

    // return the number of items on the deque
    public int size() 
    {   return size;    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        
        Node<Item> third = first.next;
        Node<Item> second = new Node<Item>();
        second.item = item;
        second.next = third;
        second.prev = first;
        first.next = second;
        third.prev = second;
        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> preLast = last.prev;
        Node<Item> x = new Node<Item>();
        x.item = item;
        x.next = last;
        x.prev = preLast;
        last.prev = x;
        preLast.next = x;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        Node<Item> dSecond = first.next;
        Node<Item> nSecond = dSecond.next;
        Item item = dSecond.item;

        first.next = nSecond;
        nSecond.prev = first;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        Node<Item> dPreLast = last.prev;
        Node<Item> nPreLast = dPreLast.prev;
        Item item = dPreLast.item;

        nPreLast.next = last;
        last.prev = nPreLast;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() 
    {   return new LinkedIterator(last);    }

    private class LinkedIterator implements Iterator<Item>
    {
        private Node<Item> current;
        private int index = 0;

        public LinkedIterator(Node<Item> last) 
        {   current = last.prev;    }
        public boolean hasNext()    
        {   return index < size;    }
        public void remove()        
        {   throw new UnsupportedOperationException();  }

        public Item next() 
        {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            index++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String> deque = new Deque<>();

        deque.addFirst("item1");
        deque.addFirst("item2");
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
        StdOut.println(deque.iterator().next());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
    }
}