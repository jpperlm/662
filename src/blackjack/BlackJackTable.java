package blackjack;

import java.util.ArrayList;

// Represents a single Black Jack Table
// Has the ability to interact with a user as a real table would
// Players can sit at the table, hands will be dealt and BJ rules apply
public class BlackJackTable extends Table implements Visualize {
    Integer numDecks = 1; // Default num of Decks per shoe
    Integer minSeats = 1; // Minimum num seats to start a game
    Integer maxSeats = 3; // Max seats available at the table
    Seat dealer;
    Shoe shoe;

    public BlackJackTable(Integer minSeats, Integer maxSeats, Integer numDecks) {
        this.maxSeats = maxSeats;
        this.minSeats = minSeats;
        this.numDecks = numDecks;
        this.dealer = new Seat();
        this.dealer.makeDealer();
        this.setupSeats();
    }

    // Runs the table, all other logic should be hidden from runner
    // For now this is going to accept an Array list of players, these will be the potential players for the game.
    public void loop(ArrayList<Player> potential_players) {
        this.pre_game_prep(potential_players);
        this.play_game();
    }

    // Setting up table for game, the game is not started at this point.
    private void pre_game_prep(ArrayList<Player> potential_players) {
        this.printStatus("Table Setup");
        this.seatChangeLoop(potential_players);
    }
    // Loops through the actual display and game logic
    private void play_game() {
        this.printStatus("Game Start");
        this.displaySeats();
        this.waitInput();
        this.play();
    }
    // Output results and any additional information
    private void post_game() {

    }

    // Private Logic Loop Helpers

    // Makes a new Shoe
    // While a shoe has enough cards, continue to deal hands and prompt
    //  players for their inputs
    private void play() {
        this.shoe = new Shoe(this.numDecks);
        while (this.shoe.isPlayable()){
            this.clearHand();
            this.dealHand();
            this.displayAllCardsOnTable(false);
            this.waitInput();
        }
    }

    // Displays the current seats and prompts user if they would
    //  like to adjust the seating arrangement.
    private void seatChangeLoop(ArrayList<Player> p) {
        Boolean change_seats = true;
        while (change_seats) {
            this.displaySeats();
            change_seats = this.promptSeatChange();
            if (change_seats) {
                try {
                    this.changeSeats(p);
                } catch (MaxSeatExeception err) {
                    this.displayError(err.toString());
                }
            } else if (!this.isReady()) {
                this.displayError("There are not enough players at the table to start a game...");
                change_seats = true;
            }
        }
    }

    private Boolean promptSeatChange() {
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
            this.leave(seatNum);
        } else {
            this.sit(possible_players.get(playerId), seatNum);
        }
    }
    // Sits a player at the specified integer seat
    // If the player is already occupying more than 2 seats an exception is thrown
    private void sit(Player p, Integer i) throws MaxSeatExeception {
        if (this.checkCanSit(p, i, 2)) {
            this.seats.get(i).addPlayer(p);
        } else {
            throw new MaxSeatExeception(2);
        }
    };

    private Boolean checkCanSit(Player p, Integer i, Integer max) {
        int seat_count = 0;
        for (Seat s: this.seats) {
            if (s.getPlayerObject() == p ){
                seat_count++;
            }
        }
        return seat_count != max;
    }

    private void leave(Integer i) {
        this.seats.get(i).removePlayer();
    };


    // Private Display Helpers

    private void displaySeats() {
        this.printSpacer();
        this.printTitle("Seats");
        this.printHorizontalOptions(this.seats.toArray());
        this.printSpacer();
    }

    private void displayError(String err) {
        this.printSpacer("x");
        this.printTitle(err);
        this.printSpacer("x");
    }

    private Boolean isReady() {
        Integer player_count = this.getCurrentSeatedPlayerCount();
        return player_count >= this.minSeats;
    }

    private void displayAllCardsOnTable(Boolean dealer_hand) {
        this.printSpacer();
        this.printAnnouncement("Cards Dealt!");
        this.displayDealerHand(dealer_hand);
        this.displaySeatsWithCards();
    }

    private void displaySeatsWithCards() {
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

    private void displayDealerHand(Boolean showAll) {
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

    private ArrayList<Seat> getSeats(){
        return this.seats;
    }

    private Integer getCurrentSeatedPlayerCount() {
        Integer i = 0;
        for (Seat seat: this.seats) {
            if (seat.occupied) { ++i; }
        }
        return i;
    }


    // Private Helpers

    // Runs once, at object creation - fills seats array with number of empty seats
    private void setupSeats() {
        for (int i =0; i <this.maxSeats;++i) {
            this.seats.add(new Seat());
        }
    }

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
