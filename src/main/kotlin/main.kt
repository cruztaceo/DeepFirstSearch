import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.Graph
import models.JsonGraph
import java.io.File

fun main(args: Array<String>) {
    val inputFileName = "src/main/resources/Grafo_no_conexo.json"
    val json = readFileAsLinesUsingBufferedReader(inputFileName)
    val jsonGraph = Json.decodeFromString<JsonGraph>(json)
    val graph = Graph()

    jsonGraph.edges.map { graph.addEdge(it[0], it[1]) }
    val visitedNodes = mutableMapOf<Int, Boolean>().apply {
        graph.adjacencyMap.keys.forEach { node -> put(node, false) }
    }

    print(graph.toString())

    deepFindSearch(graph, graph.adjacencyMap.keys.random(), visitedNodes)

    println("=============================================")

    println("Graph 1 -> ${visitedNodes.filter { node -> node.value }}")

    println("Graph 2 -> ${visitedNodes.filter { node -> !node.value }}")

}

fun deepFindSearch(graph: Graph, sourceVertex: Int, visitedNodes: MutableMap<Int, Boolean>) {
    visitedNodes[sourceVertex] = true

    if (!visitedNodes.containsValue(false)) {
        return
    }

    graph.adjacentEdges(sourceVertex)?.forEach { w ->
        if (visitedNodes[w] != true) {
            deepFindSearch(graph, w, visitedNodes)
        }
    }
}

/**
 * Read file
 *
 * @param fileName Read file
 * @return List of strings read
 */
fun readFileAsLinesUsingBufferedReader(fileName: String): String = File(fileName).bufferedReader().use { it.readText() }
