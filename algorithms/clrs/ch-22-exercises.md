# Chapter 22 Exercises

### 22.1-1

**Q:** Given an adjacency-list of a graph, how long does it take to compute the outdegree of every vertex? How long does it take to compute the in-degree?

**A:** An indegree and outdegree is only applicable to a directed graph. The indegree of a vertex is the number of edges pointing to it. The outdegree of a vertex is the number of edges pointing from it. Given an adjacency-list representation of a graph, the total cost of computing the outdegree of a vertex `u` is equal to the length of `adj[u]`, and the sum of all adjacency lists is `|E|`. Thus to compute one outdegree of one vertex is `Θ(adj[u])` and to compute all outdegrees of all vertices of `G`, it would be `Θ(V + E)` time.

The indegree of a vertex is equal to the number of times it appears in the all the adjacency lists. The time to compute the indegree of each vertex would require us to process all adjacency lists `|adj[u]|` for every vertex. This would result in `Θ(VE)` time. To optimize time in exchange for space, we could allocate an array `T` of size `|V|` and initialize all entries to zero. We would only need to scan each adjacency list once and increment `T[u]` when we see `u`. This can be done in `Θ(|V| + |E|)` time and `Θ(V)` space.

An adjacency matrix `A` contains `Θ(V^2)` entries, regardless of the number of entries. In order to compute the outdegree of a vertex `u` we need to scan the row corresponding to `u` in `A` and sum all the ones. Thus, the time to compute the number of outdegrees of a vertex is `Θ(V)` time and all vertices is `Θ(V^2)`. In order to the indegree of a vertex `u` is equivalent to scanning the column of `u` in `A`. The time compute the number of indegrees for a vertex is `Θ(V)` and all vertices is `Θ(V^2)`.

### 22.1-3

**Q:** The transpose of a directed graph G = (V,E) is the graph G<sup>T</sup>=(V,E<sup>T</sup>), where E<sup>T</sup> = {(v,u) ∈ V x V : (u,v) ∈ E}. Thus, G<sup>T</sup> is G with all its edges reversed. Describe efficient algorithms for computing G<sup>T</sup> from G, for both the adjacency list and adjacency-matrix representations of G. Analyze the running times of your algorithms.

**A:**

Given a graph `G` represented as an adjacency matrix, the time complexity to compute the transpose of `G`, G<sub>T</sub>, is <code>Θ(V<sub>2</sub>)</code>. Observe the following algorithm:

```java
for i = 0 to |G|
  for j = i to |G[i]|
    swap (i, j) with (j, i)
```

For a given vertex `v`, we do length of `|G[v]|` work. In total, The algorithm does <code>V<sub>2</sub></code>. Thus, the runtime is <code>Θ(V<sub>2</sub>)</code>.

Given a graph `G` represented as an adjacency list, the runtime to compute its tranpose, G<sub>T</sub>, is `Θ(|V| + |E|)`.
Peep the algorithm:

```java
// Allocate memory for a new adjacency list
Graph adjListTranspose = new Graph(adj.getLength());

// Compute the transpose of adj
for every vertex i in adj, from i = 0 to |adj|
  for every edge j in adj[i], from j = 0 to |adj[i]|
    append new Vertex(i) to the adjListTranspose[j]
```

We allocate memory for a new adjacency list that will be the transpose of `G`, this takes `Θ(V)` space. For a given vertex `v`, we do `|adj[v|` work to compute the tranpose, assuming that appending to the tranpose list is a constant operation (like a linked list). If we iterate over all vertices, then our runtime becomes `Θ(|V| + |E|)`.

### 22.1-8

**Q:** Suppose that instead of a linked list, each array entry `Adj[u]` is a hash table containing the vertices `v` for which `(u, v) ∈ E`. If all edge lookups are equally likely, what is the expected time to determine whether an edge is in the graph? What disadvantages does this scheme have? Suggest an alternate data structure for each edge list that solves these problems. Does your alternative have disadvantages compared to the hash table?

**A:** If we used a hash table as the underlying data structure for the adjacency list, the expected lookup time would be `O(1)`. This is a strong lookup time, doesn't get any better than that, however, the mapping of keys depends on the mod function we choose. If our keys hash to the same index, we could have a worst case `O(|V|)` lookup time.
If we chose to sort the vertices in each adjacency list and perform a binary search the lookup would be `O(lg|V|)`. The choice to sort and binary search as a much worse expected lookup time than `O(1)`.

### 22.2-4

**Q:** What is the running time of BFS if we represent its input graph by an adjacency matrix and modify the algorithm to handle this form of input?

**A:** If we represent its input graph as an adjacency matrix and modify the algorithm to handle this form of input, the running time of BFS is <code>O(V<sup>2</sup>)</code> worst case for an undirected graph. For every vertex `u` dequeue, we'll process all neighboring vertices `v` to decide if `v` is adjacent to `u`. It takes `O(V)` time to iterate to do this. If we enqueue every vertex of the graph, the worst case is <code>O(V<sup>2</sup>)</code>.

### 22.2-5

