package com.mars.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;

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
}