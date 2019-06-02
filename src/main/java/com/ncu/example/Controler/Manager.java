package com.ncu.example.Controler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.example.dao.PTDaoImpl;
import com.ncu.example.dao.PlayerDaoImpl;
import com.ncu.example.dao.TeamDaoImpl;
import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.GroupStrategy;
import com.ncu.example.pojo.Player;
import com.ncu.example.pojo.Team;
import jdk.nashorn.internal.scripts.JS;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.*;


public class Manager {
    private List<Player> players;
    private List<Team> teams;
    private GroupStrategy groupStrategy;
    private static Manager manager;

    private PTDaoImpl ptDaoImpl;

    private TeamDaoImpl teamDaoImpl;

    private PlayerDaoImpl playerDaoImpl;


    private  Manager(){
        ptDaoImpl = new PTDaoImpl();
        playerDaoImpl = new PlayerDaoImpl();
        teamDaoImpl = new TeamDaoImpl();
    }

    public static Manager getInstance(){
        if(manager ==null)
            manager= new Manager();
        return manager;
    }

    /**
     * 参赛选手报名
     * @param pid
     * @param name
     */
    public void register(int pid,String name){
        if(players == null)
            players = new ArrayList<>();
        players.add(new Player(pid,name));
    }



    /**
     * 为每个参赛选手分组
     * @param contestType
     */
    public void group(ContestType contestType){
        setGroupStrategy(new GroupStrategy(players,contestType));
        //将新产生的分组加入到teams中
        setTeams(groupStrategy.group());
    }


    //根据选手每次出手击倒的瓶数进行分数统计
//    public void addScore(){
//        players.forEach(e->{
//            List<Integer>[] grades= e.play();
//            for(int i=0;i<10;i++){
//                for(Integer o:grades[i])
//                    e.getScores()[i]+=o;
//
//                int count = 0;
//                if(grades[i].size()==1) count = 2;
//                else if(e.getScores()[i]==10) count = 1;
//
//                for(int j=i+1>9?9:i+1;count>0;j++){
//                    int k=0;
//                    if(j==9) k++;
//                    for(;k<grades[j].size();k++){
//                        e.getScores()[i]+=grades[j].get(k);
//                        count--;
//                        if(count<=0)
//                            break;
//                    }
//                }
//            }
//        });
//    }


    /**
     * 保存参赛选手信息
     * @param
     */
    public void savePlayers(){
        players.forEach(e->playerDaoImpl.insertPlayer(e));
    }



    /**
     * 裁判将小组的信息保存到team表中
     * @param
     */
    public void saveTeams(){
        teams.forEach(e->teamDaoImpl.insertTeam(e));
    }

    /**
     * 裁判保存每个小组参赛队员的成绩
     */
    public void savePt()  {
        teams.forEach(e->{
            for(Player o:e.getMembers()){
                  ptDaoImpl.insertGrade(e);
            }
        });
   }


   public JSONObject getEliteGrade(int pid, String name){
        int tolScoreTmp = 0;
        SqlRowSet rs = ptDaoImpl.findPlayerGrade(pid,name);
        while (rs.next()){
            tolScoreTmp +=rs.getInt("tolScore");
        }

        JSONObject obj = new JSONObject();
        obj.put("pid",pid);
        obj.put("name",name);
        obj.put("tolScore",tolScoreTmp);
        obj.put("contestType",ContestType.ELITE.getDesc());
        return obj;
   }


    /**
     * 对每个小组进行的赛事进行排名
     * @param contestType
     * @return
     */
    public JSONArray ranking(ContestType contestType){
//        return PTDaoImpl.getInstance().findTeamGrade(contestType);
        return ptDaoImpl.findTeamGrade(contestType);
    }


    public void findGrade(int pid,String name){
        ptDaoImpl.findPlayerGrade(pid,name);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public GroupStrategy getGroupStrategy() {
        return groupStrategy;
    }

    public void setGroupStrategy(GroupStrategy groupStrategy) {
        this.groupStrategy = groupStrategy;
    }
}
