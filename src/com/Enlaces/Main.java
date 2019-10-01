package com.Enlaces;

import Util.Consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Map map;
    private static int lineCounter;

    public static void main(String[] args) throws IOException {
        map = new Map();
        lineCounter = 1;
        Parser parser = new Parser("src/Data/input");
        BufferedReader in = parser.getIn();

        Pattern patternOpenConnection = Pattern.compile("(([A-z]+[0-9]*)[\\s]*(<-|->)[\\s]*([A-z]+[0-9]*)[\\s]*[.])(.*)");
        Pattern patternTravel = Pattern.compile("(([A-z]+[0-9]*)[\\s]*(<=|=>)[\\s]*([A-z]+[0-9]*)[\\s]*[?])(.*)");
        Pattern patternCloseConnection = Pattern.compile("[A-z]+[0-9]*[\\s]*(-)[\\s]*[A-z]+[0-9]*[\\s]*[.]");

        Pattern[] patterns = new Pattern[]{patternOpenConnection, patternTravel, patternCloseConnection};

        processLines(in, patterns);
    }

    static private void processLines(BufferedReader input, Pattern[] patterns) throws IOException {
        String data;
        while ((data = input.readLine()) != null) {
            for( Pattern pattern : patterns) {
                Matcher m = pattern.matcher(data);
                if (m.find()) {
                    String line = m.group(0);
                    String expresion = m.group(1);
                    String city1 = m.group(2);
                    String operator = m.group(3);
                    String city2 = m.group(4);
                    String garbage = m.group(5);

                    System.out.println();
                    System.out.println(Consola.Color.RED + "Match in line " + lineCounter + " : " + Consola.Color.RESET + line + Consola.Color.RESET);
                    System.out.println(Consola.Color.BLUE + "Expression: " + Consola.Color.RESET + expresion);
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
                            System.out.println("FromTo");
                            map.createRelation(city1, city2);
                            break;
                        case "<-":
                            System.out.println("ToFrom");
                            map.createRelation(city2, city1);
                            break;
                        case "<=":
                            System.out.println("GoToFrom");
                            map.checkIfCanTravelFromTo(city1,city2);
                            break;
                        case "=>":
                            System.out.println("GoFromTo");
                            map.checkIfCanTravelFromTo(city1,city2);
                            break;
                        case "-":
                            System.out.println("Delete connections");
                            map.closeAllConnectionsBetween(city1,city2);
                            break;
                    }
                    System.out.print(Consola.Color.RESET);
                }
            }
            lineCounter++;
        }
    }
}
