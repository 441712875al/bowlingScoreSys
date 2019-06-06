package com.ncu.example.dao;

import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Player;
import com.ncu.example.pojo.Team;
import com.ncu.example.view.GameScore;
import com.ncu.example.view.PersonScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PTDaoImpl implements PTDao {


    @Autowired
    private  JdbcTemplate jdbcTemplate;



    //保存比赛成绩SQL
    private final static String INSERT_GRADE_SQL= "" +
            "INSERT INTO pt (pid,tid," +
            "grid1,grid2,grid3,grid4,grid5," +
            "grid6,grid7,grid8,grid9,grid10,playertolScore,fouls) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //查询小组比赛成绩SQL
    private final static String SELECT_TEAMGRADE_SQL =
            "select t.tid,name,teamtolScore,p.pId,contestType, rank() over(ORDER BY teamTolScore desc) degree " +
                    "from team t,player p,pt\n" +
                    "where p.pid=pt.pid and pt.tId=t.tId and contestType=? ";


    //查询每种比赛个人的成绩SQL
    private final static String SELECCT_PLAYERGRADE_SQL = "" +
            "select pt.* ,name,t.tid,t.contestType from player p,pt,team t  " +
            "where p.pid=pt.pid and t.tId=pt.tid and p.pId = ? and p.name =?";

    //查询每个小组及其队员的信息SQL
    private final static String SELECT_TEAMANDPLAYER_SQL = "select t.tid,pt.pid,name,contestType from team t,pt,player p " +
            "where t.tid=pt.tid and pt.pid=p.pid order by 1;";


    /**
     * 向Pt表中插入一个小组分数信息
     * @param team
     */
    @Override
    public void insertPt(Team team) {

        //插入的数据对象
        team.getMembers().forEach(player->{
            Object[] args = { player.getId(),team.getId(),
                    player.getScores()[0],player.getScores()[1],
                    player.getScores()[2],player.getScores()[3],
                    player.getScores()[4],player.getScores()[5],
                    player.getScores()[6],player.getScores()[7],
                    player.getScores()[8],player.getScores()[9],
                    player.getTolScore(), player.getFouls()
            };
            jdbcTemplate.update(INSERT_GRADE_SQL, args);
        });
    }


    /**
     * 查询指定比赛的成绩并排序
     * @param contestType
     * @return
     */
    @Override
    public List<GameScore> findTeamGrade(ContestType contestType) {
        List<GameScore> gameScores = new ArrayList<>();
        Object[] args = {contestType.getDesc()};
        jdbcTemplate.query(SELECT_TEAMGRADE_SQL,args,e->{

            GameScore gameScoreTmp = new GameScore(e.getInt("tId"),e.getInt("pId"),
                    e.getString("name"),e.getInt("teamtolScore"),e.getInt("degree"));
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
                    e.getInt("fouls"), e.getInt("playerTolScore"));
            scores.add(scoretmp);
        });
        return scores;
    }

    @Override
    public List<Team> findTeamINfo() {
        List<Team> teamList = new ArrayList<>();
        jdbcTemplate.query(SELECT_TEAMANDPLAYER_SQL,e-> {
            if(!teamList.isEmpty()&&
                    teamList.get(teamList.size()-1).getId()==e.getInt("tID")){
                teamList.get(teamList.size()-1).getMembers().add(
                        new Player(e.getInt("pId"),e.getString("name")));
                return ;
            }

            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(e.getInt("pId"),e.getString("name")));
            teamList.add(new Team(e.getInt("tId"),playerList));
        });

        return teamList;
    }


}
