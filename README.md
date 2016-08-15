一元夺宝的服务器端的前台夺宝和后台管理,纯属模仿.

本项目使用Intellij IDEA开发,默认编码是UTF-8.

/src/hibernate.cfg.xml 这个文件只是用来配合/src/junit/ExportDatabaseSchema.java来生成数据库表的,无其他作用了.
/src/junit/ExportDatabaseSchema.java这个文件是用来生成数据库表的,先在mysql里create database duobao; use duobao;
然后运行这个文件生成数据库表, 为true时注意再次生成会擦除所有数据.
商品管理的页面: http://localhost:8080/goods-navi
夺宝界面 http://localhost:8080/
如果你想先看看效果,我已经把它放在我的服务器里了. http://duobao.fozoto.com/
