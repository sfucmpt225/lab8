# lab8

For lab 8, you will implement the `PriorityQueue` class that keeps its values in a binary min-heap stored in an array. You will write eight methods. Three methods work out where a node's parent and children are in the array. Three methods add, look at, and remove values respectively. And finally a constructor that turns a whole array into a heap at once and heap sorts it. 

## Objectivies

- Storing a complete binary tree in an array and moving between a node and its parent or children with index arithmetic.
- Keeping the heap order when a value is added (sifting up) and when the smallest value is removed (sifting down).
- Building a heap from an array in O(n) time and using a priority queue to sort.

## Files

| File | What to do |
|:--|:--|
| `README.md` | These instructions. |
| `PriorityQueue.java` | Complete the eight methods marked `TODO`. |
| `Main.java` | Example tests. Run it to check your work. |

Each of the eight methods currently throws an `UnsupportedOperationException`. Replace that line with your code. The files compile as they are, so you can work on one method at a time.

## Heaps in this assignment

A **binary min-heap** is a binary tree with two properties:

- **It is complete:** every level is full, except perhaps the bottom one, which is filled from the left with no gaps.
- **It is in heap order:** every node's value is smaller than or equal to the values of its children. So the smallest value is always at the root.

Here is a heap with seven values. Below it is the same heap stored in an array, level by level, from left to right:

```text
               1
            /     \
           3       2
          / \     / \
         7   4   9   5

index:   0  1  2  3  4  5  6
heap:  [ 1, 3, 2, 7, 4, 9, 5 ]
```

Because the tree is complete, the array has no gaps, and you can find a node's family with arithmetic alone:

| For the node at index `i` | Index |
|:--|:--|
| its parent (if `i > 0`) | `(i - 1) / 2` (integer division) |
| its left child | `2 * i + 1` |
| its right child | `2 * i + 2` |

For example, the node at index 1 (value 3) has its children at indexes 3 and 4 (values 7 and 4), and its parent at index 0.

The queue's values are in `heap[0]` to `heap[size - 1]`. Everything from `heap[size]` on is unused space, whatever it holds: a child exists only if its index is less than `size`. In the heap above (`size` is 7), the node at index 3 would have its children at indexes 7 and 8, so it has none: it is a leaf.


## The provided code

The `PROVIDED CODE` section at the top of `PriorityQueue.java` gives you:

| Member | What it is |
|:--|:--|
| `int[] heap` and `int size` | The queue's only two fields, as described above. |
| `DEFAULT_CAPACITY` | 10, the length of the array in a new, empty queue. |
| `PriorityQueue()` | Creates an empty queue. |
| `size()`, `isEmpty()` | The number of values in the queue, and whether it is 0. |
| `toArray()`, `toString()` | A copy of `heap[0]` to `heap[size - 1]`, and the same values as a `String`. |
| `swap(i, j)` | Swaps `heap[i]` and `heap[j]`. |
| `grow()` | Replaces `heap` with an array twice as long that holds the same values. Call it when the array is full. |

## Rules

