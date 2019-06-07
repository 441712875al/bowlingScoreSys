package sample.tableInfo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Team {
    private int id;
    private List<Player> members;
    private int tolScore = 0;
    private ContestType contestType;

    public Team() {

    }

    public  Team(int id, List<Player> members, ContestType contestType) {
        this.id = id;
        this.members = members;
        this.contestType = contestType;
    }


    /**
     * 计算该组的总成绩
     * @return
     */
    public void caculateToltalScore(){
        members.forEach(e->{
            tolScore+=e.getTolScore();
        });
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMembers() {
        return Arrays.toString(members.toArray());
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


}
