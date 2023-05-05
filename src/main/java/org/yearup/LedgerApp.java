package org.yearup;
import jdk.jfr.Description;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Calendar;
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
        //Display home screen options

        boolean running = true;
        while (running)
        {
            System.out.println(ColorCodes.PURPLE +  "Welcome to your account ledger"+ ColorCodes.RESET);
            System.out.println("-----------------------------------------------------");
            System.out.println( "\u001B[33m1.) Add deposit\u001B[33m");
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
            System.out.println(ColorCodes.GREEN);
            System.out.println("Transaction saved.");
            System.out.printf("$%.2f successfully deposited\n", transaction.getAmount());
            System.out.println("\n");
            System.out.println( ColorCodes.GREEN + "Returning to Home Screen...." + ColorCodes.RESET);


        }
        catch (IOException e)
        {
            System.out.println("Error when saving transaction.");
        }

    }
    public void viewLedger()
    {
        while(true) {
            System.out.println("\n");
            System.out.println(ColorCodes.BLACK_BACKGROUND + "Ledger Screen" + ColorCodes.RESET);
            System.out.println(ColorCodes.WHITE_BACKGROUND + "---------------------------------------------"+ ColorCodes.RESET);
            System.out.println(ColorCodes.ORANGE + "Please select a option: ");
            System.out.println("\n");
            System.out.println("1.) View all entries ");
            System.out.println("2.) View deposits ");
            System.out.println("3.) View payments ");
            System.out.println("4.) View reports ");
            System.out.println("5.) Go back to home screen " + ColorCodes.RESET);


            String choice = scanner.nextLine();

            List<Transaction> transactions = readTransactionFromCSV(csvFile);
            switch (choice) {

                case "1":
                    //View all entries

                    for (Transaction transaction : transactions) {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);

                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount() );
                        System.out.println(ColorCodes.RESET);
                        System.out.println("-------------------------------------------------------------------------------------------------------");
                    }

                    break;
                case "2":
                    //View deposits
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount()>0) {
                            System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                            System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                            System.out.println(ColorCodes.RESET);
                            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }
                    }
                    break;
                case "3":
                    //View payments
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount() < 0) {
                            System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                            System.out.printf( ColorCodes.RED_BACKGROUND +"%-15s %-15s %-20s %-15s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                            System.out.println(ColorCodes.RESET);
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
        //Current Month & Year
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        List<Transaction> allTransactions = readTransactionFromCSV(csvFile);

        // Previous Month & Year
        LocalDate previousMonth = LocalDate.now().minusMonths(1);
        int previousMonthValue = previousMonth.getMonthValue();

        LocalDate previousYear = LocalDate.now().minusYears(1);
        int previousYearValue = previousYear.getYear();



        //View report options
        System.out.println(ColorCodes.CYAN + "1.) Month to Date");
        System.out.println("2.) Previous Month");
        System.out.println("3.) Year to Date");
        System.out.println("4.) Previous Year");
        System.out.println("5.) Search by Vendor");
        System.out.println("Please select a report option:" + ColorCodes.RESET);

        String reportChoice = scanner.nextLine();

        List<Transaction> currentMonthTransactions = new ArrayList<>();
        switch (reportChoice) {

            case "1":
                //Generate Month to date report
                for (Transaction transaction : allTransactions) {
                    if (transaction.getDate().getMonthValue() == currentMonth && transaction.getDate().getYear() == currentYear) {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                        System.out.println(ColorCodes.RESET);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;

            case "2":
                //Generate Previous Month report
                for (Transaction transaction : allTransactions) {
                    if (transaction.getDate().getMonthValue() == previousMonthValue && transaction.getDate().getYear() == previousYearValue) {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                        System.out.println(ColorCodes.RESET);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;

            case "3":
                //Generate Year To Date report:
                for (Transaction transaction : allTransactions) {
                    if (transaction.getDate().getYear() == currentYear) {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                        System.out.println(ColorCodes.RESET);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;
            case "4":
                //Generate Previous Year report
                for (Transaction transaction : allTransactions) {
                    if (transaction.getDate().getYear() == previousYearValue && transaction.getDate().getYear() == previousYearValue) {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                        System.out.println(ColorCodes.RESET);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }

                break;

            case "5":
                //Search by Vendor
                System.out.println(ColorCodes.GREEN_BACKGROUND + "Enter the name of the vendor: "+ ColorCodes.RESET);
                String vendor = scanner.nextLine();


                for (Transaction transaction : allTransactions)
                {
                    if (transaction.getVendor().equalsIgnoreCase(vendor))
                    {
                        System.out.printf(ColorCodes.BLACK_BACKGROUND + " %-15s %-15s %-28s %-25s %10s\n","Date", "Time", "Description", "Vendor", "Amount" + ColorCodes.RESET);
                        System.out.printf(ColorCodes.RED_BACKGROUND + "%-15s %-15s %-28s %-25s %10f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                        System.out.println(ColorCodes.RESET);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;
            default:
                System.out.println(ColorCodes.RED + "Invalid choice. Please select one of the listed options. " + ColorCodes.RESET);
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
        int month = today.getMonthValue();
        int year = today.getYear();

        System.out.println("Date of deposit: " + date);

        System.out.println("Time of deposit : " + time);

        System.out.println("Enter your payment description: ");
        String description = scanner1.next();

        System.out.println("Enter the vendor name: ");
        String vendor = scanner1.next();

        System.out.println("Enter the amount of the payment: ");
        double amount = Double.parseDouble(scanner1.next());
        Transaction payment;
        //payment = new Transaction(date,time,description,vendor,amount);
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
            System.out.println(ColorCodes.GREEN + "Transaction saved.");
            System.out.printf("$%.2f successfully paid \n", transaction.getAmount());
            System.out.println(ColorCodes.RESET);
            System.out.println("\n");


        }
        catch (IOException e)
        {
            System.out.println("Error when saving transaction.");
        }




    }

}



