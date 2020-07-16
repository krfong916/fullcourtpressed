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

39: Combination Sum

- classic backtracking (exhaustive search, choose/unchoose) problem
- how about using a reference to the current index?

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

76: Minimum Window Substring

- **Sliding Window**
- how do we know when we have a valid/invalid window?
- what are the termination conditions?
- Careful choosing your loop expressions

78: Subsets

- Classic backtracking problem **Include/Exclude**
- Objects in java are pass by reference, so mutating the structure will affect all references to it.

79: Word Search

- We must recognize that this problem is a backtracking problem, but also, it's a choose/unchoose problem.
- In a DFS, our goal is to find if a path is reachable from a source vertex to a destination vertex. However, this is not a graph traversal problem - it's a backtracking problem i.e. the choose/unchoose component.
- We must find a _distinct_ combination of characters, this indicates the problem is a backtracking problem

90: Subsets II

- Backtracking Include/Exclude with variation - must not contain duplicate sequences. For example, our result set cannot contain [1,4] and [4,1] if the input is [1,4,1]
- How about sorting the array before computing power set? That way we'll only add unique subsets to our dictionary, and process the numbers in increasing order.
- Example: Given a set [1,4,1], if we sort initially, we'll have [1,1,4]. We'll never end up with [4,1] only [1,4] -> sorted order maintains the uniqueness of subset creation.

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

112: Path Sum

- A tree problem: case analysis and defining states
- Recognize self-similarity

124: Binary Tree Maximum Path Sum

- Hint: How do you consider negative values?
- Hint: What if we use a global variable called max?
- The direction of you're path does not matter, once a path is chosen cannot have a "backtracking" direction in your path. See https://www.youtube.com/watch?v=mOdetMWwtoI at timestamp 7 min. 25 sec.

125: Valid Palindrome

- Classic string problem
- Consider the comparison of uppercase and non-uppercase characters
- Identify what the conditions are to increment/decrement pointers

129: Sum Root To Leaf Numbers

- Translate natural/intuitive way into code
- Define your base cases - what happens at a leaf node?

133: Clone Graph

- This problem can be solved two ways, BFS and DFS
- Hint: Regardless of the graph traversal we chooose, what structure can we use to store a cloned node, and build the neighbors list of a cloned node? Note, the presence of the cloned node within this structure implies that we have visited the node already
- Using a DFS to solve this problem is perhaps the more challenging approach because the algorithm does not follow a textbook DFS "so to speak"

139: Word Break A

- Complexity: `O(length of string * dictionary size)`
- We know we should perform an equivalency check on a possible substring partition and words in the dictionary. The key to an efficient algorithm is in two parts
  - a: calculate partitions based on the starting index and the length of a word in the dictionary
  - b: immediately go to the starting index of the next partition after a partition has been verified

139: Word Break B

- The time is poor if the size of the dictionary is very large for Word Break Solution A
- To improve upon our algorithm's time, we can make our algorithm a function of the string instead of a function of the dictionary in exchange for using a little more space
- We can get to an `O(n^2)` runtime where `n` is the length of the string

153: Find minimum in rotated sorted array

- Binary Search
- what is the condition that the target element is the min
- what is the condition that the minimum is in the left partition or in the right partition?
- consider when we have a fully rotated cycle (sorted array)
- and or only a rotated, non-full cycle array

167: Two Sum II - Input array is sorted

- Two pointer solution
- Pay attention to and leverage the details: ascending sorted, result indices must be non-zero (so 1-indexed)
- As for verbal performance, speak only when there is an insight or progress in thought process - no empty words

215: Kth largest element in an array

- **Sorting**
- Sorting or priority queues
- O(nlogk) time and O(n) space if we use a priority queue as a max-heap
- Can reduce time if we use quicksort partitioning scheme. We can do this O(n) time - how? see https://youtu.be/hGK_5n81drs?t=1251

230: Kth Smallest in a BST

- What kind of traversal visits nodes in order of smallest to largest?

235: Lowest Common Ancestor of a Binary Search Tree

- Leverage the properties of the binary search tree
- When do we know the LCA is in the left subtree?
- When do we know the LCA is in the right subtree?
- When do we know the LCA is the current node?

295: Find Median from Data Stream

- We can use a priority queue for operations such as finding a running median, avg., min., and max
- If we use two heaps, a max-heap for left and min-heap for right - then we can perform operations such as insertion in O(logn) time
  - The worst-case runtime of the algorithm is O(log n), since we need at most one swap on each level of a heap on the path from the inserted node to the root.
- finding the median can be calculated in constant time because we impose a median invariant on insertion

303: Range Query Immutable

- The range query immutable structure uses a prefix sum structure, it's an elementary form of dynamic programming.
- The structure to perform mutable range queries is the more interesting structures i.e. Binary Indexed Trees (Fenwick Trees)

307: Range Query Sum Mutable

- The input is 0-indexed, we must change it to be 1-indexed - this is the source of much frustration and confusion
- Further, the update operation is a set operation, and not an add operation

326: Power of Three

- this can be solved without recursion or loops
- how about using logarithm properties?

404: Sum of Left Leaves

- We can process a tree using a queue for BFS
  - The order that we add to the queue determines the kind of tree traversal (pre, in, post)
- Recursive, think of the cases:
  - What is my simple base case?
  - What variables can I use to define a leaf and if the leaf is a left subtree?

417: Pacific Atlantic Water Flow

- DFS graph traversal
- What are the conditions that you explore a path? Don't explore a path? When do we mark a cell as visited?
- It's okay for extra space for an adjancency matrix

436: Non-overlapping Intervals

- can we try greedy here?
- what is the condition that satisfies an overlapping interval?
- conversely, what is the condition that satisfies a non-overlapping interval?

509: Fibonacci Number

- Think about the how a given subproblem depends on two previous subproblems -> those are your base cases
- If implemented recursively, can we optimize call stack? Can we optimize space now? How can we improve to constant space and linear time?

617: Merge Two Binary Trees

- Simple case analysis and recursion
- Hint: define the base cases, then the general case

647: Palindromic Substring

- what are the ways that we can extend the substring?
- Hint: can we do this without nested loops?

739: Daily Temperatures

- **Revisit**
- we can use a sliding window for this problem - but this is non-optimal `O(n^2)` worst case
- how can we use a stack to improve the time complexity to O(n)? and what should the stack store?

937: Reorder Data in Log Files

- This is about knowing the language - java - and the methods available
- This problem covers:
  - Case analysis
  - sorting with comparator class
  - string and character comparison
