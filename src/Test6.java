public class Test6 {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,6);
        graph.addEdge(0,4);
        graph.addEdge(0,5);
        graph.addEdge(1,2);
        graph.addEdge(1,6);
        graph.addEdge(2,3);
        graph.addEdge(4,8);
        graph.addEdge(5,4);
        graph.addEdge(6,3);
        graph.addEdge(6,7);
        graph.addEdge(6,8);
        graph.addEdge(8,9);
        graph.addEdge(7,9);

        BreathFirstSearch bfs1 = new BreathFirstSearch(graph, 0);
        System.out.println(bfs1.pathTo(3));
        System.out.println(bfs1.pathTo(9));
        BreathFirstSearch bfs2 = new BreathFirstSearch(graph, 8);
        System.out.println(bfs2.pathTo(2));
    }
}
