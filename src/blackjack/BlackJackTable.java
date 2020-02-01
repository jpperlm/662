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

    public void sit(Player p, Integer i) throws MaxSeatExeception {
        if (this.checkCanSit(p, i)){
            this.seats.get(i).addPlayer(p);
        }else {
            throw new MaxSeatExeception(2);
        }
    };

    public void leave(Integer i) {
        this.seats.get(i).removePlayer();
    };

    public void play() {
        this.printSpacer();
        this.printAnnouncement("Starting New Game!");
        this.displaySeats();
        this.waitInput();

        this.shoe = new Shoe(3);

        while (this.shoe.isPlayable()){
            this.clearHand();
            this.dealHand();
            this.displayAllCardsOnTable(false);
            this.waitInput();

        }
    }


    public Boolean isReady() {
        Integer player_count = this.getCurrentSeatedPlayerCount();
        if (player_count < this.minSeats) {
            this.error = "There are not enough players at the table to start a game...";
        }
        return player_count >= this.minSeats;
    }

    public void displayAllCardsOnTable(Boolean dealer_hand) {
        this.printSpacer();
        this.printAnnouncement("Cards Dealt!");
        this.displayDealerHand(dealer_hand);
        this.displaySeatsWithCards();
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
                this.printHorizontalOptions(current_cards_for_row.toArray(), false);
            }
            counter++;
        }
        this.printSpacer();
    }

    public void displayDealerHand(Boolean showAll) {
        this.printSpacer();
        this.printTitle("Dealer's Cards");

        ArrayList<Card> cards = this.dealer.cards;
        if (showAll) {
            this.printHorizontalOptions(cards.toArray(), false);
        } else {
            Object[] show = { cards.get(0), "Hidden" };
            this.printVerticalOptions(show, false);
        }
    }

    public Boolean promptSeatChange() {
        this.printSpacer();
        this.printPrompt("Would you like to change the seats?");
        this.printSpacer();
        Integer i = this.waitResponse(new String[]{"Yes", "No"});
        return i == 0;
    }

    public void changeSeats(ArrayList<Player> possible_players) throws MaxSeatExeception{
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
            if (this.checkCanSit(possible_players.get(playerId), seatNum)) {
                this.seats.get(seatNum).addPlayer(possible_players.get(playerId));
                this.displaySeats();
            }
            else {
                throw new MaxSeatExeception(2);
            }
        }
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

    private Boolean checkCanSit(Player p, Integer i) {
        int seat_count = 0;
        for (Seat s: this.seats) {
            if (s.getPlayerObject() == p ){
                seat_count++;
            }
        }
        return seat_count != 2;
    }

    // Private Helpers

    //Related To Hands & Dealing
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
    private void dealToDealer() {
        this.shoe.dealCard(this.dealer);
    }
    private void dealToSeats() {
        for (Seat s: this.seats) {
            if (s.occupied){
                this.shoe.dealCard(s);
            }
        }
    }

}
