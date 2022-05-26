package com.example.hermes;

public class FeedBack {
    private final int feedID;
    private final String comment;
    private final int rate;

    public FeedBack(String comment, int rate) {
        this.feedID = idGenerator();
        this.comment = comment;
        this.rate = rate;
    }

    public int getFeedID() { return this.feedID; }
    public String getText() { return this.comment; }
    public int getRate() { return this.rate; }

    public int idGenerator(){
        return (int) this.comment.hashCode();
    }


}
