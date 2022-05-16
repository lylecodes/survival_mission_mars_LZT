package com.mars.puzzle;

//interface to define but not implement behavior (establishes what but not how)
public interface Puzzle {
//any declared variables here would be constants (static final)

//methods each challenge must implement
    public String showIntro();
    public void runPuzzle();
    public boolean isSolved();
    public String getName();

}