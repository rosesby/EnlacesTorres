package com.enlaces;

import util.Consola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enlaces {

    public static void main(String[] args) throws IOException {
        Map map = new Map();
        int lineCounter = 1;

        FileReader fileReader = new FileReader("src/com/data/input4");
        BufferedReader in = new BufferedReader(fileReader);

        Pattern pattern = Pattern.compile("[\\s]*(([A-z][A-z0-9]{0,14})[\\s]*(<-|->|<=|=>|-)[\\s]*([A-z][A-z0-9]{0,14})[\\s]*([.?]))(.*)");

        String data;
        while ((data = in.readLine()) != null) {

            Matcher m = pattern.matcher(data);
            if (m.find()) {
                String line = m.group(0);
                String expresion = m.group(1);
                String city1 = m.group(2);
                String operator = m.group(3);
                String city2 = m.group(4);
                String garbage = m.group(6);
                String dotMark = m.group(5);

                boolean doktMarkCheck = ((operator.equals("<=") || operator.equals("=>")) && dotMark.equals(".")) || ((operator.equals("<-") || operator.equals("->")) && dotMark.equals("?"));
                if(doktMarkCheck) continue;

                System.out.println();
                System.out.println(Consola.Color.RED + "Match in line " + lineCounter + " : " + Consola.Color.RESET + line + Consola.Color.RESET);
                System.out.println(Consola.Color.BLUE + "Expression: " + Consola.Color.RESET + expresion);
                if (!garbage.isBlank())
                    System.out.println(Consola.Color.CYAN + "Garbage: " + Consola.Color.RESET + garbage);

                System.out.print(Consola.Color.PURPLE + "City1 : " + Consola.Color.RESET + city1 + " : ");
                System.out.print(Consola.Color.GREEN);
                map.createCityIfDoesNotExists(city1);
                System.out.print(Consola.Color.RESET);

                System.out.println(Consola.Color.PURPLE + "Operator: " + Consola.Color.RESET + operator);

                System.out.print(Consola.Color.PURPLE + "City2 : " + Consola.Color.RESET + city2 + " : ");
                System.out.print(Consola.Color.GREEN);
                map.createCityIfDoesNotExists(city2);
                System.out.print(Consola.Color.RESET);

                System.out.print(Consola.Color.PURPLE + "Result : " + Consola.Color.RESET);

                switch (operator) {
                    case "->":
                        map.createRelation(city1, city2);
                        break;
                    case "<-":
                        map.createRelation(city2, city1);
                        break;
                    case "=>":
                        System.out.println("Puede ir de " + city1 + " a " + city2 + " : " + map.searchWayToCityBFS(city1, city2));
                        break;
                    case "<=":
                        System.out.println("Puede ir de " + city2 + " a " + city1 + " : " + map.searchWayToCityBFS(city2, city1));
                        break;
                    case "-":
                        map.closeConnectionBetween(city1, city2);
                        break;
                }
            }
            lineCounter++;
        }
    }
}
