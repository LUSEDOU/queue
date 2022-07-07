import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private Item[] r;
    private int n;

    // construct an empty deque
    public RandomizedQueue() {
        r = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public boolean isEmpty()    {   return n == 0;            }    
    public int size()           {   return n;                 }

    private int randomIndex() {
        int rIndex = 0;             // final index
        int con = 0;        // contestant
        double p = 1.0;

        while (con < n) {
            if (StdRandom.bernoulli(1 / p)) rIndex = con;
            con++;
            p++;
        }
        return rIndex;
    }
    
    // add the item to the front
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == r.length) resize(2 * n);
        r[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int rIndex = randomIndex();
        Item item = r[rIndex];
        if (rIndex == (n - 1)) --n;
        else r[rIndex] = r[--n];
        r[n] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        int rIndex = randomIndex();
        return r[rIndex];
    }

    public Iterator<Item> iterator() { return new ArrayIterator(r, n); }

    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private final Item[] arrayI;
    
        public ArrayIterator(Item[] array, int n) {
            arrayI = array;
            i = n;

            StdRandom.shuffle(arrayI, 0, i);
        }

        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException(); }
        
        public Item next() { 
            if (!hasNext()) { throw new java.util.NoSuchElementException(); }
            return arrayI[--i];
        }
    }

    // resize the underlaying array, 
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = r[i];
        }
        r = copy;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rQueque = new RandomizedQueue<String>();

        rQueque.enqueue("item0");
        rQueque.enqueue("item1");
        rQueque.enqueue("item2");
        StdOut.println(rQueque.size());
        StdOut.println(rQueque.isEmpty());
        StdOut.println(rQueque.iterator().next());
        StdOut.println(rQueque.sample());
        StdOut.println(rQueque.dequeue());
    }
}
