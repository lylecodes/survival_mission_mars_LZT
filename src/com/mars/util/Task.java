package com.mars.util;

import java.util.TimerTask;

// task to execute when timer runs out
public class Task extends TimerTask {
    public void run() {
        System.out.println("I'm sorry, you didn't complete the tasks in time. This is where you starve to death and die!" +
                " Please feel free to try again!");
        System.exit(0);
    }
}