1. **Keep the provided code.** Do not change anything in the `PROVIDED CODE` section of `PriorityQueue.java`. Do not rename the class or the eight methods, change their parameters or return types, or replace the `package` line. I will set up my test queues by writing the `heap` and `size` fields directly, and check your results by reading them.
2. **Do not add fields.** Do not add instance variables or static variables: a queue is exactly its `heap` and its `size`. Use parameters, local variables and private helper methods instead. You may add as many private helper methods as you like; `siftUp`, `siftDown`, etc.
3. **Write the heap yourself.** Do not use `java.util.PriorityQueue`, `TreeSet`, `TreeMap` or any sorting method from the Java library (`Arrays.sort`, `Collections.sort`, `List.sort`, or a stream's `sorted`). 
4. **Exceptions.** The only exception your code may throw is an `IllegalStateException` from `peek()` or `poll()` when the queue is empty. Do not call `System.exit`.

## Testing

Main runs the examples above and prints `PASS`, `FAIL`, `ERROR` (an exception was thrown) or `NOT DONE` for each one:

```text
insert(int)
  PASS      insert 5 into []: [5]
  FAIL      insert 10, 20, 30, 5 (5 moves up to the root): expected [5, 10, 30, 20], got [10, 5, 30, 20]
  PASS      insert 1, 3, 2, 7, 4 (no value moves): [1, 3, 2, 7, 4]
  PASS      insert 1, 3, 6, 7, 4, then 2 (2 stops below the root): [1, 3, 2, 7, 4, 6]
  ERROR     insert 15, 14, 13, ..., 1 (the array must grow): threw ArrayIndexOutOfBoundsException at line 140 of PriorityQueue.java: Index 10 out of bounds for length 10
  ...

Passed 27 of 32 checks.
```

I will use my own queues and arrays (empty and full arrays, heaps where a value moves all the way, part of the way or not at all, repeated and negative values, larger random heaps), so passing every check in `Main` does not guarantee full marks. Add your own checks: copy a `check(...)` line in `main` and change the values and the expected answer.

## Rubric

40 test cases × 1 mark = 40 marks. 

- **Compiling.** If `PriorityQueue.java` does not compile, the whole assignment scores **0**.
- **Test cases.** Each test case runs one method on one or more queues or arrays, each made fresh. A test case earns its mark only if every result in it is right. 
- **Unexpected exceptions.** If a method throws an exception that this README does not allow, in **any** of its test cases, that method scores **0** for **all** of its test cases. This includes an `ArrayIndexOutOfBoundsException` (for example, from a missing `grow()` or from reading a child that does not exist), a `NullPointerException`, a `StackOverflowError`, the `UnsupportedOperationException` of a method you have not done, a `NoSuchElementException` from an empty queue (it must be an `IllegalStateException`), and an `IllegalStateException` from `peek()` or `poll()` on a queue that is not empty. Calling `System.exit` counts the same way.
- **Time limit.** A test case that runs for more than 5 seconds (for example, because of an infinite loop) scores 0.

| Method | Marks | What its test cases check |
|:--|:--|:--|
| `parent(int)` | 2 | 1. small indices (1 to 4) · 2. larger indices |
| `leftChild(int)` | 2 | 1. small indices (0 to 2) · 2. larger indices |
| `rightChild(int)` | 2 | 1. small indices (0 to 2) · 2. larger indices |
| `insert(int)` | 8 | 1. into an empty queue · 2. the new value moves up one level · 3. the new value moves all the way up to the root · 4. the new value stops part of the way up · 5. nothing moves: the new value is not smaller than its parent · 6. the array is full, so it must grow · 7. repeated values · 8. longer random sequences |
| `peek()` | 4 | 1. a queue with one value · 2. several values (the queue must not change) · 3. negative values and zero · 4. an empty queue (must throw `IllegalStateException`) |
| `poll()` | 10 | 1. a queue with one value · 2. two and three values · 3. swapping with the smaller child (the right one in one heap, the left one in another) · 4. the moved value sinks to the bottom · 5. the moved value stops part of the way down · 6. a node with only a left child · 7. an empty queue (must throw `IllegalStateException`) · 8. polling until the queue is empty, with repeated values · 9. polling until the queue is empty: the exact heap after every poll · 10. larger random heaps |
| `PriorityQueue(int[])` | 6 | 1. an empty array and a single value · 2. small arrays · 3. arrays in descending order (the last parent has only a left child) · 4. the queue keeps its own copy of the array · 5. repeated and negative values · 6. larger random arrays |
| `heapSort(int[])` | 6 | 1. an empty array and a single value · 2. small arrays · 3. repeated and negative values · 4. the argument is not changed, and a new array is returned · 5. sorted and reverse-sorted arrays · 6. large random arrays (up to 1000 values) |

## Submission
Zip the project directory and submit the ZIP file to Canvas.
**Please do not remove any build artifacts or manifest files generated by NetBeans, and do not modify the directory structure. Zip the project directory exactly as it is.**

**Your program must compile and execute successfully. If the program fails to compile or crashes during execution, the submission will receive a grade of zero.**

## Deadline
Sunday, November 22, 2026, at 11:59 PM PDT

