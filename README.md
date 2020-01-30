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
<li>解决了在新增或删除课程的同时在student表中新增或删除列数据，现在MySQL中的两张表格的数据得到了连接，可以同时做出修改。(为了修复这个小Bug对源代码进行了大幅度的修改，感觉有悖于松耦合的原则，所以决定修复1.3.3版本的两个问题之后不再进行大幅度修改)。</li>
<li>再次优化了部分Foreach循环，使用流Stream处理循环，聚合操作filter设定筛选规则，最后对Pipelining的元素进行操作。</li>

### 2020.1.21 - Demo 2.0.0
<li>经过两天的思考，觉得这个项目实在是做不下去了，所以有了个大胆的想法，就是结合最近学的Mybatis对其进行重写，使用Mybatis可以简化对数据库中数据增删改查的操作，毕竟现在的程序六七百行而且功能杂糅，重复调用等问题很突出。而且使用Mybatis可以很轻松的解决1.3.3版本中存在的两个问题，四舍五入就算已解决吧。</li>
<li>初步想法就是首先把Mybatis的框架搭好，然后根据现在的代码编写相应的功能，等学了spring的相关知识之后再次进行优化。</li>

### 2020.1.22 - Demo 2.1.1
<li>已知问题(√):如何将学生信息表和课程信息表中的信息提取并连接在成绩表中。</li>
<li>已知问题(√):照理说 not exists 和 exists 意义相反，add里是如果存在就不插入，update里是如果存在就修改，但是add可以按照逻辑正常操作而update却会100%修改或插入数据。</li>
<li>使用了Mybatis框架，完成Mybatis的config文件配置，并且将jdbc的登录信息打包在.properties文件中，无需在代码文件中寻找信息。</li>
<li>创建了学生、课程、成绩三个类以及其对应的表单，但是成绩的接口和mapper配置尚未完成。</li>
<li>修复了update失效的bug。</li>
<li>增加了多参数多返回结果的查询方法和模糊查找的方法。</li>
<li>完成了Score类的mapper和xml文件，可以采用多种方式查询结果。</li>
<li>至此，整个学生管理系统的完整框架已经搭建完成，接下来只需完成UI部分的代码，各种菜单、功能以及交互即可。</li>

### 2020.1.23 - Demo 2.1.5
<li>已知问题(√):Service中写的组合增删改查方法都有返回值，可以返回相应的信息，不过暂时Controller中只能提示操作成功还是失败，后期再加入操作数据的回显，先记一笔以防忘记。</li>
<li>已知问题:输入的日期只有年月日，而数据库中还包括星期等信息，无法进行比较和搜索。可以使用cast语句将数据库中的date转换成string，但是太复杂，先把查询生日的功能去除。</li>
<li>已知问题(√):添加的日期存储在数据库时会减少一天。</li>
<li>采用entity层构建POJO原子实体，controller层获取用户输入数据并且提交给service层处理，service层组合了mapper层中对应mapper里的一个或多个数据库操作方法，最后在test中进行各种测试。</li>
<li>由于输入时间转换成伦敦标准时间会产生差异，所以需要通过Calender类再加一天(目前我是这么想的，具体是不是这个原因导致日期减少了一天还有待考证，因为通过debug可以发现我输入的0点0分被转换成了前一天的下午1点被存进了数据库)。</li>
<li>在ManagerController中提供入口，调用MenuController中的提示信息进行用户层的输入。</li>
<li>记录一个很严重的问题，就是每次对数据库进行操作完成之后的destroy操作一定要记得写，destroy里包含了对sqlSession的commit操作，只有commit之后数据库中的数据才会改变，因为少写了这个我花了大量的时间进行debug还找不出原因，实在不应该。</li>

### 2020.1.24 - Demo 2.1.7
<li>记录另一个严重的问题，sqlSession同时只能存在一个，也就是说对Mysql数据库的访问是串行的，如果操作完之后没有销毁而调用另一个sqlSession程序就会崩溃，解决方法就是不要在外部Controller中调用两次service中方法，因为我的连接池初始化实在service中建立的，应该在service类中对mapper的功能进行组合封装，将mapper的原子功能组合成Controller中需要用到的功能。</li>
<li>解决了如何将学生信息表和成绩信息表结合成成绩表中的内容。</li>
<li>至此，学生成绩管理系统已经初步完成，今天修复一些小bug，优化一下之后明天或者后天发布正式版。</li>

### 2020.1.26 - Demo 2.1.9
<li>由于近期过春节，正式版推迟发布。可能要等到项目应用了Spring的技术之后，或许还会开发前端的网页。</li>
<li>更新了信息回显功能，现在运行添加删除和更新功能如果成功会显示出刚刚操作的数据(修改功能显示修改后的数据)。</li>
<li>解决了添加date信息时时间不正确的问题，是因为Mybatis将Java的date转换成数据库信息的时候采用了Calendar操作，这个操作会附带有时区信息，而本地系统内部是CTS(美国)时区，所以转换会产生误差，在MySQL中修改为东八区即可。</li>
<li>修改了jdbc配置文件，采用 Unicode UTF-8。</li>

### 2020.1.27 - Demo 2.2.0
<li>已知问题(√):使用spring之后原来的mapper文件无法扫描到，导入的POJO包名也失效了。使用MapperScan注释没用，将mapper.xml中的对象改为具体路径没用，在yml文件中设置别名也没用。运行程序报NullPointerException错误。</li>
<li>使用了Spring框架，将数据库工厂的创建和提交等封装在SqlBiz中。</li>

### 2020.1.28 - Demo 2.2.4
<li>已知问题(√):在mapper的xml文件中可以使用where和if等标签，筛选合理的输入内容，可以解决用户输入错误信息后造成的程序奔溃现象。先记一笔，内容较多有空再改。</li>
<li>已知问题(√):全是问题。前端网页该如何布置，功能如何与后端关联？Controller层如何改写(事实是模板类基本没用过不知道怎么操作)？多亏了大佬教现在也才初步了解了如何用postman测试web API请求以及简单增删改查的步骤。</li>
<li>最近两天都在尝试如何使用一个模板类完成Sqlsession的获取以及commit操作，可以减少很多重复的代码，但是目前还没成功。因为表格分三个，每个表的操作又不止增删改查的基础步骤，想要统一较为困难。</li>
<li>借鉴了一个对Date数据进行相关操作的Util类，增加了Response类处理HTTP请求。</li>
<li>修复了几个bug。</li>

### 2020.1.30 - Demo 2.2.7
<li>删除了mapper中的no exist语句，因为service封装mapper功能的时候已经将增删改查的基础功能组合在了一起，mapper应该完成其最基本的功能。</li>
<li>正在画前端页面的原型图，深入思考前端与后端的关联性，对于Controller层应当抛弃原本的增删改查四个Controller，因为前端是先选择表再选择功能，所以对应的应该有三个表的Controller类。</li>
<li>添加了mapper中的where和if标签，提高预判性。并且在score中加入了关联student的resultMap，使用双向一对多映射，有望实现三个表的关联。</li>
<li>删除了Mybatis的配置文件，全部由spring进行管理,删除之后mapper内的POJO类名会报错，这是IDEA自身的问题，实际运行时可以由spring扫描到，为了不显示红色还是使用了包名。</li>