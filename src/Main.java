import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String [][] cinemaRoom = new String[rows + 1][seats + 1];
        for (int i = 0; i <= rows; i++){
            for (int j = 1; j <= seats; j++){
                if(i == 0){
                    cinemaRoom[i][j] = String.valueOf(j);
                    continue;
                }
                cinemaRoom[i][j] = "S";
            }
            cinemaRoom[i][0] = String.valueOf(i);
        }
        cinemaRoom[0][0] = " ";
        boolean working = true;
        while(working){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int state = scanner.nextInt();
            switch (state){
                case 1:{
                    printRoom(cinemaRoom, rows, seats);
                    break;
                }
                case 2:{
                    ticketPurchase(cinemaRoom, rows, seats);
                    break;
                }
                case 3:{
                    roomStatistic(cinemaRoom, rows, seats);
                    break;
                }
                case 0:{
                    working = false;
                    break;
                }
                default:{
                    System.out.println("Incorrect number (0 - 3)");
                }
            }
        }

    }

    public static void ticketPurchase(String cinemaRoom[][], int rows, int seats){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int ticketRow = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int ticketSeatInThatRow = scanner.nextInt();

        if(ticketRow > 9 || ticketSeatInThatRow > 9){
            System.out.println("Wrong input!");
            ticketPurchase(cinemaRoom, rows, seats);
        }else if(cinemaRoom[ticketRow][ticketSeatInThatRow] == "B") {
            System.out.println("That ticket has already been purchased!");
            ticketPurchase(cinemaRoom, rows, seats);
        }else{
            int ticketPrice = 1;
            if(rows * seats <= 60) {
                ticketPrice = 10;
            } else {
                int firstHalf = rows / 2;
                if(ticketRow <= firstHalf){
                    ticketPrice = 10;
                }else{
                    ticketPrice = 8;
                }
            }
            cinemaRoom[ticketRow][ticketSeatInThatRow] = "B";
            System.out.println("Ticket price: " + "$" + ticketPrice);
        }

    }

    public static void roomStatistic(String cinemaRoom[][], int rows, int seats){
        int totalTicketsPurchased = 0;
        double percentagePurchasedTickets = 1.0;
        int totalIncome = 1;
        int currentIncome = 1;
        for (int i = 0; i <= rows; i++){
            for (int j = 0; j <= seats; j++){
                if(cinemaRoom[i][j] == "B"){
                    totalTicketsPurchased++;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + totalTicketsPurchased);

        percentagePurchasedTickets = ((float)totalTicketsPurchased / (rows * seats)) * 100;
        System.out.printf("Percentage: %.2f" , percentagePurchasedTickets);
        System.out.print("%");
        System.out.println();

        if(rows * seats <= 60){
            currentIncome = totalTicketsPurchased * 10;
            System.out.println("Current income: $" + currentIncome);
            totalIncome = seats * rows * 10;
            System.out.println("Total income: $" + totalIncome);
        }else{
            int firstHalf = rows / 2;
            int foreSeats = 0;
            int backSeats = 0;
            for (int i = 0; i <= rows; i++){
                for (int j = 0; j <= seats; j++){
                    if(i <= firstHalf && cinemaRoom[i][j] == "B"){
                        foreSeats++;
                    }else if (i > firstHalf && cinemaRoom[i][j] == "B"){
                        backSeats++;
                    }
                }
            }
            currentIncome = foreSeats * 10 + backSeats * 8;
            System.out.println("Current income: $" + currentIncome);
            totalIncome =  (firstHalf * seats * 10) + ((rows - firstHalf) * seats * 8);
            System.out.println("Total income: $" + totalIncome);

        }

    }

    public static void printRoom(String cinemaRoom[][], int rows, int seats){
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++){
            for (int j = 0; j <= seats; j++){
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
    }

}
