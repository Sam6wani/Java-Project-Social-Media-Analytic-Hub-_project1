package com.sarmad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final String EXIT_MSG = "Thanks for using Social Media Analyzer!";


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testInvalidInputOption() {
        // Simulate user input for an invalid menu option
        simulateUserInput("6\n");
        // Capture the standard output to check the exit message
        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));

        Main.main(new String[0]);

        String output = outputCapture.toString();
        assertTrue(output.contains(EXIT_MSG), "Exit message not found.");
    }

    private void simulateUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
//
//    private String getExpectedMenu() {
//        return """
//                -------------------------------------------
//                > Select from main menu
//                -------------------------------------------
//                1) Add a social media post
//                2) Delete an existing social media post
//                3) Retrieve a social media post
//                4) Retrieve the top N posts with most likes
//                5) Retrieve the top N posts with most shares
//                6) Exit
//                Please select:\s
//                """;
//    }

}