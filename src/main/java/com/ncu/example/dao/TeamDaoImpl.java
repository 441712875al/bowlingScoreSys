package com.ncu.example.dao;

import com.ncu.example.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    /**
     * 插入小组的sql
     */
    private final String INSERT_TEAM_SQL = "insert into team (tid,teamtolScore,contestType) values (?,?,?)";

    private final String SELECT_MAX_TID_SQL = "select max(tid) maxID from team";
    /**
     * 将小组的信息插入到team表中
     * @param team 队伍信息类
     */
    @Override
    public void insertTeam(Team team) {
        Object[] args = {team.getId(),team.getTolScore(),team.getContestType().getDesc()};
            jdbcTemplate.update(INSERT_TEAM_SQL,args);
    }

    @Override
    public int findMaxId() {
        List<Integer> maxId = new ArrayList<>();
        jdbcTemplate.query(SELECT_MAX_TID_SQL,e->{
            maxId.add(e.getInt("maxId"));
        });

        return maxId.get(0);
    }


}
