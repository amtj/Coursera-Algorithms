/* Amitoj Singh created this file on August 19, 2017.

Randomized queue: A randomized queue is similar to a stack or queue, except that
the item removed is chosen uniformly at random from items in the data structure.
*/

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[8];
        count = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return (count == 0);
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Invalid item");

        if (count >= items.length) resize(count*2);
        items[count] = item;
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0) throw new NoSuchElementException("Invalid item");

        int random = StdRandom.uniform(0, count);
        Item item = items[random];
        while (random < count - 1) {
            items[random] = items[random + 1];
            random++;
        }
        items[random] = null;
        count--;
        if (count <= items.length / 4 && count > 0) {
            resize(items.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (items[0] == null) throw new NoSuchElementException("Invalid item");

        int random = StdRandom.uniform(0, count);
        Item item = items[random];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current;
        private final int[] randomInt;
        public ArrayIterator() {
            current = 0;
            randomInt = new int[count];

            for (int i = 0; i < count; i++) {
                randomInt[i] = i;
            }

            StdRandom.shuffle(randomInt);
        }

        public boolean hasNext() {
            return current < count;
        }

        public Item next() {
            if (current >= count) throw new NoSuchElementException("Invalid item");
            Item item = items[randomInt[current]];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported");
        }
    }

    private void resize(int size) {
        Item[] array  = (Item[]) new Object[size];
        for (int i = 0; i < size / 2; i++) {
            array[i] = items[i];
        }

        items = array;
    }
}
