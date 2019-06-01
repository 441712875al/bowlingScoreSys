package com.ncu.example.dao;

import com.ncu.example.JDBCUtils;
import com.ncu.example.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static  TeamDaoImpl teamDaoImpl;

//    private  TeamDaoImpl() {
//        this.jdbcTemplate = JDBCUtils.getJdbcTemplate();
//    }
//
//    public  static  TeamDaoImpl getInstance(){
//        if(teamDaoImpl==null)
//            teamDaoImpl = new TeamDaoImpl();
//        return teamDaoImpl;
//    }

    /**
     * 插入小组的sql
     */
    private final String INSERT_TEAM_SQL = "insert into team (tid,contestType) values (?,?)";

    /**
     * 将小组的信息插入到team表中
     * @param team
     */
    @Override
    public void insertTeam(Team team) {
        Object[] args = {team.getId(),team.getContestType().getDesc()};
        try{
            jdbcTemplate.update(INSERT_TEAM_SQL,args);
        }catch (Exception e){
            System.out.println("插入失败");
            e.printStackTrace();
        }
    }
}
