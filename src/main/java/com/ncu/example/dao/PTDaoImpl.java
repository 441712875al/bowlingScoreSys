package com.ncu.example.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PTDaoImpl implements PTDao {

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    private static PTDaoImpl gradeDaoImpl;



    //保存比赛成绩SQL
    private final static String INSERT_GRADE_SQL= "INSERT INTO pt (pid,tid," +
            "grid1,grid2,grid3,grid4,grid5," +
            "grid6,grid7,grid8,grid9,grid10,tolScore,contestType,fouls) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //查询小组比赛成绩SQL
    private final static String SELECT_TEAMGRADE_SQL = "select tid ,sum(tolScore) teamScore from pt where" +
            " contesttype=? group by tid order by teamScore desc;";


    //查询每种比赛个人的成绩SQL
    private final static String SELECCT_PLAYERGRADE_SQL = "select pt.*,name,contestType, " +
            " from player p,team t ,pt where p.pid=pt.pid and t.tid=pt.tid " +
            "and pt.pid= ? and p.name= ?";

    //查询小组成员的SQL
    private final static String SELECT_MEMBERS_SQL = "select pt.pid,name from pt,player p where " +
            "p.pid=pt.pid and tid = ?";


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
     * @param contestType
     * @return
     */


    @Override
    public JSONArray findTeamGrade(ContestType contestType) {
        JSONArray jsonArray = new JSONArray();
        Object[] args = {contestType.getDesc()};
        jdbcTemplate.query(SELECT_TEAMGRADE_SQL,args,e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tid",e.getInt("tid"));
            jsonObject.put("tolScore",e.getInt("teamScore"));
            jsonObject.put("rank",e.getRow());
            jsonObject.put("playerMap",findMembersByTid(e.getInt("tid")));
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }



    /**
     * 查询个人的所有比赛的成绩
     * @param pId
     * @param name
     * @return
     */
    @Override
    public SqlRowSet findPlayerGrade(int pId, String name){
        Object[] args = {pId,name};
        return  jdbcTemplate.queryForRowSet(SELECCT_PLAYERGRADE_SQL, args);
    }

    @Override
    public Map<Integer,String> findMembersByTid(int tId) {
        Map<Integer,String> playerMap = new HashMap<>();
        Object[] args = {tId};
        jdbcTemplate.query(SELECT_MEMBERS_SQL,args,e->{
            playerMap.put(e.getInt("pid"),e.getString("name"));
        });

        return playerMap;
    }
}
