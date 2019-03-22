import java.util.LinkedList;

public class DepthFirstSearch {
    private int source;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstSearch(MyGraph graph, int source){
        if (source < 0 || source >= graph.vertexCount()) throw new IllegalArgumentException("Такой вершины не существует");
        this.source = source;
        edgeTo = new int[graph.vertexCount()];
        marked = new boolean[graph.vertexCount()];
        dfs(graph, source);
    }

    private void dfs(MyGraph graph, int vertex){
        marked[vertex] = true;
        for (int v:graph.adjList(vertex)) {
            if (!marked[v]) {
                edgeTo[v] = vertex;
                dfs(graph, v);
            }
        }
    }

    public boolean hasPathTo(int destination){
        if (destination < 0) throw new IllegalArgumentException("Номер вершины не может быть отрицательным");
        if (destination >= marked.length) {
            throw new IndexOutOfBoundsException("Вершины с таким номером не существует");
        }
        return marked[destination];
    }

    public LinkedList pathTo(int destination){
        if (!hasPathTo(destination)) return null;

        LinkedList<Integer> path = new LinkedList<>();

        int v = destination;
        while (v!= source){
            path.push(v);
            v = edgeTo[v];
        }
        return path;
    }
}
