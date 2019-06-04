package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sample.tableInfo.gameScore;
import sample.tableInfo.personScore;


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
     * 赛事管理界面控件
     */
    @FXML
    private ChoiceBox gameType;
    @FXML
    private TableView gameTable;


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

    @FXML
    public void startGame(){
        //比赛类型，个人，双人赛，3人赛
        String game = (String) gameType.getSelectionModel().getSelectedItem();

        //用于连接表格的数据模型
        gameScore[] score = new gameScore[100];
        for(int i=0;i<100;i++){{
            score[i] = new gameScore(1,1,"ha"+i,10+i,10+i);
        }}


        ObservableList<gameScore> data = FXCollections.observableArrayList(score);
        String[] tableList = {"tid","pid","name","score","rank"};
        for(int i=0;i<5;i++){
            ((TableColumn) gameTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<gameScore, String>(tableList[i]));
        }


        ((TableColumn) gameTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<gameScore, String>("pid"));
        ((TableColumn) gameTable.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<gameScore, String>("name"));
        ((TableColumn) gameTable.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<gameScore, String>("score"));
        ((TableColumn) gameTable.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory<gameScore, String>("rank"));

        gameTable.setItems(data);
    }

    @FXML
    private TextField resultName;
    @FXML
    private TextField resultId;
    @FXML
    private TableView resultTable;
    @FXML
    public void queryResult(){
        //查询对象信息
      /*  String name = resultName.getText();
        int id = Integer.parseInt(resultId.getText());*/

      //把数据存到personScore数组就行了！！！！！！！我这里是模拟数据的查询
        personScore[] score = new personScore[100];
        for(int i=0;i<100;i++){
            score[i] = new personScore("a"+i,"2",2,2,1,2,3,4,5,6,7,8,9,10,10+i);
        }

        ObservableList<personScore> data = FXCollections.observableArrayList(score);
        String[] tableList = {"gameType","pid","tid","name","score1","score2","score3","score4","score5","score6","score7","score8","score9","score10","totalScore"};
        for(int i=0;i<15;i++){
            ((TableColumn)resultTable.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<gameScore, String>(tableList[i]));
        }
        resultTable.setItems(data);
    }

}