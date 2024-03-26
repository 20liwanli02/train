1.新增的依赖要重新启动项目，热部署不生效

2.git安装后重启电脑，否则环境变量不生效

3.公共的依赖放在common，依赖的版本放置在trian的pom.xml

4.移动依赖发现找不到导入的模块依赖，有可能是新建的模块没有安装进入仓库（父项目clean，install把子模块安装进入仓库）

5.application.properties在member模块放在了resources下，则common模块不能直接放在resources下，否则会冲突，所以可以添加config文件夹，默认也可以读到

6.