**Q:** Argue that the discovery time of a vertex u is independent of the order in which the vertices appear in the adjacency list. Show that the breadth-first tree computed by BFS can depend on the ordering within adjacency lists.

**A:**

### 22.2-7

### 22.2-8

**Q:** The diameter of a tree `T = (V,E)` is defined as <code>max<sub>u,v ∈V</sub>𝜹(u,v)</code>, that is, the largest of all shortest-path distances in the tree. Give an efficient algorithm to compute the diameter of a tree, and analyze the running time of your algorithm.

**A:** The algorithm to find the diameter of a tree using breadth-first search is a quite natural algorithm, actually. Keep track of the last vertex `v` i.e. the vertex with the longest distance from the source vertex `s`. Then perform a breadth-first search on `v` as the source vertex. The distance of the path from `v` to the the last vertex discovered is the diameter of the tree.

In order to find the diameter of a tree if the tree is a binary tree, we compute the longest path of the left subtree `l` and combine it with the longest path of the right subtree `r`.

## 22.3-3

**Q:** Show the parenthesis structure of the depth-first search of Figure 22.4.

**A:**
Parenthesis structure:

Figure 22.4
| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| u | v | y | x | x | y | v | u |

| 9   | 10  | 11  | 12  |
| --- | --- | --- | --- |
| w   | z   | z   | w   |

```
(((())))(())
```

Figure 22.5(a)

```
(((())()))(()())
```

## 22.3-5

**Q:** Show that edge `u` is

a. a tree edge or forward edge if and only if `u.d < v.d < v.f < u.f`,
b. a back edge if and only if `v.d <= u.d < u.f <= v.f`, and
c. a cross edge if and only if `v.d < v.f < u.d < u.f`.

**A:**

a. By definition: edge `(u,v)` is a tree edge if `v` was first discovered by exploring edge `(u,v)`. Put another way, an edge is a tree edge iff `u.d < v.d < v.f < u.f`.

b. By definition: edge `(u,v)` is a back edge if `u` connects to an ancestor `v` in a depth-first tree. A back edge may be a self-loop or a cycle in a directed graph. Put another way, en edge is a back edge

## 22.3-6

**Q:**
**A:**

## 22.3-7

**Q:** Rewrite the procedure DFS, using a stack to eliminate recursion.
**A:**
Observe DFS and DFS-Visit as a recursive function

```java
DFS(g, s, u) {
  for (int i = 0; i < g.length; i++) {
    predecessor[i] = null;
    colors[i] = COLOR.WHITE;
    discovered[i] = null;
    finished[i] = null;
  }

  for (int i = 0; i < g.length; i++) {
    if (colors[g[i]] == COLOR.WHITE) {
      DFSVisit(g, g[i]);
    }
  }
}

DFSVisit(g, u) {
  time = time + 1;
  discovered[u] = time;
  colors[u] = COLOR.GREY;
  for (int i = 0; i < g[u].length; i++) {
    if (colors[g[u][i]] == COLOR.WHITE) {
      predecessor[g[u][i]] = u;
      DFSVisit(g, g[u][i]);
    }
  }

  colors[u] = COLOR.BLACK;
  time = time + 1;
  finished[u] = time;
  topologicalOrder.prepend(u);
}

```

Observe: an implementation of DFS using a stack to model DFS-Visit

```java
DFS(g, s) {
  for (int i = 0; i < g.length; i++) {
    colors[i] = COLOR.WHITE;
    predecessor[i] = null;
    discovered[i] = null;
    finished[i] = null;
  }

  time = 0;

  Stack<Vertex> dfsVisit = new Stack<>();
  dfsVisit.push(s);

  while(dfsVisit.isEmpty() == false) {

  }
}
```

Observe: an implementation of DFS that has cycle detection using a stack to model DFS-Visit

```java
DFS(g, s) {
  for (int i = 0; i < g.length; i++) {
    predecessor[i] = null;
    colors[i] = COLOR.WHITE;
    discovered[i] = null;
    finished[i] = null;
  }

  time = 0;

  Stack<Vertex> dfsVisit = new Stack<>();
  Set<Vertex> processed = new Set<>();
  while (dfsVisit.isEmpty() == false) {
    Vertex u = dfsVisit.peek();
    if (colors[u] == COLORS.WHITE) {
      time = time + 1;
      discovered[u] = time;
      colors[u] = COLORS.GREY;

      for (int i = 0; i < g[u].length; i++) {
        Vertex v = g[u][i];
        if (colors[v] == COLORS.WHITE) {
          predecessor[v] = u;
          dfsVisit.push(v);
        } else if (colors[v] == COLORS.GREY) {
          hasCycle = true;
        }
      }
    } else if (colors[u] == COLORS.GREY) {
      u = dfsVisit.pop();
      time = time + 1;
      finished[u] = time;
      colors[u] = COLORS.BLACK;
      topologicalOrder.prepend(u);
    }
  }
}
```

Note: It does not matter the order that a vertex's neighbors are discovered. What matters is if our implementation of DFS creates a valid DAG, classifies edges correctly, detects cycles, builds a correct predecessor subgraph, and allows us to create a valid topologically sorted list.

