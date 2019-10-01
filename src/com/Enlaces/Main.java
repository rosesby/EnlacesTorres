package com.Enlaces;

import Util.Consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Map map;

    public static void main(String[] args) throws IOException {
        map = new Map();
        Parser parser = new Parser("src/Data/input");
        BufferedReader in = parser.getIn();

        String patternOpenConnection = "([A-z]+[0-9]*[\\s]*(<-|->)[\\s]*[A-z]+[0-9]*[\\s]*)[.]";
        String patternTravel = "([A-z]+[0-9]*[\\s]*(<=|=>)[\\s]*[A-z]+[0-9]*[\\s]*)[?]";
        String patternCloseConnection = "[A-z]+[0-9]*[\\s]*(-)[\\s]*[A-z]+[0-9]*[\\s]*[.]";

        Pattern connectionPattern = Pattern.compile(patternOpenConnection);

        System.out.println();
        System.out.println(Consola.Color.YELLOW_UNDERLINED + "CONNECTIONS :" + Consola.Color.RESET);
        checkRegex(in, connectionPattern);

        Parser parser2 = new Parser("src/Data/input");
        in = parser2.getIn();
        Pattern travelPattern = Pattern.compile(patternTravel);

        System.out.println();
        System.out.println(Consola.Color.YELLOW_UNDERLINED + "TRAVELS :" + Consola.Color.RESET);
        checkRegex(in, travelPattern);


//
//        Map.Tower t1 = map.createTower("Culiacan");
//        Map.Tower t2 = map.createTower("Mazatlan");
//        Map.Tower t3 = map.createTower("Guadalajara");
//        Map.Tower t4 = map.createTower("Monterrey");
//
//        t1.addLink(t2);
//        t1.addLink(t3);
//        t1.addLink(t4);

//        System.out.println(t1);
    }

    static private void checkRegex(BufferedReader input, Pattern pattern) throws IOException {
        String data;
        while ((data = input.readLine()) != null) {

            Matcher m = pattern.matcher(data);
            if (m.find()) {
                System.out.println();
                System.out.println(Consola.Color.RED + "Match in line x : " + Consola.Color.RESET + data + Consola.Color.RESET);
                String expresion = m.group(1);
                System.out.println(Consola.Color.BLUE + "Expresion: " + Consola.Color.RESET + expresion);
                System.out.println(Consola.Color.CYAN + "Garbage: " +Consola.Color.RESET);
                tokenize(expresion);
            }
        }
    }

    static private void tokenize(String string) {
        String city1;
        String operator;
        String city2;
        StringTokenizer st = new StringTokenizer(string);
        if (st.hasMoreTokens()) {
            city1 = st.nextToken();
            System.out.print(Consola.Color.PURPLE + "City1 : " + Consola.Color.RESET + city1 + " : ");
            checkCity(city1);
        }
        if (st.hasMoreTokens()) {
            operator = st.nextToken();
            System.out.println(Consola.Color.PURPLE + "Operator: " + Consola.Color.RESET + operator);
        }
        if (st.hasMoreTokens()) {
            city2 = st.nextToken();
            System.out.print(Consola.Color.PURPLE + "City2 : " + Consola.Color.RESET + city2 + " : ");
            checkCity(city2);
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
