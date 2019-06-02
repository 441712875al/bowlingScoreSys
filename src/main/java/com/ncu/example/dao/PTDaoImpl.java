package com.ncu.example.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.example.JDBCUtils;
import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Team;
import com.ncu.example.view.GameScore;
import com.ncu.example.view.PersonScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PTDaoImpl implements PTDao {


    private  JdbcTemplate jdbcTemplate;


    public PTDaoImpl() {
        this.jdbcTemplate = JDBCUtils.getJdbcTemplate();
    }

    //保存比赛成绩SQL
    private final static String INSERT_GRADE_SQL= "INSERT INTO pt (pid,tid," +
            "grid1,grid2,grid3,grid4,grid5," +
            "grid6,grid7,grid8,grid9,grid10,tolScore,contestType,fouls) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    //查询小组比赛成绩SQL
    private final static String SELECT_TEAMGRADE_SQL = "select t.tid ,name,teamtolScore,p.pId,contestType from team t,player p,pt where " +
            "p.pId=pt.pid and pt.tId=t.tId and contestType= ? order by t.tId,teamTolScore desc;";


    //查询每种比赛个人的成绩SQL
    private final static String SELECCT_PLAYERGRADE_SQL = "select pt.* ,name,t.tid,t.contestType from player p,pt,team t  " +
            "where p.pid=pt.pid and t.tId=pt.tid and p.pId = ? and p.name =?;";

    //查询小组成员的SQL
//    private final static String SELECT_MEMBERS_SQL = "select pt.pid,name from pt,player p where " +
//            "p.pid=pt.pid and tid = ?";


    /**
     * 向Pt表中插入一个小组分数信息
     * @param team
     */
    @Override
    public void insertGrade(Team team) {

        //插入的数据对象
        team.getMembers().forEach(player->{
            Object[] args = { player.getId(),team.getId(),
                    player.getScores()[0],player.getScores()[1],
                    player.getScores()[2],player.getScores()[3],
                    player.getScores()[4],player.getScores()[5],
                    player.getScores()[6],player.getScores()[7],
                    player.getScores()[8],player.getScores()[9],
                    player.getTolScore(),team.getContestType().getDesc(),
                    player.getFoulsNum()
            };
            try{
                jdbcTemplate.update(INSERT_GRADE_SQL, args);
            }
            catch (Exception e){
                System.out.println("插入失败");
                e.printStackTrace();
            }
        });

    }


    /**
     * 查询指定比赛的成绩并排序
     * @param name
     * @return
     */


    @Override
    public List<GameScore> findTeamGrade(String game) {
        ContestType contestType = null;
        switch (game){
            case "单人赛" : contestType = ContestType.SINGLE;break;
            case "双人赛" :contestType = ContestType.DOUBLE;break;
            case "三人赛" :contestType = ContestType.TRIPLE;break;
            case "五人赛" :contestType = ContestType.QUINTUPLE;break;
        }

        List<GameScore> gameScores = new ArrayList<>();
        Object[] args = {contestType.getDesc()};
        jdbcTemplate.query(SELECT_TEAMGRADE_SQL,args,e->{

            GameScore gameScoreTmp = new GameScore(e.getInt("tId"),e.getInt("pId"),
                    e.getString("name"),e.getInt("teamtolScore"),1);
            gameScores.add(gameScoreTmp);
        });
        return gameScores ;
    }



    /**
     * 查询个人的所有比赛的成绩
     * @param pId
     * @param name
     * @return
     */
    @Override
    public List<PersonScore> findPlayerGrade(int pId, String name){
        List<PersonScore> scores = new ArrayList<>();
        Object[] args = {pId,name};
        jdbcTemplate.query(SELECCT_PLAYERGRADE_SQL, args,e->{
            PersonScore scoretmp = new PersonScore(e.getString("name"),
                    e.getString("contestType"), e.getInt("pId"),
                    e.getInt("tId"),
                    e.getInt("grid1"),e.getInt("grid2"),
                    e.getInt("grid3"),e.getInt("grid4"),
                    e.getInt("grid5"),e.getInt("grid6"),
                    e.getInt("grid7"),e.getInt("grid8"),
                    e.getInt("grid9"),e.getInt("grid10"),
                    e.getInt("playerTolScore"));
            scores.add(scoretmp);
        });
        return scores;
    }

//    @Override
//    public Map<Integer,String> findMembersByTid(int tId) {
//        Map<Integer,String> playerMap = new HashMap<>();
//        Object[] args = {tId};
//        jdbcTemplate.query(SELECT_MEMBERS_SQL,args,e->{
//            playerMap.put(e.getInt("pid"),e.getString("name"));
//        });
//
//        return playerMap;
//    }
}
