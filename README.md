# 学生信息管理系统
## 说明
本人能力有限，第一次接触Java、MySQL和GIT，有点手忙脚乱，之前的版本更新说明没有写。2020年1月16号开始更新1.2.5版本。<br>
~~需要导入Java jdbc的包(mysql-connector-java-8.0.19),请在社区自行下载(https://dev.mysql.com/downloads/)~~。<br>
~~需要修改数据库IP、账号和密码，见DbUtil类中Connection对象conn。~~ <br>
jar包已经可以通过Maven直接导入，数据库连接请修改jdbc.properties文件。

## 开发日志
### 2020.1.16 - Demo 1.2.5
<li>已知问题(√)：缺少课程导入功能，只能初始化一次课程。</li>
<li>更新了数据导入，解决了再次运行程序之后由于数据库中已经存在数据而操作异常的问题。</li>
<li>删除了初始化课程信息的功能，问题同上，但是更新课程的数据导入功能比较繁琐，需要修改课程初始化的方法，将在接下来的更新中逐步修复。</li>

### 2020.1.17 - Demo 1.2.8
<li>已知问题(√)：尽量使用For循环代替Iteration。</li>
<li>已知问题(√)：查找功能不能通过名称等信息查找。</li>
<li>已知问题(√)：增加相同ID的学生或课程时，在Treeset中可以通过Comparable的比较器避免重复添加，但是在MySQL中无法检测出重复导致程序报错。</li>
<li>已知问题(√)：新增或删除了课程之后student表中未修改column的值，程序中也没有相应的改正。</li>
<li>增加了初始化课程信息的功能，可以在程序再次运行时读取数据库中已经存在的数据。</li>
<li>新增了课程的增删查功能，并且可以同步到MySQL中的subject表中。</li>

### 2020.1.18 - Demo 1.3.3
<li>已知问题(√):可以在程序中手动输入数据库连接信息来连接，这样无需改动Connection的连接。</li>
<li>已知问题(√):修改学生成绩时仍旧只能通过科目名称查找然后修改。</li>
<li>优化了数据库连接初始化的操作，封装了读取数据库信息的方法。</li>
<li>使用Foreach简化了Iteration的迭代，优化了代码中的大部分warning。</li>
<li>通过Maven直接导入jdbc包，无需再从官网下载。</li>
<li>更新了.gitignore文件中的内容，导入(https://www.gitignore.io/)的模板。</li>
<li>新增了通过名称查找学生和课程的功能，现在可以通过ID和名称查找了。查找函数现在单独封装成了多个小方法。</li>
<li>解决了增加相同ID或名称的课程和学生信息之后导致数据库与程序不同步而结束运行的情况。</li>
<li>新增了删改功能中对学生和课程通过ID和名称的查找，现在基本所有方法都采用双重查找方式了。</li>

### 2020.1.19 - Demo 1.3.6
<li>解决了在新增或删除课程的同时在student表中新增或删除列数据，现在MySQL中的两张表格的数据得到了连接，可以同时做出修改。(为了修复这个小Bug对源代码进行了大幅度的修改，感觉有悖于松耦合的原则，所以决定修复1.3.3版本的两个问题之后不再进行大幅度修改)</li>
<li>再次优化了部分Foreach循环，使用流Stream处理循环，聚合操作filter设定筛选规则，最后对Pipelining的元素进行操作。</li>

### 2020.1.21 - Demo 2.0.0
<li>经过两天的思考，觉得这个项目实在是做不下去了，所以有了个大胆的想法，就是结合最近学的Mybatis对其进行重写，使用Mybatis可以简化对数据库中数据增删改查的操作，毕竟现在的程序六七百行而且功能杂糅，重复调用等问题很突出。而且使用Mybatis可以很轻松的解决1.3.3版本中存在的两个问题，四舍五入就算已解决吧</li>
<li>初步想法就是首先把Mybatis的框架搭好，然后根据现在的代码编写相应的功能，等学了spring的相关知识之后再次进行优化</li>

## 2020.1.22 - Demo 2.0.8
<li>已知问题:如何将学生信息表和课程信息表中的信息提取并连接在成绩表中。</li>
<li>已知问题:照理说 not exists 和 exists 意义相反，add里是如果存在就不插入，update里是如果存在就修改，但是add可以按照逻辑正常操作而update却会100%修改或插入数据。</li>
<li>使用了Mybatis框架，完成Mybatis的config文件配置，并且将jdbc的登录信息打包在.properties文件中，无需在代码文件中寻找信息。</li>
<li>创建了学生、课程、成绩三个类以及其对应的表单，但是成绩的接口和mapper配置尚未完成。</li>