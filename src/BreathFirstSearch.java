import java.util.LinkedList;

public class BreathFirstSearch {
    private int[] edgeTo;
    private boolean[] marked;
    private int[] distTo;
    private int source;
    private static final int INF = Integer.MAX_VALUE;

    public BreathFirstSearch(MyGraph graph, int source){
        if (source < 0 || source >= graph.vertexCount()) throw new IllegalArgumentException("Такой вершины не существует");
        this.source = source;
        edgeTo = new int[graph.vertexCount()];
        marked = new boolean[graph.vertexCount()];
        distTo = new int[graph.vertexCount()];
        for (int i:distTo) {
            i = INF;
        }
        bfs(graph, source);
    }

    public void bfs(MyGraph graph, int source){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        distTo[source] = 0;

        while (!queue.isEmpty()){
            int vertex = queue.removeFirst();
            for (int v: graph.adjList(vertex)) {
                if (!marked[v]) {
                    marked[v] = true;
                    edgeTo[v] = vertex;
                    distTo[v] = distTo[vertex] + 1;
                    queue.add(v);
                }
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

    public int distTo(int dest){
        if (dest < 0) throw new IllegalArgumentException("Номер вершины не может быть отрицательным");
        if (dest >= marked.length) {
            throw new IndexOutOfBoundsException("Вершины с таким номером не существует");
        }
        return distTo[dest];
    }
}
