# example


## 查询界面

### 赛事成绩查询

```java
调用方法：JSONArray findTeamGrade(ContestType contestType);



return 
{
    JSONArray[0]{
        int tId,
        int tolScore,
        int rank,
        Map<Integer,String> playerMap，存储id,name的映射 //单人赛只有一个元素，依次类推
    }//JSONArray[0]是一个JSONObject
}
```



### 个人成绩查询

```java
调用方法：SqlRowSet fingPlayerGrade（int pid,String name);
传入参数 pid，name

return 
SqlROwSet{
    int pId,
    int tId,
    String name,
    int tolScore,
    String  contestType,
    List<Integer> scores
}


//附 SqlRowSet遍历方法
while(sqlRowSet.next){
    int pId = sqlRowSet.getInt("pId");
    String = sqlRowSet.getString;
}
```



## 注册界面

```java
调用方法：void register(int id,String name); 
传入参数 id，name
```



