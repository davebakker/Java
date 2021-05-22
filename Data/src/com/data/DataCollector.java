package com.data;

import java.util.Scanner;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.lang.String;

public class DataCollector
{
    Scanner scannerObject = new Scanner(System.in); // creates a scanner object
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // creates a custom date time format
    DateTimeFormatter displayDateTimeFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG); // creates a custom date time format for display purpose
    String unicode = "^[\\p{L} .'-]+$"; // creates an unicode character set with allowed characters

    public String GetName()
    {
        while (true)
        {
            String name = scannerObject.nextLine(); // sets the variable to the next line's (user) input
            if (Pattern.matches(unicode, name)) { return name; } // returns the name if it matches the pattern
            else { System.out.print("Incorrect name format, please enter a valid name: "); } // prints an error message
        }
    }

    public String CapitalizeString(String string)
    {
        int length = string.trim().length(); // calculates the length of the complete string

        if ((length == 0)) { return string; } // checks for no value

        string = string.toLowerCase(); // lowercase's the complete string
        char[] characterArray = string.trim().toCharArray(); // trims the string(s) to an array of characters
        characterArray[0] = Character.toUpperCase(characterArray[0]); // uppercase's the first character

        for (int i = 0; i < length; i++) // loops for the length amount
        {
            // uppercase's the first character after space or a specific character
            if (characterArray[i] == ' ' || characterArray[i] == '-' && (i + 1) < length) { characterArray[i + 1] = Character.toUpperCase(characterArray[i + 1]); }
        }

        return new String(characterArray); // returns a string made from the characters
    }

    public LocalDate GetDateOfBirth()
    {
        while (true)
        {
            try { return LocalDate.parse(scannerObject.nextLine(), dateTimeFormat); } // return the next line's (user) input (parsed to the type and date time format)
            catch (Exception exception) { System.out.print("Incorrect date format, please enter a valid date (dd/mm/yyyy): "); } // prints an error message
        }
    }

    public int CalculateAge(LocalDate birthday)
    {
        Period periodDifference = Period.between(birthday, LocalDate.now()); // gets the difference between the date of birth and the current local date
        return periodDifference.getYears(); // returns the age difference in years
    }
}
