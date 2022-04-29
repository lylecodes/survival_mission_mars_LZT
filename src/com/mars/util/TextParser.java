package com.mars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class TextParser {
    //method to remove "extra" words from user input (to, a, the, etc)
    private String cleanUserInput(String userInput){
        //TODO logic to strip extra words
        return userInput;
    }
    public List<String> getCommand(String userInput){
        List<String> cmdInput = new ArrayList<>();
        String cleanInput = cleanUserInput(userInput);
        //Parse into verb + noun
        cleanInput = cleanInput.toLowerCase();
        cmdInput = Arrays.asList(cleanInput.split(" "));
        System.out.println(cmdInput); //testing
        return cmdInput;

    }
}
