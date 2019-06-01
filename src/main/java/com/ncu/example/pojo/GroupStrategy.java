package com.ncu.example.pojo;






import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Player;
import com.ncu.example.pojo.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupStrategy {

    private AtomicInteger counter = new AtomicInteger();
    private List<Player> players;
    private ContestType contestType;


    public GroupStrategy(List<Player> players, ContestType contestType) {
        this.players = players;
        this.contestType = contestType;
    }

    /**
     * 根据比赛形式给每个参赛队员分组
     */
    public List<Team> group(){
        Collections.shuffle(players);
        List<Team> teams = new ArrayList<Team>();
        int num = contestType.getPlayerNum();//获取这种比赛需要的队员人数

        for(int i=0;i<players.size()/num;i+=num){
            List<Player> members = new ArrayList<Player>();

            for(int j=i;j<i+num;j++)
                members.add(players.get(i));


            teams.add(new Team(counter.getAndIncrement(),members, ContestType.DOUBLE));
        }
        return teams;
    }






    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }
}
