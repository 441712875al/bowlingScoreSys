package com.ncu.example.pojo;

import java.util.Arrays;
import java.util.List;

public class Team {
    private int id;
    private List<Player> members;
    private int tolScore = 0;



    private ContestType contestType;
    private String membersDesc;

    public Team() {

    }


    public Team(int id, List<Player> members) {
        this.id = id;
        this.members = members;
    }

    public  Team(int id, List<Player> members, ContestType contestType) {
        this.id = id;
        this.members = members;
        this.contestType = contestType;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getMembers() {
        return this.members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }

    public int getTolScore() {
        return tolScore;
    }

    public void setTolScore(int tolScore) {
        this.tolScore = tolScore;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }

    public String getMembersDesc() {
        return Arrays.toString(members.toArray());
    }

    @Override
    public String toString() {
        String desc ="";
        for (Player e : members) {
            desc+=e.getName()+" ";
        }
        return desc;
    }
}
