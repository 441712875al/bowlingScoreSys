# example


## 赛事查询界面

### 赛事成绩查询

```java
调用方法：List<GameScore> findTeamGrade(String contestType);

传入参数:比赛类型(String)
return 包含一个小组分组信息的类的列表
```



### 个人成绩查询

```java
调用方法: List<PersonScore> fingPlayerGrade（int pid,String name);
说明：
- 传入参数: pid(int)，name(String)
- return  包含个人比赛信息的类的列表

```



## 注册界面

```java
调用方法：void register(int id,String name); 
说明：
- 传入参数 id，name
- 无返回
```

## 选手信息管理界面

```java
//查询所有选手的信息
List<Player> findAllPlayer()

说明：
- 无传入参数
- return 返回所有选手的对象

```

