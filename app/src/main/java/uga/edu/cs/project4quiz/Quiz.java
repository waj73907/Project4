package uga.edu.cs.project4quiz;

import java.time.LocalDate;

public class Quiz {
    private int ID;

    private int amountCorrect;

    private String dateCompleted;

    public Quiz(int ID, int amountCorrect, String dateCompleted) {
        this.ID = ID;
        this.amountCorrect = amountCorrect;
        this.dateCompleted = dateCompleted;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAmountCorrect() {
        return amountCorrect;
    }

    public void setAmountCorrect(int amountCorrect) {
        this.amountCorrect = amountCorrect;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted.toString();
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "ID=" + ID +
                ", amountCorrect=" + amountCorrect +
                ", dateCompleted=" + dateCompleted +
                '}';
    }
}
