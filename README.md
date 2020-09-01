# 信息管理系统 Information Management System
## 说明
本人能力有限，第一次接触 Java 、MySQL 和 GIT ，有点手忙脚乱，之前的版本更新说明没有写。2020年1月16号开始更新1.2.5版本。<br>
~~需要导入 Java jdbc 的包( MySQL -connector-java-8.0.19),请在社区自行下载( https://dev.mysql.com/downloads/ )~~。<br>
~~需要修改数据库 IP 、账号和密码，见 DbUtil 类中 Connection 对象 conn 。~~ <br>
~~jar 包已经可以通过 Maven 直接导入，数据库连接请修改 jdbc.properties 文件。~~

## 层次结构
5层 Application -> 程序运行的入口 <br>
4层 Util & VO -> 提供使用工具类和 HTTP Response 的协助层 <br>
4层 Controller -> 业务逻辑层，提供前端接口，获取数据给 Service <br>
3层 Service -> 将 Mapper 中一个或多个功能组合成实际需要的功能 <br>
2层 Mapper -> 与 XML 文件唯一对应，提供最基本的数据库功能 <br>
1层 XML -> 使用 Mybatis 语法编写的 Mapper 配置文件供上层调用 <br>
0层 Entity -> POJO 对象(纯粹的JavaBean)，不提供业务和服务 <br>
配置层 application.yml -> Spring 配置 pom.xml -> Maven配置

## 开发日志
### 2020.1.16 - Demo 1.2.5
- 已知问题(√)：缺少课程导入功能，只能初始化一次课程。
- 更新了数据导入，解决了再次运行程序之后由于数据库中已经存在数据而操作异常的问题。
- 删除了初始化课程信息的功能，问题同上，但是更新课程的数据导入功能比较繁琐，需要修改课程初始化的方法，将在接下来的更新中逐步修复。

### 2020.1.17 - Demo 1.2.8
- 已知问题(√): 查找功能不能通过名称等信息查找。
- 已知问题(√): 尽量使用 For 循环代替 Iteration 。
- 已知问题(√): 新增或删除了课程之后 student 表中未修改 column 的值，程序中也没有相应的改正。
- 已知问题(√): 增加相同 ID 的学生或课程时，在 Treeset 中可以通过Comparable的比较器避免重复添加，但是在 MySQL 中无法检测出重复导致程序报错。
- 新增了课程的增删查功能，并且可以同步到 MySQL 中的 subject 表中。
- 增加了初始化课程信息的功能，可以在程序再次运行时读取数据库中已经存在的数据。

### 2020.1.18 - Demo 1.3.3
- 已知问题(√): 修改学生成绩时仍旧只能通过科目名称查找然后修改。
- 已知问题(√): 可以在程序中手动输入数据库连接信息来连接，这样无需改动 Connection 的连接。
- 通过 Maven 直接导入 jdbc 包，无需再从官网下载。
- 优化了数据库连接初始化的操作，封装了读取数据库信息的方法。
- 使用 Foreach 简化了 Iteration 的迭代，优化了代码中的大部分 warning 。
- 更新了 .gitignore 文件中的内容，导入( https://www.gitignore.io/ )的模板。
- 解决了增加相同 ID 或名称的课程和学生信息之后导致数据库与程序不同步而结束运行的情况。
- 新增了删改功能中对学生和课程通过 ID 和名称的查找，现在基本所有方法都采用双重查找方式了。
- 新增了通过名称查找学生和课程的功能，现在可以通过ID和名称查找了。查找函数现在单独封装成了多个小方法。

### 2020.1.19 - Demo 1.3.6
- 再次优化了部分 Foreach 循环，使用流 Stream 处理循环，聚合操作 filter 设定筛选规则，最后对 Pipelining 的元素进行操作。
- 解决了在新增或删除课程的同时在 student 表中新增或删除列数据，现在 MySQL 中的两张表格的数据得到了连接，可以同时做出修改。(为了修复这个小 Bug 对源代码进行了大幅度的修改，感觉有悖于松耦合的原则，所以决定修复1.3.3版本的两个问题之后不再进行大幅度修改)。

### 2020.1.21 - Demo 2.0.0
- 初步想法就是首先把 Mybatis 的框架搭好，然后根据现在的代码编写相应的功能，等学了 Spring 的相关知识之后再次进行优化。
- 经过两天的思考，觉得这个项目实在是做不下去了，所以有了个大胆的想法，就是结合最近学的 Mybatis 对其进行重写，使用 Mybatis 可以简化对数据库中数据增删改查的操作，毕竟现在的程序六七百行而且功能杂糅，重复调用等问题很突出。而且使用 Mybatis 可以很轻松的解决1.3.3版本中存在的两个问题，四舍五入就算已解决吧。

### 2020.1.22 - Demo 2.1.1
- 已知问题(√): 如何将学生信息表和课程信息表中的信息提取并连接在成绩表中。
- 已知问题(√): 照理说 not exists 和 exists 意义相反， add 里是如果存在就不插入， update 里是如果存在就修改，但是 add 可以按照逻辑正常操作而 update 却会100%修改或插入数据。
- 修复了 update 失效的 Bug 。
- 增加了多参数多返回结果的查询方法和模糊查找的方法。
- 完成了 Score 类的 Mapper 和 xml 文件，可以采用多种方式查询结果。
- 创建了学生、课程、成绩三个类以及其对应的表单，但是成绩的接口和 Mapper 配置尚未完成。
- 至此，整个学生管理系统的完整框架已经搭建完成，接下来只需完成 UI 部分的代码，各种菜单、功能以及交互即可。
- 使用了 Mybatis 框架，完成 Mybatis 的config文件配置，并且将 jdbc 的登录信息打包在 .properties 文件中，无需在代码文件中寻找信息。

### 2020.1.23 - Demo 2.1.5
- 已知问题(√): 添加的日期存储在数据库时会减少一天。
- 已知问题(√): Service 中写的组合增删改查方法都有返回值，可以返回相应的信息，不过暂时 Controller 中只能提示操作成功还是失败，后期再加入操作数据的回显，先记一笔以防忘记。
- 已知问题:输入的日期只有年月日，而数据库中还包括星期等信息，无法进行比较和搜索。可以使用 cast 语句将数据库中的 date 转换成 string ，但是太复杂，先把查询生日的功能去除。
- 采用 entity 层构建 POJO 原子实体， Controller 层获取用户输入数据并且提交给 Service 层处理， Service 层组合了 Mapper 层中对应 Mapper 里的一个或多个数据库操作方法，最后在 test 中进行各种测试。
- 在 ManagerController 中提供入口，调用 MenuController 中的提示信息进行用户层的输入。
- 由于输入时间转换成伦敦标准时间会产生差异，所以需要通过 Calender 类再加一天(目前我是这么想的，具体是不是这个原因导致日期减少了一天还有待考证，因为通过 deBug 可以发现我输入的0点0分被转换成了前一天的下午1点被存进了数据库)。
- 记录一个很严重的问题，就是每次对数据库进行操作完成之后的 destroy 操作一定要记得写， destroy 里包含了对 sqlSession 的 commit 操作，只有 commit 之后数据库中的数据才会改变，因为少写了这个我花了大量的时间进行 deBug 还找不出原因，实在不应该。

### 2020.1.24 - Demo 2.1.7
- 解决了如何将学生信息表和成绩信息表结合成成绩表中的内容。
- 至此，学生成绩管理系统已经初步完成，今天修复一些小Bug，优化一下之后明天或者后天发布正式版。
- 记录另一个严重的问题， sqlSession 同时只能存在一个，也就是说对 MySQL 数据库的访问是串行的，如果操作完之后没有销毁而调用另一个 sqlSession 程序就会崩溃，解决方法就是不要在外部 Controller 中调用两次 Service 中方法，因为我的连接池初始化实在 Service 中建立的，应该在 Service 类中对 Mapper 的功能进行组合封装，将 Mapper 的原子功能组合成 Controller 中需要用到的功能。

### 2020.1.26 - Demo 2.1.9
- 修改了 jdbc 配置文件，采用 Unicode UTF-8。
- 由于近期过春节，正式版推迟发布。可能要等到项目应用了 Spring 的技术之后，或许还会开发前端的网页。
- 更新了信息回显功能，现在运行添加删除和更新功能如果成功会显示出刚刚操作的数据(修改功能显示修改后的数据)。
- 解决了添加 date 信息时时间不正确的问题，是因为 Mybatis 将 Java 的 date 转换成数据库信息的时候采用了 Calendar 操作，这个操作会附带有时区信息，而本地系统内部是 CTS (美国)时区，所以转换会产生误差，在 MySQL 中修改为东八区即可。

### 2020.1.27 - Demo 2.2.0
- 已知问题(√): 使用 Spring 之后原来的 Mapper 文件无法扫描到，导入的 POJO 包名也失效了。使用 MapperScan 注释没用，将 Mapper.xml中的对象改为具体路径没用，在 yml 文件中设置别名也没用。运行程序报 NullPointerException 错误。
- 使用了 Spring 框架，将数据库工厂的创建和提交等封装在 SqlBiz 中。

### 2020.1.28 - Demo 2.2.4
- 已知问题(√): 在 Mapper 的xml文件中可以使用 where 和 if 等标签，筛选合理的输入内容，可以解决用户输入错误信息后造成的程序奔溃现象。先记一笔，内容较多有空再改。
- 已知问题(√): 全是问题。前端网页该如何布置，功能如何与后端关联？ Controller 层如何改写(事实是模板类基本没用过不知道怎么操作)？多亏了大佬教现在也才初步了解了如何用 postman 测试 web API 请求以及简单增删改查的步骤。
- 修复了几个 Bug 。
- 借鉴了一个对 Date 数据进行相关操作的 Util 类，增加了 Response 类处理 HTTP 请求。
- 最近两天都在尝试如何使用一个模板类完成 sqlSession 的获取以及commit操作，可以减少很多重复的代码，但是目前还没成功。因为表格分三个，每个表的操作又不止增删改查的基础步骤，想要统一较为困难。

### 2020.1.30 - Demo 2.2.7
- 删除了 Mapper 中的 no exist 语句，因为 Service 封装 Mapper 功能的时候已经将增删改查的基础功能组合在了一起， Mapper 应该完成其最基本的功能。
- 添加了 Mapper 中的 where 和 if 标签，提高预判性。并且在score中加入了关联 student 的 resultMap ，使用双向一对多映射，有望实现三个表的关联。
- 删除了 Mybatis 的配置文件，全部由 Spring 进行管理,删除之后 Mapper 内的 POJO 类名会报错，这是IDEA自身的问题，实际运行时可以由 Spring 扫描到，为了不显示红色还是使用了包名。
- 正在画前端页面的原型图，深入思考前端与后端的关联性，对于 Controller 层应当抛弃原本的增删改查四个 Controller ，因为前端是先选择表再选择功能，所以对应的应该有三个表的 Controller 类。

### 2020.1.31 - Demo 2.2.9
- 已知问题(√): 实际项目开发中并不会真的把用户的数据永久删除，对于每个数据库中的数据应该有一个存在属性，而增删改查都是对存在的数据进行修改的。反映到前端就是用户修改之后并不会马上生效，需要点击保存才行，并且内部人员仍旧可以查到删除的数据。
- 已知问题(√): 每条数据都应当有创建时间和修改时间的属性，如何将其交给 Spring 控制自动注入是一个问题。如果能实现这个也就不会产生创建时间和操作实际响应时间不一致的问题，而且也不需要在底层 Service 中创建时间戳并 set 给实体对象。
- 增删改查和 manager 的 Controller 已经全面更换为 Spring 控制下的学生、课程、成绩 Controller ，所有 Service 中的功能接口已经在 Controller 中实现并且测试成功。
- 至此，学生管理系统的后端内容已经基本完成，即将准备进入3.0版本。

### 目前的首要任务:
- 修复 Demo 2.x 中的各种 Bug 。
- 根据已知问题完善功能以及不足的地方。
- 先画出所需前端页面的原型图，学习 ajax 、 jquery 、 vue.js 等相关知识之后再做前端的网页。
- 抛弃第三版中不需要的内容然后存档，注意 Demo 版本和实际版本并不对应。Demo 2 应该是第二版和第三版的结合。

### 2020.2.1 - Demo 2.3.2
- 删除了增删改查的 Controller 和 Test 类，因为数据库表格的修改这些已经不再适用，并且用户界面也将由前端页面代替。
- 为学生和成绩表也添加了创建时间( utcCreate )和修改时间( utcModify )以及是否存在( isReal )的字段， isReal 在对象被创建时即赋值为 true 。。
- 删除了 ResultMap 的双向一对多映射，没有必要将成绩和学生、课程连接起来，因为成绩中已经包含学生和课程的 ID ，如果需要可以调用学生表和课程表的接口读取数据。。
- 现在的删除功能不会真的删除数据，而是把 isReal 设置为0，如果使用 save 功能就会把所有 isReal 为0的数据真正的删除(而 save 功能只有管理员才能使用，并且里面使用了特有的 select 方法)。所以学生和老师端在操作的时候都只会显示 isReal 为1的数据，只有管理员端才能查看已被删除的数据并将其真正删除。

### 2020.2.11 - Demo 2.3.5
- 已知问题(√): 项目开发中与数据库之间传输的对象应该是 map 还是 Javabean ，两者各有优缺点，但是此项目中两者都用到了，我觉得还是统一比较好。
- 已知问题:加入了 Mybatis 的二级缓存配置，但是似乎并没有什么用，二级缓存适合作用于 Redis 而不是 MySQL，因此等学了 Redis 之后再考虑完善。
- 已知问题(√): 在实际生活中学生成绩肯定是介于0-100分之间的，而 MySQL 中没有作出判断，又或许应该在 POJO 对象在构建的时候判断，又或许是在前端接收到参数的时候判断？
- 完成了前端页面的一份原型图，搭好了登录框的结构。
- 加入了 Mybatis 二级缓存的配置，采用 FIFO 规则，每30秒刷新一次，容量为512，只读。
- 更新了 Mybatis 语句，使用 set 标签替代 MySQL 中的原始 set ，再与 if 标签结合，可以判断传入的参数是否为 null 或者为空字符串等情况，处于上述情况将不会执行该属性的 update 操作。
- 限定了 utcCreate 、 utcModify 、 isReal 这三个性质的位置，现在 utcCreate 只能在 insert 中被创建， utcModify 只能在 delete 、 update 中被修改， isReal 只能在 delete 中被赋值为 false ，在 insert 中被赋值为 true 。

### 2020.2.12 - Demo 2.3.6
- 加了几个 Spring 的 core 包。
- 代码新增 lombok 插件的注释功能，现在所有的 get 和 set 都可以在编译的时候自动生成，提高的代码的简洁性。当然由于 hashcode 的比较特殊以及构造函数中 isReal 必须设置为 true ，所以没有使用 @DATA 注解。

### 2020.2.13 - Demo 2.4.0
- 已知问题(√): 更新了 Mapper 中的注释，但是 IDEA 还是提示我应该使用 Javadoc 的形式。
- 修改时区为 UTC ，世界统一时间。
- 更新了 save 功能，现在不再此功能更新修改时间了，因为更新完就要被删除。
- 优化了与数据库的传输方式，全部采用 Javabean 的形式，抛弃了 map ，虽然每次都要组装，但是安全性更高，避免了一些问题。
- 取消 Javadoc 的报错，眼不见为净。很明显是 IDEA 的问题，写了 Javadoc 还说没写。随便点开 Hashmap 的类都发现他们的 Javadoc 写的也不规范。
- 在 Controller 层中更新了对合法数据的判断，现在分数在[0,100]范围以外的数据都不能存入数据库。在后端设置判断机制可以确保安全性，在前端设置判断机制可以提升用户体验，两者都需要有。 BaseController 中还包含对其他数据的判断，暂时没必要使用。

### 2020.2.14 - Demo 3.0.0
- 学生信息管理系统正式更名为信息管理系统( Information Management System )
- 做到验证码那块不会，百度上的做法都是前后端结合运用 servlet 和 JavaScript 等技术，然而我都没学，所以又要耽误几天了。

### 2020.2.15 - Demo 3.0.1
- 优化了几个极小的问题。
- resources 文件夹中加入了 MySQL 的建表语句方便使用。

### 2020.2.16 - Demo 3.0.1
- 测试项目同步 Coding 和 GitHub 。

### 2020.2.18 - Demo 3.0.4
- 由于使用 Spring Boot 自动配置，所以删除了 log4j 的 XML 文件。
- 因为涉及到用户登录，所以要建储存用户账号密码的表，并且从底层 Mapper 到顶层逐步增加新功能。
- 添加了一个 AOP 注解接口用来判断 Controller 接口登录权限，并且在 UserAspect 类中进行了 HTTP 请求的读取，如果没有登录会自动回到登录界面，最后在每个 Controller 的方法中加入了这个注解。

### 2020.2.19 - Demo 3.0.7
- 已知问题(√): 用户登录接口测试成功，但是因为使用了 Spring MVC 的相关技术，还没有学到，所以 POSTMAN 中测试接口提示方法被禁止。
- 已知问题(√): IDEA 提示我缺少了 JetBrains 的 annotation 包，等有空看看先，好像还少了一些 dylib 库，在 Windows 中后缀是 dll，找了一下官网好像还没有 Mac 的。
- 已知问题: 还有一个问题就是，虽然可以在接受 HTTP 请求的时候判断这个接口是否需要管理员权限，但是怎么才能让用户拥有权限，换句话说就是如果用户直接跳过登录界面访问内部接口怎么避免权限问题，所以想通过优化之前写的 LoginAspect 改进 AOP 的方式来实现这一功能。
- 重构了一部分代码，现在代码更加简洁了。
- 导入了 JetBrains annotation 的包。
- 由于现在使用的时区为 UTC ，所以更改了所有 GMT 为 UTC。
- 前天 GitHub 仓库 URL 搞错了，怪不得没有同步成功，现在 Coding 和 GitHub 已经同步更新了。
- User 已经构建到了 Controller 层，凌晨了等明天再更新，先记一笔，等 User 全部 OK 就可以做简则用户登录的方法了。
- 发现之前 selectGarbage 的管理员功能没写完整，完成了 User 全部的基础功能，目前可以存在检测接口是否需要管理员权限以及登录功能，但是两个还没有关联在一起，即需要管理员权限的接口需要用户使用登录功能之后变为开启。

### 2020.2.20 - Demo 3.0.8
- 增加了 LICENCE 文件。
- 测试项目同步 Gitee 、 Coding 和 GitHub 。

### 2020.2.22 - Demo 3.0.9
- 格式化代码。

### 2020.2.23 - Demo 3.1.0
- 将四个 Service 升级了事务，采用 REQUIRED 修饰，即整体事务，一般的事物都会先使用 Select 操作，如果查询期间就出异常就不就行下面的数据库操作了，在遇到 Exception 和 Error 时程序和数据库都会 Rollback ，另外事务隔离级别设置为 RC 防止脏读。

### 2020.2.24 - Demo 3.1.5
- 已知问题(√): 目前在 yml 文件中写了相关配置，IndexController 中也加入了登录页面，但是启动程序后输入 localhost:8080/login/login 并不能进入该 jsp 文件(一度想要放弃)。
- 越来越忙，学校也快开课，更新速度会大幅下降。
- 使用 GetMapping 和 PostMapping 代替 RequestMapping ，省去 method 值。
- 更新了项目的 Web 架构，今天才知道原来 JS 和 JSP 不是用一个东西，需要学习的技术又多了。
- 更新了一下 pom ，加了注解。之前的太乱找都找不到依赖在哪儿，今天还发现 SpringBoot 的包里已经有 Spring-Core 的包了，重复导入后者会失效，项目会报错，所以删除了多余的依赖。

### 2020.6.2 - Demo 3.1.6
- 好久没有更新这个项目了，过完六一我就不是孩子了，不能随便拖更。
- 重构了一下，现在的 Entity 实体都继承自 AbstractEntity 类。
- 加了一个类型处理器 typeHandlers 想处理日期类的数据，后来发现日期已经转换好了没必要再转换。

### 2020.6.3 - Demo 3.1.9
- mapper -> dao , entity -> model .
- 新增了一些配置信息。
- 四个月，总算是解决了前后端接口的问题，现在加入了部分 Spring MVC 的内容，可以通过 Controller 返回一个视图层 jsp 界面，简单的参数传递也没有问题，接下来就是重点开发前端页面了。

### 2020.6.6 - Demo 3.2.1
- 感谢室友提供了一份登录界面的样式，登录界面的网页终于有脸见人了。

### 2020.6.10 - Demo 3.2.2
- 项目改为使用 SSM + VUE 的前后端分离的形式，废弃 jsp ，再见jsp ，不对，以后都不会见到了。
- 已知问题(√): 前后端跨域问题。

### 2020.6.17 - Demo 3.2.6
- 已知问题: 尝试在后端存储菜单信息，前端登录成功之后接受菜单数据，可是前端接受到菜单之后将数据变为了观察者(observe)，观察者数据是不能够进行枚举的，因此最后还是采用了前端直接存储菜单数据的方式，这样做不太安全，所以又添加了一个 token 令牌防止用户在不登录的情况下对接口进行调用。
- 写了一个 MyCrossFilter 类，使用 @crossorigin 注解解决了前后端跨域问题(困扰了我将近一个月，严重拖延了学习进度)。但是这只是最简单的解决方案，如果涉及到服务器项目部署而使用 nginx 肯定会遇到更多问题。
- 响应前端的需求，增加了一个恢复用户权限的功能，现在管理员可以选择关闭或者开启用户权限，包括管理员自己。
- 添加了用户搜索的功能，但是由于请求体无法传递给后端(可能是因为前端没有设置v-model对user对象的绑定)，现在只能通过 ID 查找用户，并且由于是管理员权限，所以自顶向上新增了一条管理员查找的方法。
- 优化了用户登录的接口与返回值。

### 2020.6.18 - Demo 3.2.7
- 修改了保存的操作以及查找的操作，现在只会对唯一 ID 的数据进行删除操作。
- 期末考试，停更 2 周。

### 2020.7.18 - Demo 3.3.0
- 根据前端内容改进了一些新接口，添加了用户权限的开启与锁定状态，同时修改删除操作只删除未锁定状态的数据。
- 修改课程中教师编号为字符串形式，对应教师用户的字符串用户名。
- 修复了 update 操作中的一个小 bug。

### 2020.7.20 - Demo 3.3.2
- 修复了小 bug，插入操作现在会判断是否与禁用的数据ID重复。
- 已知问题: 学习了如何使用 @Param 注解，等接口差不多写完之后重构一下 dao 层的代码。
- 为 Score 添加了一些新功能，满足前端调用的需求

### 2020.7.25 - Demo 3.3.3
- 由于学分存在小数，将 Subject 表中的学分改为了 Double
- 吐血更新数据库，手动加入了 400 多条真实有效的学生、课程和成绩数据

### 2020.7.27 - Demo 3.3.4
- 尝试部署到服务器失败，明显还是跨域问题，然后还发现另一个问题就是数据库地址只能填 localhost ，不能用 127.0.0.1 代替。
- 测试了一天半终于找到了原因，原来是因为不仅阿里云需要开放端口，宝塔面板也需要为后端应用放行端口。
- 修复了成绩数据库的一个小 bug。

### 2020.8.1 - Demo 3.3.5
- 开始整合 Shiro，数据库表可能会大改。
- 用 lombok 优化了一些代码，删除了测试阶段残留的代码。
- 更新了 javadoc

### 2020.8.2 - Demo 3.3.6
- 因为涉及到权限和角色这两个新增的表以及角色与权限、用户与角色这两个关联的表，数据库做的很大的修改，顺便加入了更多的参数。
- 已经重构完用户部分的代码，接下来先创建权限和角色相关的代码，将其使用 Shiro 关联起来并测试用户的登录和权限的设置是否可行，最后再修改其余几张表。先 commit 一下，希望最后不要 rollback

### 2020.8.3 - Demo 3.3.7
- 写了两天终于出稳定版了，现在 Shiro 基本的授权认证已经完成，只需要稍微优化一下即可。

### 2020.8.5 - Demo 3.4.0
- 遇到了很严重的跨域问题，最主要的是前端的 ajax 请求默认是不能携带 Cookie 信息的，导致 Postman 上测试接口都是正常的但是放到前后端分离的环境下就不行了。Cookie 的问题在前端做了解决，另外的跨域问题通过后端设置了一个自定义的 ShiroFilter 来添加请求头以及处理未授权时的异常报错。一言难尽。
- 我是真的肝，几天之内就整合了 Shiro 和 Vue 和 SSM，期间遇到了很多关于跨域的问题，好几天晚上都没怎么睡，不过好在问题都解决了。
- 重构了整个项目的代码，重构了数据库表，加入了比较正统的权限五张表，之前的 User 也算在其中，对于所有 POJO 的抽象基类都加上了新的字段，将 isReal 改为 valid。
- 根据同学测试的反馈将学号改成了字符串类型，学生信息不再存储年龄，而是前端接收到出生日期之后及时计算出年龄然后显示。
- 重写了 DefaultWebSessionManage，现在会发送 Cookie 和 Session 双重认证。
- 完善了 Shiro 的配置，在 securityManager 中加入了自定义的安全认证，在 shiroFilter 中加入了自定义的过滤器。
- 添加了 GlobalExceptionHandle 全局控制 Shiro 的异常，返回给前端对应信息。
- AuthorizingRealm 中将用户对象替换为用户名解决类型转换问题
- 已知问题: 其实也不算问题，而是过几天需要做的事。首先将权限和用户匹配起来，比如前端添加了一个管理员用户，立刻为其分配管理员权限。后期再考虑同级别的用户不同的自定义权限。还有一个就是由于重构过了，所有的 Controller 都只包含了最基本的增删改查功能，将原先写的一些特殊功能全部删除了，接下来添加自定义功能。

### 2020.8.6 - Demo 3.4.2
- 更新了挺多东西的，主要把管理员、学生、教师的权限都解决了，同时在创建用户时也调用了角色的 Mapper 将用户与角色绑定。我之前是把角色放在了用户的属性中，这样做确实省了不少事，现在分开导致前端获取数据需要发送不同的请求，网络开销是成倍增长的。
- 另外，希望什么时候有空能把项目从头到尾再重构一遍，主要是解决 mapper.xml 的报错（其实没有错，是IDEA nt 了，但是看着不舒服）。其次就是使用注解优雅的操作数据库以及接口。等全部基本完工之后再把之前删了又加，加了又删的 javadoc 给加上。

### 2020.8.7 - Demo 3.4.4
- 调整了 Shiro 配置中登出操作的顺序，排在匿名访问之后，这样不会导致退出的时候清空 session 导致后端给前端发送的数据有误。
- 为配合前端获取综合数据和用户权限、角色等信息而添加了新的接口。

### 2020.8.8 - Demo 3.4.6
- 角色和权限方面又根据前端需求加了很多新接口。
- 项目第二阶段基本完成，过几天开始重构后端代码。

### 2020.8.13 - Demo 3.4.8
- 添加了盐值加密
- 添加了用户锁定

### 2020.8.15 - Demo 3.4.9
- 最新版本被 rollback 了。导致错误的原因可能是删除了拦截器的关系，现在还是采用自定义拦截器，但是改用继承 user 类的默认拦截器。

### 2020.8.16 - Demo 3.5.0
- 发现一个 bug 就是 Mybatis 中 set 标签内的 if 在判断条件时会出错，加入参数本身为布尔值的 false 或者数字 0 ，就会直接判定为错误。以后尽量还是不要用 0 这个数字吧。

### 2020.8.19 - Demo 3.5.2
- 今天读阿里巴巴 Java 开发手册时发现里面讲到 Service 层暴露在外面的只能是接口并且不能有任何修饰符，我突然想起来学长的项目也是这么做的，而我没有用到接口，一开始也想不明白为什么 Controller 层注入接口却能使用实现类中代码，后来知道了这个 Spring 管理的，于是将 Service 层全部重构了顺便加上了 Javadoc。
- 专门写了个跨域处理工具类，现在不需要在测试和部署的时候频繁修改配置了。

### 2020.8.20 - Demo 3.5.4
- 使用 Swagger 和 Swagger Bootstrap UI 快速生成了 API 文档，接下来要使用注解优化 API。
- 重构了一下 POJO 对象

### 2020.8.23 - Demo 3.5.5
- 修复了一个 bug，之前重构 Service 层的时候直接用了自动重命名，导致 Controller 层注入的还是 Service 的实现类，应当注入接口类。

### 2020.9.1 - Demo 3.5.6
- 完成了 Swagger 的注解，对于 Integer 类型的参数报错采用日志忽略
