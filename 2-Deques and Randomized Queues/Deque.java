/* Amitoj Singh created this file on August 19, 2017.

Dequeue: A double-ended queue or deque (pronounced "deck") is a generalization
of a stack and a queue that supports adding and removing items from either the
front or the back of the data structure.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // Construct an empty deque.
    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return (first == null || last == null);
    }

    // Return the number of items on the deque.
    public int size() {
        return count;
    }

    // Add the item to the front.
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Invalid item");
        Node oldFirst = first;
        first = new Node();
        if (oldFirst != null) oldFirst.previous = first;
        first.item = item;
        first.next = oldFirst;
        if (first.next == null) first.next = first;
        if (last == null) last = first;
        first.previous = last;
        count++;
    }

    // Add the item to the end.
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Invalid item");
        Node oldLast = last;
        last = new Node();
        if (oldLast != null) oldLast.next = last;
        last.item = item;
        last.previous = oldLast;
        if (last.previous == null) last.previous = last;
        if (first == null) first = last;
        last.next = first;
        count++;
    }

    // Remove and return the item from the front.
    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException("Invalid item");
        if (first == last) {
            Item item = first.item;
            first.item = null;
            first.next = null;
            first.previous = null;
            first = null;
            last = first;
            count--;
            return item;
        }

        Item item = first.item;
        Node node = first.next;
        first.item = null;
        first.next = null;
        first.previous = null;
        first = node;
        first.previous = last;
        count--;
        return item;
    }

    // Remove and return the item from the end.
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException("Invalid item");
        if (last == first) {
            Item item = last.item;
            last.item = null;
            last.next = null;
            last.previous = null;
            last = null;
            first = last;
            count--;
            return item;
        }
        Item item = last.item;
        Node node = last.previous;
        last.item = null;
        last.next = null;
        last.previous = null;
        last = node;
        last.next = first;
        count--;
        return item;
    }

    // Return an iterator over items in order from front to end.
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // TODO
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private int i = 0;

        public boolean hasNext() {
            return i < count;
        }

        public Item next() {
            if (i >= count) throw new NoSuchElementException("Invalid item");
            Item item = current.item;
            current = current.next;
            i++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported");
        }
    }
}
