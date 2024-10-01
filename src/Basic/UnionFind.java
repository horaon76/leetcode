package Basic;

class UnionFind {
    private int[] parent;  // parent[i] points to the parent of i
    private int[] rank;    // rank[i] is the rank (approx height) of the tree for element i

    // Constructor: initialize the parent and rank arrays
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];

        // Initially, each element is its own parent, and the rank is 1
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Find function with path compression
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);  // Path compression
        }
        return parent[p];
    }

    // Union function with union by rank
    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        // If p and q are already in the same set, return false (no union needed)
        if (rootP == rootQ) {
            return false;
        }

        // Union by rank: attach the smaller tree under the larger tree
        if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        return true;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5); // Create Union-Find for 5 elements (cities 0 to 4)

        // Connect city 0 and city 1
        uf.union(0, 1);

        // Connect city 1 and city 2
        uf.union(1, 2);

        // Check if city 0 and city 2 are connected (They should be, through city 1)
        System.out.println(uf.find(0) == uf.find(2)); // Output: true

        // Check if city 0 and city 3 are connected (They are not)
        System.out.println(uf.find(0) == uf.find(3)); // Output: false

        // Connect city 3 and city 4
        uf.union(3, 4);

        // Connect city 2 and city 3 (Now all cities should be connected)
        uf.union(2, 3);

        // Check if city 0 and city 4 are connected (They should be)
        System.out.println(uf.find(0) == uf.find(4)); // Output: true
    }
}

/**
 * Time Complexity:
 * Find: With path compression, each find operation runs in nearly constant time,
 * 𝑂
 * (
 * 𝛼
 * (
 * 𝑛
 * )
 * )
 * O(α(n)), where
 * 𝛼
 * (
 * 𝑛
 * )
 * α(n) is the inverse Ackermann function, which grows very slowly and is effectively constant for all practical inputs.
 * Union: Each union operation is also nearly constant time,
 * 𝑂
 * (
 * 𝛼
 * (
 * 𝑛
 * )
 * )
 * O(α(n)), due to the use of union by rank.
 * Thus, the amortized time complexity for both find and union operations is almost constant time,
 * 𝑂
 * (
 * 𝛼
 * (
 * 𝑛
 * )
 * )
 * O(α(n)).
 * **/