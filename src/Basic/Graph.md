To effectively solve data structures and algorithms (DSA) problems involving graphs, it’s important to grasp several fundamental concepts. Below is an overview of key graph fundamentals, along with illustrations and explanations.

### 1. **Graph Definition**
- A **graph** consists of a set of vertices (or nodes) connected by edges.
- Graphs can be **undirected** (where edges have no direction) or **directed** (where edges have a direction).

**Illustration**:
- **Undirected Graph**:

  \[
  \text{Vertices: } A, B, C, D
  \]
  \[
  \text{Edges: } (A, B), (A, C), (B, D)
  \]

  ```
     A
    / \
   B   C
   |
   D
  ```

- **Directed Graph**:

  \[
  \text{Vertices: } A, B, C, D
  \]
  \[
  \text{Edges: } A \rightarrow B, A \rightarrow C, B \rightarrow D
  \]

  ```
     A
    / \
   v   v
   B   C
   |
   v
   D
  ```

### 2. **Graph Representations**
Graphs can be represented in multiple ways:

- **Adjacency Matrix**: A 2D array where the cell at \((i, j)\) indicates whether there is an edge from vertex \(i\) to vertex \(j\).

**Example**:
For vertices \(A\), \(B\), and \(C\):

\[
\text{Adjacency Matrix} =
\begin{bmatrix}
0 & 1 & 1 \\
0 & 0 & 0 \\
0 & 1 & 0
\end{bmatrix}
\]

- **Adjacency List**: Each vertex has a list of all adjacent vertices.

**Example**:
```
A: B, C
B: 
C: B
```

### 3. **Types of Graphs**
- **Directed vs. Undirected**: Directed graphs have edges with direction; undirected graphs do not.
- **Weighted vs. Unweighted**: Weighted graphs have edges with weights (costs), while unweighted graphs do not.
- **Cyclic vs. Acyclic**: Cyclic graphs contain cycles; acyclic graphs do not.
- **Connected vs. Disconnected**: A connected graph has a path between every pair of vertices; a disconnected graph does not.

### 4. **Graph Traversal Algorithms**
- **Depth-First Search (DFS)**: A traversal algorithm that explores as far as possible along each branch before backtracking.

**Illustration**:
Starting from vertex \(A\):

1. Visit \(A\)
2. Go to \(B\)
3. Go to \(D\)
4. Backtrack to \(B\)
5. Backtrack to \(A\)
6. Visit \(C\)

**Traversal Order**: \(A \rightarrow B \rightarrow D \rightarrow C\)

- **Breadth-First Search (BFS)**: A traversal algorithm that explores all neighbors at the present depth level before moving on to nodes at the next depth level.

**Illustration**:
Starting from vertex \(A\):

1. Visit \(A\)
2. Visit neighbors \(B\) and \(C\)
3. Visit neighbor \(D\) of \(B\)

**Traversal Order**: \(A \rightarrow B \rightarrow C \rightarrow D\)

### 5. **Shortest Path Algorithms**
- **Dijkstra’s Algorithm**: Computes the shortest path from a source node to all other nodes in a weighted graph.

**Illustration**:
Given a graph:
```
  (2)
A ----> B
|      / |
| (1)  | (3)
|    /  |
v  v    v
C ----> D
  (1)
```
- Starting from \(A\):
    - Shortest path to \(B\) is \(A \rightarrow B\) with a distance of 2.
    - Shortest path to \(C\) is \(A \rightarrow C\) with a distance of 1.
    - Shortest path to \(D\) is \(A \rightarrow C \rightarrow D\) with a distance of 2 (1 + 1).

### 6. **Minimum Spanning Tree (MST) Algorithms**
- **Kruskal’s Algorithm**: Finds the minimum spanning tree by sorting edges and adding them one by one.

**Illustration**:
For a graph with edges and weights:
```
   (1)
A ------ B
|  \    |
|   \   |
(2) (3) |
|     \ |
C ------ D
   (2)
```
- MST will include edges with weights \(1\) (A-B), \(2\) (A-C), and \(2\) (C-D).

### 7. **Topological Sorting**
- **Topological Sort**: A linear ordering of vertices such that for every directed edge \(u \rightarrow v\), vertex \(u\) comes before \(v\). This is applicable to Directed Acyclic Graphs (DAGs).

**Illustration**:
For a graph:
```
A → B → D
|         ↑
v         |
C --------
```
- A valid topological sort could be: \(A, C, B, D\) or \(A, B, C, D\).

### 8. **Graph Coloring**
- **Graph Coloring**: Assigning colors to vertices so that no two adjacent vertices share the same color. This is often used in scheduling problems.

**Illustration**:
```
   A
  / \
 B   C
  \ /
   D
```
- A valid coloring could be:
    - A: Red
    - B: Blue
    - C: Blue
    - D: Red