We use colors to help us understand the state of a vertex in DFS. If a vertex is white, it has yet to be discovered. If a vertex is grey, it is an ancestor vertex whose descendants are being processed. If a vertex is black, it is a vertex whose edges have already been fully explored. In short, white = unexplored, grey = exploring its descendants, and black = visited/explored.
We can use vertex-state coloring to detect cycles in a directed. In a directed graph, if we visit a vertex whose color is grey (a vertex whose descendants are being explored), then we have found a cycle. If we visit a vertex whose color black, this may not imply a cycle, instead, this indicates either a forward edge or a cross edge and a connection to another depth-first tree.

**In a depth-first search of an undirected graph, forward and cross edges never occur. In an undirected graph, every edge of `G` is either a tree edge or a back edge.**

## 22.3-8

**Q:** Give a counterexample to the conjecture that if a directed graph G contains a path from `u` to `v`, and if `u.d < v.d` in a depth-first search of `G`, then `v` is a descendant of `u` in the depth-first forest produced. In other words, argue against the claim that `v` will be a descendant of `u` if we perform DFS.

**A:** Consider a graph `G` with three vertices `u`, `v` and edges `(w,u)`, `(u,w)`, and `(w,v)`. Suppose we perform DFS on `w` and we discover `u` first in the adjacency list. The only vertex adjacent to `u` is `w`, but `w`'s color is grey, meaning it has already been discovered, thus a cycle. DFS will end and `v` will neither be a descendant of `u` nor will it be discovered.

## 22.3-10

**Q:** Modify the pseudocode for depth-first search so that it prints out every edge in the directed graph `G`, together with its type. Show what modifications, if any, you need to make if `G` is undirected.
**A:**
For a directed graph, recall, while exploring an `edge(u,v)` if the discovery of a vertex `v` is grey, this means `v` is an ancestor of `u` in the depth-first tree. `Edge (u,v)` is a back edge.
If `v` is black, `edge(u,v)` is either a forward edge or a cross edge. `Edge(u,v)` is a forward edge if `u` is a descendant of `v`, and a cross edge as long as one vertex is not an ancestor of the other, or they can go between vertices in different depth-first trees.
If `v` is white, then `edge(u,v)` is a tree edge.

```java
DFS(G)
  initialize subgraphs
    predecessor
    color
    discovered = infinity
    finished = infinity
  time = 0
  for v ∈ V
    if color.v == WHITE
      call DFS-Visit(G, v)

DFS-Visit(G, u)
  color.u = GREY
  time = time + 1
  discovered.u = time
  for v ∈ V
    if color.v = WHITE
      Print("(u,v) is a Tree Edge")
      predecessor.v = u
      DFS-Visit(G, v)
    else if color.v == GREY
      Print("(u,v) is a Back Edge")
    else if color.v == BLACK
      if discovered.v > discovered.u
        Print("(u,v) is a Forward Edge")
      else
        Print("(u,v) is a Cross Edge")
  color.u = BLACK
  time = time + 1
  finished.u = time
```

For a depth-first traversal of an undirected graph, recall, there can only exist back and tree edges. If G is undirected, we don't need to make any modifications, note we simply do not label forward or cross edges, nor color a vertex black.

## 22.3-12

**Q:** Show that we can use a depth-first search of an undirected graph `G` to identify the connected components of `G`, and that the depth-first forest contains as many trees as `G` has connected components. More precisely, show how to modify depth-first search so that it assigns to each vertex an integer label `v.cc` between `1` and `k`, where `k` is the number of connected components of `G`, such that `u.cc = v.cc`if and only if `u` and `v` are in the same connected component i.e. label connected components (depth-first trees) and assign vertices to the connected component they belong to in a depth-first forest.
**A:** In order to label vertices that belong to the same connected component, we modify `DFS()` and `DFS-Visit()`. Observe the following psuedocode. Reminder: this is for finding the connected components of an undirected graph.

```java
DFS(G)
  initialize subgraphs
    ...
    connected-components = nil
    ...
  k = 1
  for v ∈ V
    if (color.v == WHITE)
      k = k + 1
      cc.u = k
      call DFS-Visit(G, v)
DFS-Visit(G, u)
  ...
  for v ∈ G.u
      v.cc = u.cc
```

## 22.3-13

**Q:** A directed graph `G = (V,E)` is **singly connected** if `u -> v` implies that `G` contains at most one simple path from `u` to `v` for all vertices `u,v ∈ V`. Give an efficient algorithm to determine whether or not a directed graph is singly connected.
**A:** This can be done in

what's the difference between a singly connected graph and a bipartite graph?

## 22.4-1

**Q:**
**A:**

## 22.4-2

**Q:**
**A:**

## 22.4-3

**Q:**
**A:**

## 22.4-4

**Q:**
**A:**

## 22.4-5

**Q:**
**A:**

## 22.5-1

**Q:**
**A:**

## 22.5-2

**Q:**
**A:**

## 22.5-3

**Q:**
**A:**

## 22.5-4

**Q:**
**A:**

## 22.5-5

**Q:**
**A:**

## 22.5-7

**Q:**
**A:**
