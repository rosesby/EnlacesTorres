package com.Enlaces;

import Util.Consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
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

        System.out.println();
        System.out.println(Consola.Color.YELLOW_UNDERLINED + "CONNECTIONS :" + Consola.Color.RESET);

        System.out.println();
        System.out.println(Consola.Color.YELLOW_UNDERLINED + "TRAVELS :" + Consola.Color.RESET);

        checkRegex(in, patterns);
    }

    static private void checkRegex(BufferedReader input, Pattern[] patterns) throws IOException {
        String data;
        while ((data = input.readLine()) != null) {
            for( Pattern pattern : patterns) {
                Matcher m = pattern.matcher(data);
                if (m.find()) {
/*                    for (int i = 0; i <= m.groupCount(); i++) {
                        System.out.println(i + " : " +m.group(i));
                    }*/

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
                    checkCity(city1);

                    System.out.println(Consola.Color.PURPLE + "Operator: " + Consola.Color.RESET + operator);

                    System.out.print(Consola.Color.PURPLE + "City2 : " + Consola.Color.RESET + city2 + " : ");
                    checkCity(city2);

                    System.out.print(Consola.Color.PURPLE + "Result : " + Consola.Color.GREEN);
                    map.createRelation(city1,city2);
                    System.out.print(Consola.Color.RESET);
                }
            }
            lineCounter++;
        }

    }

    static private void checkCity(String strCity){
        boolean check = map.checkIfTowerExistsByName(strCity);
        if(check){
            System.out.println(Consola.Color.GREEN + "Already Exists" + Consola.Color.RESET);
        }
        else{
            map.createCity(strCity);
            System.out.println(Consola.Color.GREEN + "City was created" + Consola.Color.RESET);
        }
    }
}
