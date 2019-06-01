package com.ncu.example.dao;

import com.ncu.example.pojo.ContestType;
import com.ncu.example.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PTDaoImpl implements PTDao {

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    private static PTDaoImpl gradeDaoImpl;

//    private  PTDaoImpl() {
//        this.jdbcTemplate = JDBCUtils.getJdbcTemplate();
//    }

    /**
     * 单例模式下创建实例
     * @return
     */
//    public static PTDaoImpl getInstance(){
//        if(gradeDaoImpl==null)
//            gradeDaoImpl = new PTDaoImpl();
//        return gradeDaoImpl;
//    }


    //保存比赛成绩SQL
    private final static String INSERT_GRADE_SQL= "INSERT INTO grade(pid,tid," +
            "grid1,grid2,grid3,grid4,grid5," +
            "grid6,grid7,grid8,grid9,grid10,tolScore,contestType,fouls) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //查询小组比赛成绩SQL
    private final static String SELECT_TEAMGRADE_SQL = "select tid ,sum(tolScore) teamScore from grade where contesttype=? group by tid order by teamScore desc;";


    //查询每种比赛个人的成绩SQL
    private final static String SELECCT_PLAYERGRADE_SQL = "select pt.pid,pt.tid,name,tolScore,contestType  " +
            " from player p,team t ,pt where p.pid=pt.pid and t.tid=pt.tid " +
            "and pt.pid= ? and p.name= ?";

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
    public List<Map<Integer,Integer>> findTeamGrade(ContestType contestType) {
        List<Map<Integer,Integer>> results = new ArrayList<>();
        Object[] args = {contestType.getDesc()};
        jdbcTemplate.query(SELECT_TEAMGRADE_SQL,args,e->{
            Map<Integer,Integer> row = new HashMap<>();
            row.put(e.getInt("tid"),e.getInt("teamScore"));
            results.add(row);
        });
        return results;
    }

    /**
     * 查询个人的所有比赛的成绩
     * @param pid
     * @param name
     * @return
     */
    @Override
    public SqlRowSet findPlayerGrade(int pid,String name){
        Object[] args = {pid,name};
        return  jdbcTemplate.queryForRowSet(SELECCT_PLAYERGRADE_SQL, args);
    }
}
