package org.yearup;

import java.util.Scanner;

public class LedgerApp {
    private Scanner scanner = new Scanner(System.in);
    public void run()
    {

        displayHomeScreen();


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
        System.out.println("Enter the date of the deposit (YYYY/MM/DD:");
        String date = "date";

        System.out.println("Enter the time of the deposit (HH:MM AM/PM: ");
        String time = "time";

        System.out.println("Enter a description of the deposit: ");
        String description = "description";

        System.out.println("Enter the name of the vendor: ");
        String vendor = "vendor";

        System.out.println("Enter the amount of the deposit: ");
        double amount = Double.parseDouble("");
        Transaction deposit;
        deposit = new Transaction(date,time,description,vendor,amount);

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
        System.out.println("Enter the date of the payment (YYYY/MM/DD:");
        String date = "date";

        System.out.println("Enter the time of the payment (HH:MM AM/PM: ");
        String time = "time";

        System.out.println("Enter a description of the payment: ");
        String description = "description";

        System.out.println("Enter your name: ");



    }

}
    /*public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            displayHomeScreen();
            String choice = scanner.nextLine();
            switch (choice) {
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
                    System.out.println("Invalid choice. Please choose a number from 1 to 4.");
            }*/


