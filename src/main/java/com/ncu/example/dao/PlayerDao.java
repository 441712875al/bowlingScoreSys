package com.ncu.example.dao;


import com.ncu.example.pojo.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao {
    /**
     * 登记参赛选手信息
     * @param player 选手信息类
     */
    int insertPlayer(Player player);

    /**
     *查询所有参赛选手
     * @return 查询到的所有选手
     */
    List<Player> findAllPlayer();

    int  deletePlayer(Player player);


}
