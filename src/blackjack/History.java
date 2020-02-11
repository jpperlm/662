package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class History<T, S> {
    T base;
    ArrayList<S> items = new ArrayList<>();

    public void add (T t) {
        this.base = t;
    }

    public void addItem (S s) {
        this.items.add(s);
        this.save(s);
    }

    public ArrayList<S> items() {
        return this.items;
    }

    public void save(S s) {
        try {
            File file = new File("/Users/jasonperlman/IdeaProjects/Perlman_BlackJack_Tutor/src/blackjack/history.txt");
            FileWriter fr = new FileWriter(file, true);
            fr.write( this.base + "::" + s.toString() + "\n");
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAll() {
        try {
            File file = new File("/Users/jasonperlman/IdeaProjects/Perlman_BlackJack_Tutor/src/blackjack/history.txt");
            FileWriter fr = new FileWriter(file, true);
            StringBuilder output = new StringBuilder();
            for (S item: this.items){
                output.append(item);
                output.append(",");
            }
            fr.write("History related to::" + this.base + "::");
            fr.write(output.toString() + "\n");
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
