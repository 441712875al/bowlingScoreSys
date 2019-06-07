package com.ncu.example.pojo;


import com.ncu.example.dao.PTDaoImpl;
import com.ncu.example.dao.PlayerDaoImpl;
import com.ncu.example.dao.TeamDaoImpl;
import com.ncu.example.view.PersonScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Manager {
    private List<Player> players;
    private List<Team> teams;


    @Autowired
    private GroupStrategy groupStrategy;


    @Autowired
    private PTDaoImpl ptDaoImpl;

    @Autowired
    private TeamDaoImpl teamDaoImpl;

    @Autowired
    private PlayerDaoImpl playerDaoImpl;




    /**
     * 参赛选手报名
     * @param pid
     * @param name
     */
    public void register (int pid,String name){
        if(players == null)
            players = new ArrayList<>();
        Player newPlayer = new Player(pid,name);
        players.add(newPlayer);
        savePlayer(newPlayer);
    }



    /**
     * 为每个参赛选手分组
     * @param contestType
     */
    public void group(ContestType contestType){
        players = getPlayers();
        groupStrategy.setPlayers(players);
        groupStrategy.setContestType(contestType);

        //将新产生的分组加入到teams中
        setTeams(groupStrategy.group());
    }



    //根据选手每次出手击倒的瓶数进行分数统计
    public void countScore(){
        teams.forEach(team->{
            for(Player e:team.getMembers()){
                List<Integer>[] grades= e.play();
                for(int i=0;i<10;i++){
                    for(Integer o:grades[i]){
                        e.getScores()[i]+=o;
                    }

                    if(i>=9)
                        continue;

                    int count = 0;//根据情形得出本轮分数额外获得加分次数
                    if(grades[i].size()==1) count = 2;
                    else if(e.getScores()[i]==10) count = 1;

                    for(int j=i+1;count>0;j++){
                        for(int k=0;k<grades[j].size();k++){
                            e.getScores()[i]+=grades[j].get(k);
                            count--;
                            if(count<=0)
                                break;
                        }
                    }
                }
                //获得个人总分和小组的总分
                e.setTolScore(getPlayerTolscore(e));
                team.setTolScore(team.getTolScore()+e.getTolScore());
            }

        });
        save();
    }



    /**
     * 将结果保存到数据库
     */

    public void save(){
        saveTeams();//保存每个组信息

        savePt();//将小组成员的分数保存
    }

    public int getPlayerTolscore(Player player){
        int scoreTmp = 0;
        for(Integer o:player.getScores())
            scoreTmp+=o;
        return scoreTmp;
    }


    /**
     * 保存参赛选手信息
     * @param
     */
    public void savePlayer(Player player){
        playerDaoImpl.insertPlayer(player);
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
                  ptDaoImpl.insertPt(e);
        });
   }


//   public JSONObject getEliteGrade(int pid, String name){
//        int tolScoreTmp = 0;
//        SqlRowSet rs = ptDaoImpl.findPlayerGrade(pid,name);
//        while (rs.next()){
//            tolScoreTmp +=rs.getInt("tolScore");
//        }
//
//        JSONObject obj = new JSONObject();
//        obj.put("pid",pid);
//        obj.put("name",name);
//        obj.put("tolScore",tolScoreTmp);
//        obj.put("contestType",ContestType.ELITE.getDesc());
//        return obj;
//   }
//
//   public PersonScore getGrade


    /**
     * 查询个人比赛的所有成绩
     * @param pid
     * @param name
     * @return
     */
    public List<PersonScore> findGrade(int pid,String name){
        return ptDaoImpl.findPlayerGrade(pid,name);
    }
    

    public List<Player> getPlayers() {
        players = getPlayerDaoImpl().findAllPlayer();
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

    public PTDaoImpl getPtDaoImpl() {
        return ptDaoImpl;
    }

    public TeamDaoImpl getTeamDaoImpl() {
        return teamDaoImpl;
    }

    public PlayerDaoImpl getPlayerDaoImpl() {
        return playerDaoImpl;
    }
}
