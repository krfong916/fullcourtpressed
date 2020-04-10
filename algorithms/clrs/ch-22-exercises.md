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

### 22.1-4

### 22.1-8

### 22.2-1

### 22.2-2

### 22.2-4

### 22.2-5

### 22.2-8

### 22.3-13
