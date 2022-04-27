package com.company.cinemaManagement;

import com.company.cinema.MovieManager;
import com.company.cinema.Projection;
import com.company.cinema.Ticket;
import com.company.communication.Communication;

public class TicketPurchaseProcedure {
    MovieManager movieManager;
    Communication communication;

    public TicketPurchaseProcedure(MovieManager movieManager, Communication communication) {
        this.movieManager = movieManager;
        this.communication = communication;
    }

    void initializePurchase() {

        if (movieManager.getUpcomingProjections().size() != 0) {
            communication.showProjections(movieManager.getUpcomingProjections());
            communication.show("Choose projection number");
            Projection chosenProjection = getChosenProjection();
            communication.showTheaterOccupation(chosenProjection);
            communication.show("Number of free tickets:" + " " + chosenProjection.getTheater().seeFreeSeats());
            communication.show("How many tickets do you want");
            int tickets = maxPurchasableTickets(chosenProjection);
            purchaseTicket(chosenProjection, tickets);
        } else {
            communication.show("There are no more projections for the today.\n " +
                    "Please try again tomorrow.");
        }
    }

    private void purchaseTicket(Projection chosenProjection, int tickets) {
        Ticket[] purchasedTickets = new Ticket[tickets];
        for (int i = 0; i < tickets; i++) {
            communication.show("Please enter row number");
            int row = getRow(chosenProjection);
            communication.show("Please enter seat number");
            int col = getCol(chosenProjection);
            while (chosenProjection.getTheater().isSeatOccupied(row, col)) {
                System.out.println("This seat is occupied");
                communication.show("Please enter another one\n" + "Please enter row");
                row = getRow(chosenProjection);
                communication.show("Please enter seat number");
                col = getCol(chosenProjection);
            }

            chosenProjection.getTheater().occupySeat(row, col);
            chosenProjection.addTicket(row, col);
            purchasedTickets[i] = new Ticket(row, col, chosenProjection.getProjectionDate(), chosenProjection.getMovieTitle());
            communication.showTheaterOccupation(chosenProjection);
        }

        communication.show("Please confirm your purchase - Press 1 to confirm," +
                " Press any other positive number to decline");
        int confirmationChoice = communication.getNumberInput();
        if (confirmationChoice == 1) {
            movieManager.saveChanges(chosenProjection);
            printTicket(purchasedTickets);
        }
    }

    private int getCol(Projection projection) {
        int col = communication.getNumberInput() - 1;
        while (!(col < projection.getTheater().getSeats()[0].length)) {
            communication.show("There isn't such seat number. Please enter an existing seat number");
            col = communication.getNumberInput() - 1;
        }
        return col;
    }

    private int getRow(Projection projection) {
        int row = communication.getNumberInput() - 1;
        while (!(row < projection.getTheater().getSeats().length)) {
            communication.show("There isn't such row. Please enter an existing row number.");
            row = communication.getNumberInput() - 1;
        }
        return row;
    }

    private int maxPurchasableTickets(Projection projection) {
        int numberOfFreeTickets = projection.getTheater().seeFreeSeats();
        int tickets = communication.getNumberInput();
        while (tickets >= numberOfFreeTickets) {
            communication.show("Not that many tickets available. Please enter a smaller amount");
            tickets = communication.getNumberInput();
        }

        return tickets;
    }

    private Projection getChosenProjection() {
        int projectionNumber = communication.getNumberInput() - 1;
        while (!(projectionNumber < movieManager.getUpcomingProjections().size())) {
            communication.show("There is no projection under this number. Try again!");
            projectionNumber = communication.getNumberInput() - 1;
        }
        return movieManager.getUpcomingProjections().get(projectionNumber);
    }

    private void printTicket(Ticket[] tickets) {
        for (Ticket ticket : tickets) {
            communication.printTicket(ticket);
        }
    }
}
