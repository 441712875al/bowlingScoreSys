package com.ncu.example.view;

public class GameScore {
    private int tid;
    private int pid;
    private String name;
    private int Score;
    private int rank;

    public GameScore(int tid, int pid, String name, int score, int rank) {
        this.tid = tid;
        this.pid = pid;
        this.name = name;
        Score = score;
        this.rank = rank;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