### 9. **Network Flow Algorithms**
- **Ford-Fulkerson Method**: Calculates the maximum flow in a flow network.

**Illustration**:
```
  (10)
A ------> B
|         |
(5)      (15)
|         |
v         v
C ------> D
  (10)
```
- Max flow from \(A\) to \(D\) could be calculated using augmenting paths.

# Types of Graphs
Understanding the types of graphs is crucial for solving various problems in data structures and algorithms. Each type has unique properties and applications. Here’s a detailed overview of the different types of graphs, along with illustrations and examples.

### 1. **Directed and Undirected Graphs**
- **Directed Graph (Digraph)**: In a directed graph, edges have a direction, meaning they go from one vertex to another. The edge \( (u, v) \) signifies a connection from vertex \( u \) to vertex \( v \).

  **Illustration**:
  ```
     A
    / \
   v   v
   B   C
   |
   v
   D
  ```
  **Example**:
    - Edges: \( A \rightarrow B, A \rightarrow C, B \rightarrow D \)

- **Undirected Graph**: In an undirected graph, edges do not have a direction. The edge \( (u, v) \) indicates a two-way connection between vertices \( u \) and \( v \).

  **Illustration**:
  ```
     A
    / \
   B   C
   |
   D
  ```
  **Example**:
    - Edges: \( (A, B), (A, C), (B, D) \)

### 2. **Weighted and Unweighted Graphs**
- **Weighted Graph**: In a weighted graph, each edge has a weight (or cost) associated with it. This is useful in scenarios like finding the shortest path, where the weight represents distance, cost, or time.

  **Illustration**:
  ```
     (2)
     A
    / \
(1)/   \(3)
B-----C
|(4)  
D
  ```
  **Example**:
  - Edges: \( A \rightarrow B (2), A \rightarrow C (3), B \rightarrow C (1), B \rightarrow D (4) \)

- **Unweighted Graph**: In an unweighted graph, all edges are considered equal; no weights are associated with them.

  **Illustration**:
  ```
     A
    / \
B   C
|
D
  ```
  **Example**:
  - Edges: \( (A, B), (A, C), (B, D) \)

### 3. **Cyclic and Acyclic Graphs**
- **Cyclic Graph**: A cyclic graph contains at least one cycle, meaning there is a path that starts and ends at the same vertex without retracing any edge.

  **Illustration**:
  ```
     A
    / \
B---C
|
D
  ```
  **Example**:
  - Cycle: \( A \rightarrow B \rightarrow C \rightarrow A \)

- **Acyclic Graph**: An acyclic graph does not contain any cycles. If the graph is directed and acyclic, it is referred to as a Directed Acyclic Graph (DAG).

  **Illustration**:
  ```
     A
    / \
B   C
|
D
  ```
  **Example**:
  - No cycles present.

### 4. **Connected and Disconnected Graphs**
- **Connected Graph**: A connected graph has a path between every pair of vertices. In an undirected graph, this means there is a route from any vertex to any other vertex.

  **Illustration**:
  ```
     A
    / \
B---C
|
D
  ```
  **Example**:
  - All vertices are connected.

- **Disconnected Graph**: A disconnected graph has at least two vertices such that there is no path connecting them. It can consist of multiple components.

  **Illustration**:
  ```
     A     E
    / \   /
B---C F
|
D
  ```
  **Example**:
  - Two components: {A, B, C, D} and {E, F}.

### 5. **Bipartite Graphs**
- **Bipartite Graph**: A bipartite graph can be divided into two disjoint sets of vertices such that every edge connects a vertex from one set to a vertex from the other set. There are no edges between vertices of the same set.

  **Illustration**:
  ```
Set 1: A, B
Set 2: C, D
A---C
A---D
B---C
  ```
  
  **Example**:
  - Edges: \( (A, C), (A, D), (B, C) \)

### 6. **Complete Graphs**
- **Complete Graph**: A complete graph is a graph in which every pair of vertices is connected by a unique edge. If there are \( n \) vertices, the number of edges in a complete graph is \( \frac{n(n-1)}{2} \).

  **Illustration**:
  ```
     A
    /|\
B-+-C
\|/
D
  ```
  
  **Example**:
  - For 4 vertices (A, B, C, D), edges: \( (A, B), (A, C), (A, D), (B, C), (B, D), (C, D) \)

### 7. **Tree and Forest**
- **Tree**: A tree is a special type of acyclic graph that is connected and has \( n - 1 \) edges for \( n \) vertices. Trees have no cycles and there is exactly one path between any two vertices.

  **Illustration**:
  ```
     A
    / \
B   C
/ \
D   E
  ```

- **Forest**: A forest is a disjoint set of trees. It can be thought of as a collection of trees.

  **Illustration**:
  ```
     A       E
    / \     /
B   C   F
  ```
