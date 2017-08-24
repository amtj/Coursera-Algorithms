/* Amitoj Singh created this file on August 19, 2017.

Permutation takes a command-line integer k; reads in a sequence of strings from
standard input using StdIn.readString(); and prints exactly k of them, uniformly
at random. Print each item from the sequence at most once.
*/

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (k > 0) {
                randomizedQueue.enqueue(str);
                k--;
            }
        }

        Iterator<String> itr = randomizedQueue.iterator();
        while (itr.hasNext()) {
            String str = itr.next();
            System.out.println(str);
        }
        // for (int i = 0; i < k; i++) {
        //     String str = randomizedQueue.dequeue();
        //     System.out.println(str);
        // }
    }
}
