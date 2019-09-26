package com.Enlaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    BufferedReader in;

    public BufferedReader getIn() {
        return in;
    }

    public Parser(String dataPath) throws IOException {
        FileReader fileReader = new FileReader(dataPath);
        in = new BufferedReader(fileReader);
    }


    public String readLine() throws IOException {
        return in.readLine();
    }


}
