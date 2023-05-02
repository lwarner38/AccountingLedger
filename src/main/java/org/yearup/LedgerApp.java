package org.yearup;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;



public class LedgerApp {

    public LocalDate today = LocalDate.now();

    private Scanner scanner = new Scanner(System.in);
    public void run()
    {
        boolean running = true;
        while (running) {
        displayHomeScreen();
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                addDeposit();
                break;
            case "2":
                viewLedger();
                break;
            case "3":
                makePayment();
                break;
            case "4":
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please select one of the options listed.");
        }
    }





    }





    public void displayHomeScreen()
    {
        //Prompt user for deposit information
        System.out.println("Welcome to your account ledger");
        System.out.println("-----------------------------------------------------");
        System.out.println("1.) Add deposit");
        System.out.println("2.) View ledger");
        System.out.println("3.) Make a payment");
        System.out.println("4.) Exit");
        System.out.println("Please choose a option:  ");



            }

    public void addDeposit()
    {

        Scanner scanner1 = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time.format(formatter);


        System.out.println("Date of deposit: " + date);



        System.out.println("Time of deposit : " + formattedTime);



        System.out.println("Enter a description of the deposit: ");
        // Read input from the user
        String description = "description:"  +  scanner1.next();
        //Write the input to the CSV file
        try
        { FileWriter csvWriter = new FileWriter("transaction.csv");
            csvWriter.append(description);
            csvWriter.append("/n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Description saved.");
        }
        catch (IOException e)
        {
            System.out.println("Error when saving description.");
        }

        System.out.println("Enter the name of the vendor: ");
        String vendor = "vendor:"  +  scanner1.next();

        System.out.println("Enter the amount of the deposit: ");
        String amountStr = scanner1.nextLine();
        double amount = Double.parseDouble(scanner1.nextLine());
        Transaction transaction = new Transaction(date.toString() ,time.toString() ,description,vendor,amount);



    }
    public void viewLedger()
    {
        System.out.println("1.) View all entries ");
        System.out.println("2.) View deposits ");
        System.out.println("3.) View payments ");
        System.out.println("4.) Go back to home screen ");
        System.out.println("Please select a option: ");


    }
    public void makePayment()
    {
        Scanner scanner1 = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println("Date of deposit: " + date);

        System.out.println("Time of deposit : " + time);

        System.out.println("Enter a description of the payment: ");
        String description = "description:" + scanner1.next();

        System.out.println("Enter your vendor name: ");
        String vendor = "vendorName:" + scanner1.next();

        System.out.println("Enter the amount of the payment: ");
        double amount = Double.parseDouble(scanner1.next());
        Transaction payment;
        //payment = new Transaction(date,time,description,vendor,amount);




    }

}



