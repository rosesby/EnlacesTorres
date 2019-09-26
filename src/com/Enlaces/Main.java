package com.Enlaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser("src/Data/input");

        BufferedReader in = parser.getIn();

        String pattern = "(Guadalajara3)(.*)";

        Pattern r = Pattern.compile(pattern);

        String data;
        while ((data = in.readLine()) != null){
            Matcher m = r.matcher(data);
            if (m.matches()) {
                System.out.println(data);
            }
        }
    }
}
