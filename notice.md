1.新增的依赖要重新启动项目，热部署不生效

2.git安装后重启电脑，否则环境变量不生效

3.公共的依赖放在common，依赖的版本放置在train的pom.xml

4.移动依赖发现找不到导入的模块依赖，有可能是新建的模块没有安装进入仓库（父项目clean，install把子模块安装进入仓库）

5.application.properties在member模块放在了resources下，则common模块不能直接放在resources下，否则会冲突，所以可以添加config文件夹，默认也可以读到

6.当一个功能比较复杂可以考虑定位一个模块，如网关模块

7.网关模块不需要前缀

8.yml文件转换（toyaml.com/index.html）

9.http测试文件，每个测试地址都要用###隔开

10.路由转发不要把spring写成sprign了

11.网关模块配置外网IP，其他模块配置内网IP，防止用户绕过gateway

12.连接数据库出错
***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).

可能为新建连接文件，IDEA没有及时更新进仓库（clean，install）

13.开发规定，生成器生成的4个文件都不能手动修改（只生成单表的增删改查）

14.node.js安装新的高版本，会在原来的版本上升级

15.vue-cli安装失败，看看是不是已经安装了

16.前端请求后端时，参数的格式不对，报错如下
Object
config:{transitional: {…}, adapter: Array(2), transformRequest: Array(1), transformResponse: Array(1), timeout: 0, …}
data:{success: false, message: '手机号不能为空！', content: null}
headers:AxiosHeaders {content-type: 'application/json'}
request:XMLHttpRequest {onreadystatechange: null, readyState: 4, timeout: 0, withCredentials: false, upload: XMLHttpRequestUpload, …}
status:200
statusText:"OK"
[[Prototype]]:Object

17.respond.data 到了 CommonResp 这一层

18.像layout布局一样，官方贴心的把布局的每一块的样式写在了每一块代码里面

19.登录注册二合一时没有填验证码显示的报错信息

20.热部署失败
Unable to start LiveReload server
端口冲突？
修改端口：spring.devtools.livereload.port=35731

21.为什么返回时才强制跳转登陆页面？
在后端才进行登录校验，返回时跳转更加便利

22.为什么前端也要设置登录校验？
直接在浏览器上输入地址，在未登录的情况下可以跳转只是无法拿到数据，明显不合理

23.页面401
参数格式问题
token失效

24.拦截器的优先级高于日志

25.加载二级路由时，一级路由已经加载完毕，路径是什么就加载什么

26.现在路径是什么，<route-view/>就是什么

27.为什么访问某个路径就会显示对应的某个页面
http://localhost:9000/welcome
1.加载了两个组件：main.vue和welcome.vue
2.app.vue和main.vue中各有一个位置占位符
3.由25、26得
从头看，进入app.vue此时路劲为http://localhost:9000/，加载了app的子组件-》main.vue，app的占位符就是此组件；
然后，进入main.vue组件，路径为http://localhost:9000/welcome，，加载了main的子组件welcome.vue组件，main的占位符就是welcome组件。

28.响应式变量

29.echarts数据报表

30.post请求数据，后面参数用的是json格式，所以接收时要定义json格式接收，
但get请求参数增加在了url后面，可以不用json格式接收

31.PageResp<PassengerQueryResp>
泛型：数据被封装成了一个PassengerQueryResp的对象
返回类型：但是要返回PageResp类型，没有记得封装

32.使用ref，取值赋值都加上.value

33.main函数执行乱码

34.依赖子模块显示jar不存在

35.惊奇的发现火车车次的查询前端加了session，导致下拉框显示的时session是缓存的值，不会把最新添加的车次也加入下拉框，关闭浏览器重新进入网页清湖缓存才能看见添加的车次

36.controller和service可以交叉调用的

37.在哪里注入就在调用哪里的方法

38.类，一个封装用的容器；对象一个具有确定性的类

39.IDEA只有main函数执行调用数据库科才会出现乱码，猜测数据库用来utf8mb4

40.数据库记录重复删了再2插入，不重复删了再插入

41.数据库删除座位，票数竟然不变，重新生成才会变化

42.nacos的读bootstrap的依赖时就近原则的读！！！