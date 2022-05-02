package com.mars.util;

import com.mars.objects.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.junit.Assert.*;

public class CommandProcessorTest {
    private JSONHandler handler = new JSONHandler();
    private Map<String, Location> locationMap;
    private CommandProcessor commandProcessor = new CommandProcessor();

    @Before
    public void setUp(){

        locationMap = handler.loadLocationMap();
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
}