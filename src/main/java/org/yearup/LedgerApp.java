package org.yearup;

import java.util.Scanner;

public class LedgerApp {
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        displayHomeScreen();


    }

    public class Deposit
    {
        private String date;
        private String time;
        private String description;
        private String vendor;
        private double amount;

        public Deposit(String date, String time, String description, String vendor, double amount)
        {
            this.date = date;
            this.time = time;
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }
        public String toCSVString()
        {
            return String.format("%s,%s,%s,%s,%f", date,time,description,vendor,amount);
        }
    }

    public void displayHomeScreen()
    {
        //Prompt user for deposit information
        Scanner scanner = new Scanner(System.in);

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
        Deposit deposit;
        deposit = new Deposit(date,time,description,vendor,amount);

    }
    public void viewLedger()
    {

    }
    public void makePayment()
    {

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


