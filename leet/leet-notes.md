7: Reverse Integer

- **Native types**
- must work with native types in order to reverse integer
- think modulo, deriving tens place, accounting for overflow

13: Roman to integer

- **Case Analysis**
- solve general cases, then discuss edge
- save room for add-on, integer-to-roman

21: Merge Two Sorted Lists

- **Merge Sort Variant**
- Can you solve in one pass?
- Use the finger-weave technique for list one and two

23: Merge K Sorted Lists

- **Min-Heap or Finger-Weave**
- consider cycles and creating new nodes
- if finger-weave, careful with dummy head and the list representation you return
- variation of merge 2 sorted lists

54: Spiral Matrix

- **2D matrix**
- can use enum and object-oriented here
- careful to think of edge cases (many)

56: Merge Intervals

- Greedy Algorithm: the greedy choice property is always choose maximum end times
- Sort by start time so we only have two kinds of interval cases - disjoint and overlapping

62: Unique Paths

- **Dynamic Programming**
- to determine # unique ways reachable, think of dynammic programming and 2D matrix. What does each subproblem depend on?

64: Minimum Path Sum

- **Dynamic Programming**
- After defining the recurrence relation, careful implementing the relation. i.e. fill `[x][0]`, `[0][y]`, then the recurrence relation

70: Climbing Stairs

- **Dynamic Programming**
- We only need to keep track of the last two steps to reach the current step i.e. DP(i) = DP(i-1) + DP(i-2)
- How about representing the preceeding calculations of steps as an array?
  - This will take O(n) space, can we do this in constant space O(1)?
- hint, this a similar to fibonacci sequence problem

94: Binary Tree Inorder Traversal

- Convert recursive algorithm to iterative
- Careful how you model visiting a node i.e. what indicates when we should add the node as visited, and when to add new nodes on to the stack

108: Convert Sorted Array to Binary Search Tree

- what are the base cases
- how can we solve the left subtree and right subtree recursively?

109: House Robber 1

110: Implement a Trie

- a trie is a structure for fast retrieval checks for prefixes
- we "chain letters" using a tree structure
- we can implement using a list of nodes. At each level there is a list of size the size of the alphabet
- each node in the tree structure contains a letter, a pointer to the child node
- We identify a word as the ending of a character sequence. We can use and store a boolean value within a local node in a list

125: Valid Palindrome

- Classic string problem
- Consider the comparison of uppercase and non-uppercase characters
- Identify what the conditions are to increment/decrement pointers

153: Find minimum in rotated sorted array

- Binary Search
- what is the condition that the target element is the min
- what is the condition that the minimum is in the left partition or in the right partition?
- consider when we have a fully rotated cycle (sorted array)
- and or only a rotated, non-full cycle array

215: Kth largest element in an array

- **Sorting**
- Sorting or priority queues
- O(nlogk) time and O(n) space if we use a priority queue as a max-heap
- Can reduce time if we use quicksort partitioning scheme. We can do this O(n) time - how? see https://youtu.be/hGK_5n81drs?t=1251

230: Kth Smallest in a BST

- What kind of traversal visits nodes in order of smallest to largest?

295: Find Median from Data Stream

- We can use a priority queue for operations such as finding a running median, avg., min., and max
- If we use two heaps, a max-heap for left and min-heap for right - then we can perform operations such as insertion in O(logn) time
  - The worst-case runtime of the algorithm is O(log n), since we need at most one swap on each level of a heap on the path from the inserted node to the root.
- finding the median can be calculated in constant time because we impose a median invariant on insertion

326: Power of Three

- this can be solved without recursion or loops
- how about using logarithm properties?
