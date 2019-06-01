package com.ncu.example.pojo;

import com.ncu.example.dao.PTDaoImpl;
import com.ncu.example.dao.PlayerDaoImpl;
import com.ncu.example.dao.TeamDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class Manager {
//    private static Manager manager;
    private List<Player> players;
    private List<Team> teams;
    private GroupStrategy groupStrategy;

    @Autowired
    private PTDaoImpl ptDaoImpl;


    @Autowired
    private TeamDaoImpl teamDaoImpl;

    @Autowired
    private PlayerDaoImpl playerDaoImpl;
//    private Manager() {
//        players = new ArrayList<>();
//        teams = new ArrayList<>();
//    }

//    public static Manager getInstance(){
//        if(manager==null)
//            manager = new Manager();
//        return manager;
//    }


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


   public Properties getEliteGrade(int pid,String name){
        int tolScoreTmp = 0;
        SqlRowSet rs = ptDaoImpl.findPlayerGrade(pid,name);
        while (rs.next()){
            tolScoreTmp +=rs.getInt("tolScore");
        }

        Properties properties = new Properties();
        properties.setProperty("pid",pid+"");
        properties.setProperty("name",name);
        properties.setProperty("tolScore",tolScoreTmp+"");
        properties.setProperty("contestType",ContestType.ELITE.getDesc());
        return properties;
   }


    /**
     * 对每个小组进行的赛事进行排名
     * @param contestType
     * @return
     */
    public List<Map<Integer,Integer>> ranking(ContestType contestType){
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
