package com.sarmad.Menu;

import com.sarmad.Exceptions.InvalidMenuOptionException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;

    public MenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readMenuChoice() throws InvalidMenuOptionException {
        System.out.print("Enter your choice: ");
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new InvalidMenuOptionException("Invalid Menu option entered.");
        } catch (NoSuchElementException e) {
            System.out.println("Thanks for using Social Media Analyzer!");
        }
        return choice;
    }


}
