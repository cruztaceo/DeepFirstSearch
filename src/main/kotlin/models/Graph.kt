package models

class Graph {
    val adjacencyMap: HashMap<Int, HashSet<Int>> = HashMap()

    fun addEdge(sourceVertex: Int, targetVertex: Int) {
        // Add edge to source node.
        adjacencyMap.computeIfAbsent(sourceVertex) { HashSet() }.add(targetVertex)
        // Add edge to destination node.
        adjacencyMap.computeIfAbsent(targetVertex) { HashSet() }.add(sourceVertex)
    }

    fun adjacentEdges(v: Int): HashSet<Int>? {
        return adjacencyMap[v]?.toHashSet()
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}