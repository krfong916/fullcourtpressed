7: Reverse Integer

- **Native types**
- must work with native types in order to reverse integer
- think modulo, deriving tens place, accounting for overflow

23. Merge K Sorted Lists

- **Min-Heap or Finger-Weave**
- consider cycles and creating new nodes
- if finger-weave, careful with dummy head and the list representation you return
- variation of merge 2 sorted lists

54: Spiral Matrix

- **2D matrix**
- can use enum and object-oriented here
- careful to think of edge cases (many)

62: Unique Paths

- **Dynamic Programming**
- to determine # unique ways reachable, think of dynammic programming and 2D matrix. What does each subproblem depend on?

64: Minimum Path Sum

- **Dynamic Programming**
- After defining the recurrence relation, careful implementing the relation. i.e. fill `[x][0]`, `[0][y]`, then the recurrence relation

215: Kth largest element in an array

- **Sorting**
- Sorting or priority queues
- O(nlogk) time and O(n) space if we use a priority queue as a max-heap
- Can reduce time if we use quicksort partitioning scheme. We can do this O(n) time - how? see https://youtu.be/hGK_5n81drs?t=1251
