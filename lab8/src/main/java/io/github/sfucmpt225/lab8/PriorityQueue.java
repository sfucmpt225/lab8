/*
 * A priority queue of int values in which the SMALLEST value has the highest
 * priority: poll() always removes the smallest value. The values are kept in
 * a binary min-heap stored in an array. Complete the methods in the YOUR CODE
 * section at the bottom of this file. README.md explains each method and how
 * your work is marked.
 */
public class PriorityQueue {

    // =========================================================================
    //  PROVIDED CODE: do not change anything in this section.
    //  I will set up my test queues by writing the heap and size fields
    //  directly, and read them to check your results. If you rename or change
    //  these fields, your methods cannot be tested.
    // =========================================================================

    /** The length of the heap array in a new, empty queue. */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The heap. The queue's values are in heap[0] to heap[size - 1], stored
     * level by level: heap[0] is the root, and the children of heap[i] are
     * heap[2*i + 1] and heap[2*i + 2]. The places from heap[size] on are not
     * part of the queue, whatever they hold.
     */
    private int[] heap;

    /** The number of values in the queue. */
    private int size;

    /** Creates an empty queue. */
    public PriorityQueue() {
        heap = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /** Returns the number of values in the queue. */
    public int size() {
        return size;
    }

    /** Returns true if the queue holds no values. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns a copy of heap[0] to heap[size - 1], in array order. */
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = heap[i];
        }
        return copy;
    }

    /**
     * Returns the heap in array order, e.g. "[1, 3, 2, 7, 4]". The empty queue
     * is "[]". README.md and Main.java all write heaps
     * this way.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(heap[i]);
        }
        return s.append("]").toString();
    }

    /** Swaps heap[i] and heap[j]. */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /** Replaces the heap array with one twice as long that holds the same values. */
    private void grow() {
        int[] bigger = new int[Math.max(DEFAULT_CAPACITY, 2 * heap.length)];
        for (int i = 0; i < size; i++) {
            bigger[i] = heap[i];
        }
        heap = bigger;
    }

    // =========================================================================
    //  YOUR CODE: complete the methods below.
    //
    //  - Replace each "throw new UnsupportedOperationException(...)" line
    //    with your code.
    //  - You may add private helper methods. siftUp and siftDown, at the end
    //    of this file, are suggested; they are not marked on their own.
    //  - Do not add fields (instance or static variables).
    //  - Do not use java.util.PriorityQueue or any sorting method from the
    //    Java library (such as Arrays.sort): you are writing them yourself.
    // =========================================================================

    /**
     * Returns the index of the parent of the node at the given index.
     * For example, parent(1) and parent(2) are both 0. You may assume that
     * index >= 1 (the root, at index 0, has no parent).
     */
    public static int parent(int index) {
        // TODO
        throw new UnsupportedOperationException("parent() is not implemented yet");
    }

    /**
     * Returns the index of the left child of the node at the given index.
     * For example, leftChild(0) is 1. The answer may be >= size: then the
     * node has no left child.
     */
    public static int leftChild(int index) {
        // TODO
        throw new UnsupportedOperationException("leftChild() is not implemented yet");
    }

    /**
     * Returns the index of the right child of the node at the given index.
     * For example, rightChild(0) is 2. The answer may be >= size: then the
     * node has no right child.
     */
    public static int rightChild(int index) {
        // TODO
        throw new UnsupportedOperationException("rightChild() is not implemented yet");
    }

    /**
     * Adds value to the queue. Repeated values are allowed.
     *
     * If the array is full, call grow() first. Then put value just after the
     * last value of the heap and move it up (sift up) while it is smaller
     * than its parent.
     */
    public void insert(int value) {
        // TODO
        throw new UnsupportedOperationException("insert() is not implemented yet");
    }

    /**
     * Returns the smallest value in the queue without removing it.
     *
     * @throws IllegalStateException if the queue is empty
     */
    public int peek() {
        // TODO
        throw new UnsupportedOperationException("peek() is not implemented yet");
    }

    /**
     * Removes the smallest value from the queue and returns it.
     *
     * Move the last value of the heap to the root, then move it down (sift
     * down) while its smaller child is smaller than it.
     *
     * @throws IllegalStateException if the queue is empty
     */
    public int poll() {
        // TODO
        throw new UnsupportedOperationException("poll() is not implemented yet");
    }

    /**
     * Creates a queue that holds the values of the given array, arranged into
     * a heap from the bottom up (README.md, method 7). The queue keeps its own
     * copy: the given array must not be changed, and changing it later must
     * not change the queue. You may assume that values is not null.
     */
    public PriorityQueue(int[] values) {
        // TODO
        throw new UnsupportedOperationException("PriorityQueue(int[]) is not implemented yet");
    }

    /**
     * Returns a new array that holds the same values as the given array, in
     * ascending order, sorted with a PriorityQueue. The given array must not
     * be changed. You may assume that values is not null.
     */
    public static int[] heapSort(int[] values) {
        // TODO
        throw new UnsupportedOperationException("heapSort() is not implemented yet");
    }

    /**
     * Suggested helper for insert(): moves the value at the given index up,
     * swapping it with its parent while it is smaller than its parent.
     */
    private void siftUp(int index) {
        // TODO (suggested helper)
    }

    /**
     * Suggested helper for poll() and PriorityQueue(int[]): moves the value at
     * the given index down, swapping it with its smaller child while that
     * child is smaller than it.
     */
    private void siftDown(int index) {
        // TODO (suggested helper)
    }
}
