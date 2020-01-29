package blackjack;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlackJackTable extends Table implements Visualize {
    Integer numDecks;
    Shoe shoe;
    String error;
    Seat dealer;

    public BlackJackTable(Integer minSeats, Integer maxSeats, Integer numDecks) {
        this.maxSeats = maxSeats;
        this.minSeats = minSeats;
        this.numDecks = numDecks;
        this.dealer = new Seat();
        this.dealer.makeDealer();
        this.setupSeats();
    }

    public void sit(Player p, Integer i) {
        this.seats.get(i).addPlayer(p);
    };

    public void leave(Integer i) {
        this.seats.get(i).removePlayer();
    };

    public void play() {
        this.printSpacer();
        this.printSpacer();
        this.printAnnouncement("Starting New Game!");
        this.displaySeats();
        this.waitInput();

        this.shoe = new Shoe(3);

        while (this.shoe.isPlayable()){
            this.clearHand();
            this.dealHand();
            this.displaySeatsWithCards();
            this.waitInput();
        }
    }

    private void clearHand() {
        for (Seat s: this.seats) {
            s.clearCards();
        }
        this.dealer.clearCards();
    }

    private void dealHand() {
        this.dealToSeats();
        this.dealToDealer();
        this.dealToSeats();
        this.dealToDealer();
    }

    private void dealToSeats() {
        for (Seat s: this.seats) {
            if (s.occupied){
                this.shoe.dealCard(s);
            }
        }
    }

    private void dealToDealer() {
        this.shoe.dealCard(this.dealer);
    }

    public Boolean isReady() {
        Integer player_count = this.getCurrentSeatedPlayerCount();
        if (player_count < this.minSeats) {
            this.error = "There are not enough players at the table to start a game...";
        }
        return player_count >= this.minSeats;
    }

    public void displaySeats() {
        this.printSpacer();
        this.printTitle("Seats");
        this.printHorizontalOptions(this.seats.toArray());
        this.printSpacer();
    }
    public void displaySeatsWithCards() {
        this.printSpacer();
        this.printTitle("Current Cards");
        this.printHorizontalOptions(this.seats.toArray());


        Integer counter = 0;
        Boolean still_cards = true;
        while (still_cards) {
            still_cards = false;
            ArrayList<Card> current_cards_for_row = new ArrayList<>();
            for (Seat s: this.seats) {
                Card card = s.getCard(counter);
                if (card != null) {
                    still_cards = true;
                }
                current_cards_for_row.add(card);
            }
            if (still_cards) {
                this.printHorizontalOptions(current_cards_for_row.toArray());
            }
            counter++;
        }
        this.printSpacer();
    }

    public Boolean promptSeatChange() {
        this.printSpacer();
        this.printPrompt("Would you like to change the seats?");
        this.printSpacer();
        Integer i = this.waitResponse(new String[]{"Yes", "No"});
        return i == 0;
    }

    public void changeSeats(ArrayList<Player> possible_players) {
        this.displaySeats();
        this.printPrompt("Which seat would you like to change?");
        this.printSpacer();
        ArrayList<String> currentSeats = new ArrayList<>();
        for (Seat s: this.seats) {
            currentSeats.add(s.getPlayer());
        }
        Integer seatNum = this.waitResponse(currentSeats.toArray());
        this.printSpacer();
        this.printPrompt("Who would you like to sit in seat #" + (seatNum + 1) + "?");
        this.printSpacer();
        Integer playerId = this.waitResponse(possible_players.toArray(), "Clear Player");
        if (playerId >= possible_players.size()) {
            this.seats.get(seatNum).removePlayer();
        } else {
            this.seats.get(seatNum).addPlayer(possible_players.get(playerId));
        }
        this.displaySeats();

    }

    public void displayError() {
        this.printSpacer();
        this.printTitle(this.error);
        this.printSpacer();
    }

    public ArrayList<Seat> getSeats(){
        return this.seats;
    }

    private void setupSeats() {
        for (int i =0; i <this.maxSeats;++i) {
            this.seats.add(new Seat());
        }
    }

    private Integer getCurrentSeatedPlayerCount() {
        Integer i = 0;
        for (Seat seat: this.seats) {
            if (seat.occupied) { ++i; }
        }
        return i;
    }

}
