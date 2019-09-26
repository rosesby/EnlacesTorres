package com.Enlaces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class Parser {

    String dataPath = "src/data.txt";
    BufferedReader in;

    public Parser() throws IOException {
       in = new BufferedReader(new FileReader(dataPath));
    }

    String line = in.readLine();

}
