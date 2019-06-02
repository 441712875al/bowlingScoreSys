package com.ncu.example.Controler;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;




@FXMLController
public class Controller {
    /**
     * 三个主面板
     */
    @FXML
    private Pane informationPane;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane resultPane;

    private Manager manager = Manager.getInstance();


    /**
     *信息管理界面的文本控件
     */
    @FXML
    private TextField InfoName;

    @FXML
    private TextField InfoId;


    /**
     * 对信息管理进行响应，切换右侧界面
     */
    @FXML
    protected void Info(){
        closeAll();
        informationPane.setVisible(true);
    }

    /**
     * 对信赛事管理进行响应，切换右侧界面
     */
    @FXML
    protected void Game(){
        closeAll();
        gamePane.setVisible(true);
    }

    /**
     * 对信结果查询进行响应，切换右侧界面
     */
    @FXML
    protected void Result(){
        closeAll();
        resultPane.setVisible(true);
    }

    /**
     * 关闭右侧所有面板
     */
    public void closeAll(){
        informationPane.setVisible(false);
        gamePane.setVisible(false);
        resultPane.setVisible(false);
    }

    /**
     * 对增加player事件进行响应
     */
    @FXML
    public void addPlayer(){
        String name = InfoName.getText();
        int id = Integer.parseInt(InfoId.getText());
        if(manager==null)
            System.out.println("null");
        manager.register(id,name);
        manager.savePlayers();
    }



    @FXML
    public void queryPlayer(){

    }

    @FXML
    public void updatePlayer(){

    }

    @FXML
    public void deletePlayer(){

    }

}