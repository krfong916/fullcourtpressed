# Chapter 22 Exercises

### 22.1-1

#### Q:

Given an adjacency-list of a graph, how long does it take to compute the outdegree of every vertex? How long does it take to compute the in-degree?

#### A:

An indegree and outdegree is only applicable to a directed graph. The indegree of a vertex is the number of edges pointing to it. The outdegree of a vertex is the number of edges pointing from it. Given an adjacency-list representation of a graph, the total cost of computing the outdegree of a vertex `u` is equal to the length of `adj[u]`, and the sum of all adjacency lists is `|E|`. Thus to compute one outdegree of one vertex is `Θ(adj[u])` and to compute all outdegrees of all vertices of `G`, it would be `Θ(V + E)` time.

The indegree of a vertex is equal to the number of times it appears in the all the adjacency lists. The time to compute the indegree of each vertex would require us to process all adjacency lists `|adj[u]|` for every vertex. This would result in `Θ(VE)` time. To optimize time in exchange for space, we could allocate an array `T` of size `|V|` and initialize all entries to zero. We would only need to scan each adjacency list once and increment `T[u]` when we see `u`. This can be done in `Θ(|V| + |E|)` time and `Θ(V)` space.

An adjacency matrix `A` contains <code>Θ(V<sup>2</sup>)</code> entries, regardless of the number of entries. In order to compute the outdegree of a vertex `u` we need to scan the row corresponding to `u` in `A` and sum all the ones. Thus, the time to compute the number of outdegrees of a vertex is `Θ(V)` time and all vertices is <code>Θ(V<sup>2</sup>)</code>. In order to the indegree of a vertex `u` is equivalent to scanning the column of `u` in `A`. The time compute the number of indegrees for a vertex is `Θ(V)` and all vertices is <code>Θ(V<sup>2</sup>)</code>.

### 22.1-3

The transpose of a directed graph <code>G = (V,E)</code> is the graph <code>G<sup>T</sup>=(V,E<sup>T</sup>)</code>, where <code>E<sup>T</sup> = {(v,u) ∈ V x V : (u,v) ∈ E}</code>. Thus, <code>G<sup>T</sup></code> is `G` with all its edges reversed. Describe efficient algorithms for computing <code>G<sup>T</sup></code> from `G`, for both the adjacency list and adjacency-matrix representations of `G`. Analyze the running times of your algorithms.

### 22.1-4

### 22.1-8

### 22.2-1

### 22.2-2

### 22.2-4

### 22.2-5

### 22.2-8

### 22.3-13
