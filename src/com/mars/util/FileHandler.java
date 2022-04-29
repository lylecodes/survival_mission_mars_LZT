package com.mars.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

/*
 * Helper class to read txt files
 */
class FileHandler {


    //method to read in a text file
    private void readFile(String filePath) throws FileNotFoundException {
        try (BufferedReader reader =
                new BufferedReader(new FileReader(filePath))){
            System.out.println("reading file");
        }
        catch (IOException e) {
            System.out.println("Sorry, file not found");
        }
    }
}