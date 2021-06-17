import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.Graph
import models.JsonGraph
import java.io.File

fun main(args: Array<String>) {
    val inputFileName = "src/main/resources/Grafo_no_conexo.json"
    val json = readFileAsLinesUsingBufferedReader(inputFileName)
    val jsonGraph = Json.decodeFromString<JsonGraph>(json);
    val graph = Graph()

    jsonGraph.edges.map { graph.addEdge(it[0], it[1]) }

    print(graph.toString())

}

fun DFS(graph: Graph, sourceVertex: Int){
    val visitedNodes = mutableMapOf<Int, Boolean>()
    visitedNodes[sourceVertex] = true;

    graph.adjacentEdges(sourceVertex)
}

/**
 * Read file
 *
 * @param fileName Read file
 * @return List of strings read
 */
fun readFileAsLinesUsingBufferedReader(fileName: String): String = File(fileName).bufferedReader().use { it.readText() }
