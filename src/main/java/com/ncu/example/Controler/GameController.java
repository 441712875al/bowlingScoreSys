package com.ncu.example.Controler;

import com.ncu.example.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class GameController {
    @Autowired
    private Manager manager;

}
