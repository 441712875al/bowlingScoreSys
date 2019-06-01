package com.ncu.example.dao;


import com.ncu.example.pojo.Player;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;


public interface PlayerDao {
    /**
     * 登记参赛选手信息
     * @param player
     */
    void insertPlayer(Player player);

    /**
     *查询所有参赛选手
     * @return
     */
    List<Player> findAllPlayer();
}
