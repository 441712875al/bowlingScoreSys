package com.ncu.example.pojo;

public enum ContestType {
    SINGLE("单人赛",1),
    DOUBLE("双人赛",2),
    TRIPLE("三人赛",3),
    QUINTUPLE("五人赛",5),
    ELITE("精英赛",1);

    private String desc;
    private int playerNum;

    ContestType(String desc, int playerNum) {
        this.desc = desc;
        this.playerNum = playerNum;
    }

    public String getDesc() {
        return desc;
    }

    public int getPlayerNum() {
        return playerNum;
    }
}
