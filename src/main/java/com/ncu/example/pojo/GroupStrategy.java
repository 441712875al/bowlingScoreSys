package com.ncu.example.pojo;



import com.ncu.example.dao.PTDaoImpl;
import com.ncu.example.dao.TeamDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class GroupStrategy {

    private List<Player> players;
    private ContestType contestType;

    @Autowired
    private TeamDaoImpl teamDaoImpl;



    /**
     * 根据比赛形式给每个参赛队员分组
     */
    public List<Team> group(){
        int counter = teamDaoImpl.findMaxId();

        Collections.shuffle(players);
        List<Team> teams = new ArrayList<>();
        int num = contestType.getPlayerNum();//获取这种比赛需要的队员人数

        for(int i=0;i<players.size();i+=num){
            List<Player> members = new ArrayList<>();
            for(int j=i;j<i+num;j++){
                members.add(players.get(j));
            }

            teams.add(new Team(++counter,members, getContestType()));
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
