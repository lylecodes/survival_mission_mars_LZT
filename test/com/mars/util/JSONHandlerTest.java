package com.mars.util;

import com.mars.objects.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Map;

public class JSONHandlerTest {
    JSONHandler jsonHandler;
    String txtFilePath = "text/game_info.txt";

    @Before
    public void setUp() {
        jsonHandler = new JSONHandler();
    }

    @Test
    public void getFileFromResourceAsStream_shouldReturnNonNullInputStream_whenGivenTextFilePath() {
        InputStream inputStream = JSONHandler.getFileFromResourceAsStream(txtFilePath);
        assertNotNull(inputStream);
    }

    @Test
    public void loadLocationMap_shouldPopulateMapWithLocations_whenGivenJson() {
        Map<String,Location> locationMap = jsonHandler.loadLocationMap();
        assertNotNull(locationMap);
        assertNotNull(locationMap.get("Hydro"));
    }
}