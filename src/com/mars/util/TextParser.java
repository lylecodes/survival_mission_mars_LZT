package com.mars.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {
    //method to remove "extra" words from user input (to, a, the, etc)
    private String cleanUserInput(String userInput){
        //TODO logic to strip extra words
        return userInput;
    }
    public List<String> getCommand(String userInput){
        List<String> cmdInput = new ArrayList<>(); //empty arraylist to store parsed command
        String cleanInput = cleanUserInput(userInput); //pass string through function to strip out extra words

        //Parse into verb + noun
        cleanInput = cleanInput.toLowerCase(); //make string all lowercase
        cmdInput = Arrays.asList(cleanInput.split(" ")); //split into two on the space " "
        return cmdInput; //return the list of verb, noun

    }
}
