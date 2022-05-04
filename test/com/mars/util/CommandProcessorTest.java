package com.mars.util;

import com.mars.objects.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import static jdk.nashorn.internal.objects.Global.print;
import static org.junit.Assert.assertEquals;

public class CommandProcessorTest {
    private JSONHandler handler = new JSONHandler();
    private Map<String, Location> locationMap;
    private CommandProcessor commandProcessor = new CommandProcessor();
    private final PrintStream standardOut = System.out;  // Jeff test
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); // Jeff test

    @Before
    public void setUp(){
        locationMap = handler.loadLocationMap();
        System.setOut(new PrintStream(outputStreamCaptor));  // Jeff test
    }


    @Test
    public void processCommand_shouldReturnNewLocationName_whenCommandVerbAndNoun_IsValid() {
        List<String> command = new ArrayList<>();
        command.add("go");
        command.add("east");
        Location currentLocation = locationMap.get("Docking Station");
        String newLocationName = commandProcessor.processCommand(command,currentLocation,locationMap);
        assertEquals("Green House",newLocationName);
    }

    @Test
    public void processCommand_shouldReturnCurrentLocationName_whenCommandVerb_IsValid_andCommandNoun_isNotValid() {
        List<String> command = new ArrayList<>();
        command.add("go");
        command.add("west");
        Location currentLocation = locationMap.get("Docking Station");
        String newLocationName = commandProcessor.processCommand(command,currentLocation,locationMap);
        assertEquals("Docking Station", newLocationName);
    }

    @Test  // Jeff test
    public void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        List<String> command = new ArrayList<>();
        command.add("go");
        command.add("west");
//        print("C'mon, get right, you can't go that way!");

        Assert.assertEquals("C'mon, get right, you can't go that way!", outputStreamCaptor.toString().trim());
    }

    @After  // Jeff test
    public void tearDown() {
        System.setOut(standardOut);
    }
}