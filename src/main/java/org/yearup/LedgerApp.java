package org.yearup;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;



public class LedgerApp {

    public LocalDate today = LocalDate.now();

    private Scanner scanner = new Scanner(System.in);
    private String csvFile = "transaction.csv";
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
        //FileWriter writer= new FileWriter("transaction.csv");

        //Check if the writer is not null before closing it
        /*finally {
            try
            {
                if (writer != null) {
                    writer.close();
                }
            }
            catch(IOException e)
            {
                System.out.println("Error when closing writer. ");
            }
        }*/

        System.out.println("Enter the name of the vendor: ");
        String vendor = "vendor:"  +  scanner1.next();

        System.out.println("Enter the amount of the deposit: ");
        String amountStr = scanner1.nextLine();
        double amount = Double.parseDouble(scanner1.next());
        Transaction transaction = new Transaction(date.toString() ,time.toString() ,description,vendor,amount);

        try
        {
            FileWriter writer = new FileWriter("transaction.csv", true);
            writer.append(transaction.getDate());
            writer.append(",");
            writer.append(transaction.getTime());
            writer.append(",");
            writer.append(transaction.getDescription());
            writer.append(",");
            writer.append(transaction.getVendor());
            writer.append(",");
            writer.append(Double.toString(transaction.getAmount()));
            writer.append(",");
            writer.append("\n");
            writer.flush();
            writer.close();
            System.out.println("Transaction saved.");


        }
        catch (IOException e)
        {
            System.out.println("Error when saving description.");
        }

    }
    public void viewLedger()
    {
        System.out.println("1.) View all entries ");
        System.out.println("2.) View deposits ");
        System.out.println("3.) View payments ");
        System.out.println("4.) View reports ");
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

        System.out.println("Enter your debit/credit information: ");
        String description = "debit/credit card number:" + scanner1.next() + "Expiration" + scanner1.next();

        System.out.println("Enter your vendor/cardholder name: ");
        String vendor = "vendorName:" + scanner1.next();

        System.out.println("Enter the amount of the payment: ");
        double amount = Double.parseDouble(scanner1.next());
        Transaction payment;
        //payment = new Transaction(date,time,description,vendor,amount);




    }

}



