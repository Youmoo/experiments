spring+ibatis 多数据源配置
--------------------------
1. 将db.sql导入到mysql
2. 运行App.java
3. 查看运行结果```select * from db1.user union select * from db2.user;```