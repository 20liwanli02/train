1.新增的依赖要重新启动项目，热部署不生效

2.git安装后重启电脑，否则环境变量不生效

3.公共的依赖放在common，依赖的版本放置在trian的pom.xml

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
