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
        communication.showProjections(movieManager.getUpcomingProjections());
        communication.show("Choose projection number");
        Projection chosenProjection = getChosenProjection();
        communication.showTheaterOccupation(chosenProjection);
        communication.show("How many tickets do you want");
        int tickets = maxPurchasableTickets(chosenProjection);
        purchaseTicket(chosenProjection, tickets);

    }

    private void purchaseTicket(Projection chosenProjection, int tickets) {
        Ticket[] purchasedTickets = new Ticket[tickets];
        for (int i = 0; i < tickets; i++) {
            communication.show("Please enter row");
            int row = getRow(chosenProjection);
            communication.show("Please enter seat number");
            int col = getCol(chosenProjection);
            while (chosenProjection.getTheater().isSeatOccupied(row, col)) {
                System.out.println("This seat is occupied");
                communication.show("Please enter another row");
                row = getRow(chosenProjection);
                communication.show("Please enter another seat number");
                col = getCol(chosenProjection);
            }
            chosenProjection.getTheater().occupySeat(row, col);
            chosenProjection.addTicket(row,col);
            purchasedTickets[i] = new Ticket(row,col,chosenProjection.getProjectionDate(),chosenProjection.getMovieTitle());
            communication.showTheaterOccupation(chosenProjection);
        }
        communication.show("Please confirm your purchase - Press 1 to confirm, Press any button to decline");
        int choice = communication.getNumberInput();
        if (choice == 1) {
            movieManager.saveChanges(chosenProjection);
            printTicket(purchasedTickets);
        }
    }

    private int getCol(Projection projection) {
        int col = communication.getNumberInput() - 1;
        while (!(col < projection.getTheater().getSeats()[0].length)) {
            col = communication.getNumberInput() - 1;
        }
        return col;
    }

    private int getRow(Projection projection) {
        int row = communication.getNumberInput() - 1;
        while (!(row < projection.getTheater().getSeats().length)) {
            row = communication.getNumberInput() - 1;
        }
        return row;
    }

    private int maxPurchasableTickets(Projection projection) {
        int numberOfFreeTickets = projection.getTheater().freeSeats();
        int tickets = communication.getNumberInput();
        while (tickets <= numberOfFreeTickets) {
            tickets = communication.getNumberInput();
        }
        return tickets;
    }

    private Projection getChosenProjection() {
        int projectionNumber = communication.getNumberInput() - 1;
        while (!(projectionNumber < movieManager.getUpcomingProjections().size())) {
            projectionNumber = communication.getNumberInput() - 1;
        }
        return movieManager.getUpcomingProjections().get(projectionNumber);
    }

    void printTicket(Ticket[] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            communication.printTicket(tickets[i]);
        }

    }

}
