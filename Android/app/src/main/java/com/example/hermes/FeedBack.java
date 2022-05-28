package com.example.hermes;

public class FeedBack {
    private final String comment;
    private final int rate;

    public FeedBack(String comment, int rate) {
        this.comment = comment;
        this.rate = rate;
    }

    public String getText() { return this.comment; }
    public int getRate() { return this.rate; }

}
