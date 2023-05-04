package org.yearup;
import jdk.jfr.Description;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.List;



public class LedgerApp {

    public LocalDate today = LocalDate.now();

    private Scanner scanner = new Scanner(System.in);
    private String csvFile = "transaction.csv";
    public void run()
    {
        displayHomeScreen();
    }





    public void displayHomeScreen()
    {
        //Prompt user for deposit information

        boolean running = true;
        while (running)
        {
            System.out.println("Welcome to your account ledger");
            System.out.println("-----------------------------------------------------");
            System.out.println("1.) Add deposit");
            System.out.println("2.) View ledger");
            System.out.println("3.) Make a payment");
            System.out.println("4.) Exit");
            System.out.println("Please choose a option:  ");

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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select one of the options listed.");
            }
        }



    }

    public void addDeposit()
    {

        Scanner scanner1 = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);


        System.out.println("Date of deposit: " + date);



        System.out.println("Time of deposit : " + formattedTime);



        System.out.println("Enter a description of the deposit: ");
        // Read input from the user
        String description = scanner1.next();


        System.out.println("Enter the name of the vendor: ");
        String vendor = scanner1.next();

        System.out.println("Enter the amount of the deposit: ");
        String amountStr = scanner1.nextLine();
        double amount = Double.parseDouble(scanner1.next());
        Transaction transaction = new Transaction(date ,time ,description,vendor,amount);

        try
        {
            FileWriter writer = new FileWriter("transaction.csv", true);
            writer.append(transaction.getDate().toString());
            writer.append('|');
            writer.append(transaction.getTime().toString());
            writer.append('|');
            writer.append(transaction.getDescription());
            writer.append('|');
            writer.append(transaction.getVendor());
            writer.append('|');
            writer.append(Double.toString(transaction.getAmount()));
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
        while(true) {

            System.out.println("Ledger Screen");
            System.out.println("---------------------------------------------");
            System.out.println("Please select a option: ");
            System.out.println("\n");
            System.out.println("1.) View all entries ");
            System.out.println("2.) View deposits ");
            System.out.println("3.) View payments ");
            System.out.println("4.) View reports ");
            System.out.println("5.) Go back to home screen ");


            String choice = scanner.nextLine();

            List<Transaction> transactions = readTransactionFromCSV(csvFile);
            switch (choice) {

                case "1":
                    //View all entries

                    for (Transaction transaction : transactions) {

                        System.out.printf("%-15s %-15s %-20s %-15s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                        System.out.println("----------------------------------------------------------------------");
                    }

                    break;
                case "2":
                    //View deposits
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount()>0) {

                            System.out.printf("%-15s %-15s %-20s %-15s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                            System.out.println("----------------------------------------------------------------------");
                        }
                    }
                    break;
                case "3":
                    //View payments
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount() < 0) {

                            System.out.printf("%-15s %-15s %-20s %-15s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                            System.out.println("----------------------------------------------------------------------");
                        }
                    }
                    break;
                case "4":
                    //Display reports
                    displayReports();
                    break;
                case "5":
                    //Display Home screen
                    displayHomeScreen();
                    break;

                default:
                    System.out.println("Invalid choice. Please select one of the listed options. ");


            }
        }
    }

    public void displayReports()
    {
        List<Transaction> transactions = readTransactionFromCSV(csvFile);
        //View reports
        System.out.println("1.) Month to Date");
        System.out.println("2.) Previous Month");
        System.out.println("3.) Year to Date");
        System.out.println("4.) Previous Year");
        System.out.println("5.) Search by Vendor");
        System.out.println("Please select a report option:");

        String reportChoice = scanner.nextLine();

        switch (reportChoice)
        {
            case "1":
                //Generate Month To Date report
                break;
            case "2":
                //Generate Previous Month report
                break;
            case "3":
                //Generate Year To Date report:
                break;
            case "4":
                //Generate Previous Year report
                break;
            case "5":
                //Search by Vendor
                System.out.println("Enter the name of the vendor: ");
                String vendor = scanner.nextLine();

                //Generate report by vendor
                break;
            default:
                System.out.println("Invalid choice. Please select one of the listed options. ");
                break;
        }


    }
    public List<Transaction> readTransactionFromCSV(String csvFile) {
        List<Transaction> transactions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(csvFile)))
        {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\\|");
                String date = fields[0];
                String time = fields[1];
                String description = fields[2];
                String vendor = fields[3];
                Double amount = Double.parseDouble(fields[4]);

                Transaction transaction = new Transaction(LocalDate.parse(date), LocalTime.parse(time), description, vendor, amount);
                transactions.add(transaction);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
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



