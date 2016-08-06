package junit;

/**
 * 未整合Spring4的Hibernate4
 * 用于生成数据库表结构：<br>
 * 可作为Hibernate的一个工具类，<br>
 * 将读取Hibernate配置文件，将对象模型生成关系模型（即生成数据库表结构），<br>
 * 要求：生成数据表结构之前要求已经存在数据库。
 * 		在src目录下有hibernate.cfg.xml文件
 * 特别注意：这个工具类会改变数据库数据表，数据也将被删除！
 * @author qingyan
 * 2015-12-06 14:44:14
 */
public class ExportDatabaseSchema {
	public static void main(String[] args) {
		/*
		 * org.hibernate.cfg.Configuration类的作用：
		 * 用于读取Hibernate配置文件(hibernate.cfg.xml或hiberante.properties)的，加载配置信息。
		 * 而new Configuration()默认是读取hibernate.properties的，
		 * 所以使用new Configuration().configure();来读取hibernate.cfg.xml配置文件。
		 */
		// 方式一：读取hibernate.cfg.xml
		org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration().configure();
		// 方式二：读取hibernate.properties
//		org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
		
		/*
		 * org.hibernate.tool.hbm2ddl.SchemaExport工具类：
		 * 需要传入Configuration参数，此工具类可以将类导出生成数据库表
		 */
		org.hibernate.tool.hbm2ddl.SchemaExport export = new org.hibernate.tool.hbm2ddl.SchemaExport(cfg);
		
		/*
		 * 开始导出：
		 * 第一个参数：script，是否打印DDL信息，
		 * 第二个参数：export，是否导出到数据库中生成表(注意：如果表已经存在，也会重新生成表，表中的原有数据就会被清除)
		 */
		export.create(true, false);
	}
}
