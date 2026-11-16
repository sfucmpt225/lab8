import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/*
 * Assignment: Priority Queues (ADS101): example tests
 *
 * Runs some checks on the methods in PriorityQueue.java and prints PASS or
 * FAIL for each one. Compile and run it from the folder that holds both files:
 *
 *     javac PriorityQueue.java Main.java
 *     java Main
 *
 * Main.java is NOT marked. I will run my own tests, on other heaps as
 * well, so passing every check here does not guarantee full marks. Add your
 * own checks: copy a check(...) line and change the values and the answer.
 *
 * Heaps are written as arrays in level order, e.g. [1, 3, 2, 7, 4], exactly
 * as PriorityQueue.toString() writes them.
 */
public class Main {

    public static void main(String[] args) {
        section("parent(int), leftChild(int), rightChild(int)");
        check("parent(1)", 0, () -> PriorityQueue.parent(1));
        check("parent(2)", 0, () -> PriorityQueue.parent(2));
        check("parent(5)", 2, () -> PriorityQueue.parent(5));
        check("leftChild(0)", 1, () -> PriorityQueue.leftChild(0));
        check("leftChild(3)", 7, () -> PriorityQueue.leftChild(3));
        check("rightChild(0)", 2, () -> PriorityQueue.rightChild(0));
        check("rightChild(3)", 8, () -> PriorityQueue.rightChild(3));

        // From here on, the queues are built with insert(), so do insert() first.
        section("insert(int)");
        check("insert 5 into []", "[5]", () -> inserted(5));
        check("insert 10, 20, 30, 5 (5 moves up to the root)", "[5, 10, 30, 20]", () -> inserted(10, 20, 30, 5));
        check("insert 1, 3, 2, 7, 4 (no value moves)", "[1, 3, 2, 7, 4]", () -> inserted(1, 3, 2, 7, 4));
        check("insert 1, 3, 6, 7, 4, then 2 (2 stops below the root)", "[1, 3, 2, 7, 4, 6]", () -> inserted(1, 3, 6, 7, 4, 2));
        check("insert 15, 14, 13, ..., 1 (the array must grow)", "[1, 6, 2, 9, 7, 5, 3, 15, 12, 13, 8, 14, 10, 11, 4]",
                () -> inserted(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        check("insert 4, 4, 2, 4, 1 (repeated values): is it a valid heap?", true, () -> isHeap(queueOf(4, 4, 2, 4, 1)));
        check("insert 4, 4, 2, 4, 1 (repeated values): size()", 5, () -> queueOf(4, 4, 2, 4, 1).size());

        section("peek()");
        check("peek() on [5, 10, 30, 20]", 5, () -> queueOf(10, 20, 30, 5).peek());
        check("[5, 10, 30, 20] after peek() (nothing is removed)", "[5, 10, 30, 20]", () -> afterPeek(queueOf(10, 20, 30, 5)));
        checkThrows("peek() on []", IllegalStateException.class, () -> new PriorityQueue().peek());

        section("poll()");
        check("poll() on [1, 3, 2, 7, 4] returns", 1, () -> queueOf(1, 3, 2, 7, 4).poll());
        check("[1, 3, 2, 7, 4] after poll()", "[2, 3, 4, 7]", () -> afterPoll(queueOf(1, 3, 2, 7, 4)));
        check("[1, 3, 2, 7, 4, 9, 5] after poll()", "[2, 3, 5, 7, 4, 9]", () -> afterPoll(queueOf(1, 3, 2, 7, 4, 9, 5)));
        check("[10, 20, 30, 40, 50] after poll() (20 has only a left child)", "[20, 40, 30, 50]",
                () -> afterPoll(queueOf(10, 20, 30, 40, 50)));
        check("insert 5, 1, 4, 1, 3, then poll() 5 times", "[1, 1, 3, 4, 5]", () -> pollAll(queueOf(5, 1, 4, 1, 3)));
        checkThrows("poll() on []", IllegalStateException.class, () -> new PriorityQueue().poll());

        section("PriorityQueue(int[])");
        check("new PriorityQueue({})", "[]", () -> new PriorityQueue(new int[]{}).toString());
        check("new PriorityQueue({3, 1, 2})", "[1, 3, 2]", () -> new PriorityQueue(new int[]{3, 1, 2}).toString());
        check("new PriorityQueue({5, 9, 4, 8, 1, 7})", "[1, 5, 4, 8, 9, 7]",
                () -> new PriorityQueue(new int[]{5, 9, 4, 8, 1, 7}).toString());
        check("new PriorityQueue({7, 3, 9, 1, 5, 8, 2, 6, 4, 0})", "[0, 1, 2, 4, 3, 8, 9, 6, 7, 5]",
                () -> new PriorityQueue(new int[]{7, 3, 9, 1, 5, 8, 2, 6, 4, 0}).toString());
        check("the array {5, 9, 4, 8, 1, 7} after new PriorityQueue(array) (it must not change)", "[5, 9, 4, 8, 1, 7]",
                () -> arrayAfterConstructor(new int[]{5, 9, 4, 8, 1, 7}));

        section("heapSort(int[])");
        check("heapSort({5, 9, 4, 8, 1, 7})", "[1, 4, 5, 7, 8, 9]", () -> sorted(new int[]{5, 9, 4, 8, 1, 7}));
        check("heapSort({4, -1, 4, 0})", "[-1, 0, 4, 4]", () -> sorted(new int[]{4, -1, 4, 0}));
        check("heapSort({})", "[]", () -> sorted(new int[]{}));
        check("the array {3, 1, 2} after heapSort(array) (it must not change)", "[3, 1, 2]",
                () -> arrayAfterHeapSort(new int[]{3, 1, 2}));

        // Add your own checks here. For example:
        // check("insert 9, 8, 7", "[7, 9, 8]", () -> inserted(9, 8, 7));

        System.out.println();
        System.out.println("Passed " + passed + " of " + total + " checks.");
        if (errors > 0) {
            System.out.println("Careful: " + errors + " check(s) threw an exception. When your work is marked,"
                    + " a method that throws an exception the README does not allow scores 0.");
        }
    }

    // -------------------------------------------------------------------------
    //  Small helpers used by the checks above.
    // -------------------------------------------------------------------------

    /** A new queue with the values inserted one by one, in this order. */
    static PriorityQueue queueOf(int... values) {
        PriorityQueue queue = new PriorityQueue();
        for (int value : values) {
            queue.insert(value);
        }
        return queue;
    }

    /** The heap after inserting the values one by one into an empty queue. */
    static String inserted(int... values) {
        return queueOf(values).toString();
    }

    /** Calls peek() once, then returns the heap. */
    static String afterPeek(PriorityQueue queue) {
        queue.peek();
        return queue.toString();
    }

    /** Calls poll() once, then returns the heap. */
    static String afterPoll(PriorityQueue queue) {
        queue.poll();
        return queue.toString();
    }

    /** Calls poll() once for every value in the queue and returns the values it gave. */
    static String pollAll(PriorityQueue queue) {
        List<Integer> values = new ArrayList<>();
        int n = queue.size();
        for (int i = 0; i < n; i++) {
            values.add(queue.poll());
        }
        return values.toString();
    }

    /** True if every value in the queue's heap is at least as large as its parent. */
    static boolean isHeap(PriorityQueue queue) {
        int[] heap = queue.toArray();
        for (int i = 1; i < heap.length; i++) {
            if (heap[(i - 1) / 2] > heap[i]) {
                return false;
            }
        }
        return true;
    }

    /** heapSort's answer, written like "[1, 2, 3]". */
    static String sorted(int[] values) {
        return Arrays.toString(PriorityQueue.heapSort(values));
    }

    /** Builds a queue from the array, then returns the array (which should not have changed). */
    static String arrayAfterConstructor(int[] values) {
        new PriorityQueue(values);
        return Arrays.toString(values);
    }

    /** Sorts the array with heapSort, then returns the array (which should not have changed). */
    static String arrayAfterHeapSort(int[] values) {
        PriorityQueue.heapSort(values);
        return Arrays.toString(values);
    }

    // -------------------------------------------------------------------------
    //  Helpers that run a check and print the result. You do not need to read
    //  them to use them.
    // -------------------------------------------------------------------------

    private static int passed = 0;
    private static int total = 0;
    private static int errors = 0;

    /** Prints a heading for the checks of one method. */
    static void section(String method) {
        System.out.println();
        System.out.println(method);
    }

    /** Runs code and checks that it returns expected. */
    static void check(String what, Object expected, Supplier<Object> code) {
        total++;
        Object actual;
        try {
            actual = code.get();
        } catch (RuntimeException | Error e) {
            reportException(what, e);
            return;
        }
        if (Objects.equals(expected, actual)) {
            passed++;
            print("PASS", what + ": " + actual);
        } else {
            print("FAIL", what + ": expected " + expected + ", got " + actual);
        }
    }

    /** Runs code and checks that it throws an exception of the given type. */
    static void checkThrows(String what, Class<? extends Throwable> expected, Supplier<Object> code) {
        total++;
        Object actual;
        try {
            actual = code.get();
        } catch (RuntimeException | Error e) {
            if (expected.isInstance(e)) {
                passed++;
                print("PASS", what + ": threw " + e.getClass().getSimpleName());
            } else {
                reportException(what, e);
            }
            return;
        }
        print("FAIL", what + ": expected " + expected.getSimpleName() + " to be thrown, but got " + actual);
    }

    private static void reportException(String what, Throwable e) {
        String message = e.getMessage() == null ? "" : e.getMessage();
        if (e instanceof UnsupportedOperationException && message.endsWith("not implemented yet")) {
            print("NOT DONE", what + ": " + message);
            return;
        }
        errors++;
        String text = what + ": threw " + e.getClass().getSimpleName();
        for (StackTraceElement frame : e.getStackTrace()) {
            if (frame.getClassName().startsWith("PriorityQueue")) {
                text += " at line " + frame.getLineNumber() + " of PriorityQueue.java";
                break;
            }
        }
        if (e instanceof StackOverflowError) {
            text += " (infinite recursion? Check your base case)";
        } else if (!message.isEmpty()) {
            text += ": " + message;
        }
        print("ERROR", text);
    }

    private static void print(String status, String text) {
        System.out.printf("  %-9s %s%n", status, text);
    }
}
