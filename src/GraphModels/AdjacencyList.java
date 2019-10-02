package GraphModels;

import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyList {

    static int v, e; //v = cantidad de nodos (vertex), e = cantidad de aristas (edge)
    static int MAX = 1000; //cantidad maxima de nodos
    static ArrayList<Integer>[] ady = new ArrayList[MAX]; //lista de adyacencia del grafo

    public static void main(String[] args) {
        int v, e, origen, destino;
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        init();

        while (e > 0) {
            origen = sc.nextInt();
            destino = sc.nextInt();

            ady[origen].add(destino);
            ady[destino].add(origen);
            e--;
        }
    }

    static void init() {
        int i, j;
        for (i = 0; i < v; i++) {
            ady[i] = new ArrayList<Integer>();
        }
    }
}
