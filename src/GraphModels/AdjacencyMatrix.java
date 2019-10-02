package GraphModels;

import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix {

    static int v, e; //v = cantidad de nodos (vertex), e = cantidad de aristas (edge)
    static int MAX = 1000; //cantidad maxima de nodos
    static int ady[][] = new int[MAX][MAX]; //matriz de adyacencia del grafo

    public static void main(String[] args) {
        int v, e, origen, destino;
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        init();

        while (e > 0) {
            origen = sc.nextInt();
            destino = sc.nextInt();

            ady[origen][destino] = 1;
            ady[destino][origen] = 1;
            e--;
        }
    }

    static void init() {
        int i, j;
        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
                ady[i][j] = 0;
            }
        }
    }
}
