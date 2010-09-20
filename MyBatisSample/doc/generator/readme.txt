在Eclipse上手動安裝MyBatis Generator插件步驟：

   1. 打開"Help>Software Updates..."菜單；
   2. 點擊"Available Software" 鏈接；
   3. 點擊"Add Site" 按鈕；
   4. 輸入Name：Mybatis Generator location:http://ibatis.apache.org/tools/ibator
   5. 點擊"OK"
   6. 勾選複選框，點擊"next"直到 "MyBatis Generator Feature" ；
   7. 點擊"Install" 按鈕；
   8. 直到安裝完成；


Command：
java -jar ibator.jar -configfile ibatorConfig.xml -overwrite
也可以使用ANT


一些問題：
1.Generator出來的DAO無法使用，但generator的xml設定檔裡的daoGenerator標籤不能省！
2.gen出來的xml裡，有一sql：ibatorgenerated_Example_Where_Clause，會產生錯誤！拿掉就可以使用了。
===>原來是namespace設定沒有打開，<settings useStatementNamespaces="true" /> 加上即可！！