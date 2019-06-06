package com.ncu.example.dao;

import com.ncu.example.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 插入小组的sql
     */
    private final String INSERT_TEAM_SQL = "insert into team (tid,tolScore,contestType) values (?,?,?)";

    /**
     * 将小组的信息插入到team表中
     * @param team 队伍信息类
     */
    @Override
    public void insertTeam(Team team) {
        Object[] args = {team.getId(),team.getTolScore(),team.getContestType().getDesc()};
        try{
            jdbcTemplate.update(INSERT_TEAM_SQL,args);
        }catch (Exception e){
            System.out.println("插入失败");
            e.printStackTrace();
        }
    }
}
