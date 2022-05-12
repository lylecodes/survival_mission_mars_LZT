package com.mars.gui;
/*
 *
 */

public class MiddleMan {
    // INSTANCE VARIABLES
    static GameGui gui;
    // CONSTRUCTOR

    public MiddleMan(GameGui gui){
        this.gui = gui;
    }

    // BUSINESS METHODS
    public void updateGUI(String gameString){
        gui.updateStory(gameString);
    }

    public String listener(){
        String response = gui.getUserResponse();
        while(response.equals("")){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response = gui.getUserResponse();
        }
        System.out.println("HERE:"+ response);
//       reset string to ""
        gui.setUserResponse("");

        return response;
    }

    // ACCESSOR METHODS

    // toString()
}
