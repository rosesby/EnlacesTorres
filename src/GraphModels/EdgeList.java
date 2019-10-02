package GraphModels;

import java.util.ArrayList;
import java.util.Scanner;

public class EdgeList {

    static int v, e; //v = cantidad de nodos (vertex), e = cantidad de aristas (edge)
    static int MAX = 1000; //cantidad maxima de nodos
    static ArrayList<Edge> edges;

    public static void main(String[] args) {
        int v, e, origen, destino;
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        init();

        while (e > 0) {
            origen = sc.nextInt();
            destino = sc.nextInt();

            edges.add(new Edge(origen, destino));
            e--;
        }
    }

    static void init() {
        edges = new ArrayList<Edge>(); //Kruskal
    }

    static class Edge {
        public int src, dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }
}
