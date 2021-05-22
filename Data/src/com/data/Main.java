package com.data;

import java.time.LocalDate;

public class Main
{
    static DataCollector data = new DataCollector();
    static User user = new User();

    public static void main(String[] args)
    {
        int currentYear = LocalDate.now().getYear(); // gets the year from the current local date

        System.out.print("Welcome, enter your name: "); // prints a message

        user.name = data.GetName(); // gets name by input
        user.name = data.CapitalizeString(user.name); // capitalize the name input

        System.out.print("Hello " + user.name + ", enter your date of birth (dd/mm/yyyy): "); // prints a message

        user.dateOfBirth = data.GetDateOfBirth(); // gets a date of birth by input

        user.age = data.CalculateAge(user.dateOfBirth); // sets the age from calculation by date of birth
        String displayDateOfBirth = user.dateOfBirth.format(data.displayDateTimeFormat); // creates a formatted date of birth for display purpose

        System.out.println("As of your date of birth (" + displayDateOfBirth + ") and the current year (" + currentYear + "), you're " + user.age + " years old."); // prints a message
    }
}
