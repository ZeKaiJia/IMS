# 学生信息管理系统
## 说明
本人能力有限，由于第一次接触Java、MySQL和GIT，有点手忙脚乱，之前的版本更新说明没有写。2020年1月16号开始更新2.5版本。<br>
需要导入Java jdbc的包(mysql-connector-java-8.0.19),请在社区自行下载(https://dev.mysql.com/downloads/)。需要修改数据库IP、账号和密码，见DbUtil类中Connection对象conn。
## 2020.1.16 - Demo 2.5
<li>已知问题：缺少课程导入功能，只能初始化一次课程。</li>
<li>更新了数据导入，解决了再次运行程序之后由于数据库中已经存在数据而操作异常的问题。</li>
<li>删除了初始化课程信息的功能，问题同上，但是更新课程的数据导入功能比较繁琐，需要修改课程初始化的方法，将在接下来的更新中逐步修复。</li>

## 2020.1.17 - Demo 2.8
<li>已知问题：尽量使用For循环代替Iteration。</li>
<li>已知问题：查找功能不能通过名称等信息查找。</li>
<li>已知问题：增加相同ID的学生或课程时，在Treeset中可以通过Comparable的比较器避免重复添加，但是在MySQL中无法检测出重复导致程序报错。</li>
<li>已知问题：新增或删除了课程之后student表中为修改column的值，程序中也没有相应的改正。</li>
<li>增加了初始化课程信息的功能，可以在程序再次运行时读取数据库中已经存在的数据。</li>
<li>新增了课程的增删查功能，并且可以同步到MySQL中的subject表中。</li>

## 2020.1.17 Clone Test