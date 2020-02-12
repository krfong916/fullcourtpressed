7:

- must work with native types in order to reverse integer
- think modulo, deriving tens place, accounting for overflow

54:

- can use enum and object-oriented here
- careful to think of edge cases (many)

62:

- to determine # unique ways reachable, think of dynammic programming and 2D matrix. What does each subproblem depend on?

64:

- minimum path sum

215:

- Sorting or priority queues
- O(nlogk) time and O(n) space if we use a priority queue as a max-heap
- Can reduce time if we use quicksort partitioning scheme. Though O(nlogn) and O(n^2) worst case, we randomize pivot to reduce the amount of work we do avg. case
