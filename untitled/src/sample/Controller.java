package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


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
        String id = InfoId.getText();

        System.out.println(name);
        System.out.println(id);

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