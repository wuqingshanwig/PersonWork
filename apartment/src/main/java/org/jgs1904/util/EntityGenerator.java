package org.jgs1904.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 实体类生成工具1.0
 * 
 * @author 方俊杰
 * @date 2018年11月9日
 */
public class EntityGenerator {

	private static StringBuilder sb = new StringBuilder();
	private static String entityName = "";
	private static String defaultPackagePath = null;
	private static Connection defaultConn = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;

	static {
		InputStream is = EntityGenerator.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pro = new Properties();
		try {
			pro.load(is);
			defaultPackagePath = pro.getProperty("defaultPackagePath");
			if (defaultPackagePath == null) {
				defaultPackagePath = "com.fang.entity";
			}
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			Class.forName(driver);
			defaultConn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建实体类
	 * 
	 * @param conn         数据库连接对象
	 * @param table        表名
	 * @param packagePath  实体类包名
	 * @param constructors 是否创建构造方法
	 * @param getSet       是否创建get方法和set方法
	 */
	public static void createEntity(Connection conn, String table, String packagePath, boolean constructors,
			boolean getSet) {
		entityName = table.substring(0, 1).toUpperCase() + table.substring(1, table.length()).toLowerCase();
		createCode(conn, table, packagePath, constructors, getSet);
		System.out.println(sb);
		createFile(packagePath, table);
		System.out.println("实体类生成完毕！");
	}

	public static void createEntity(Connection conn, String table, String packagePath) {
		createEntity(conn, table, packagePath, true, true);
	}

	public static void createEntity(Connection conn, String table) {
		createEntity(conn, table, defaultPackagePath);
	}

	public static void createEntity(String table, String packagePath) {
		createEntity(defaultConn, table, packagePath);
	}

	public static void createEntity(String table) {
		createEntity(defaultConn, table);
	}

	/**
	 * 创建Java文件
	 * 
	 * @param packagePath
	 */
	private static void createFile(String packagePath, String table) {
		String filePath = "src/" + packagePath.replace(".", "/") + "/" + entityName + ".java";
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拼接Java代码
	 * 
	 * @param conn
	 * @param table
	 * @param packagePath
	 */
	private static void createCode(Connection conn, String table, String packagePath, boolean constructors,
			boolean getSet) {
		StringBuilder constructorValue = new StringBuilder();
		StringBuilder constructorContent = new StringBuilder();
		StringBuilder getSetCode = new StringBuilder();

		sb.append("public class " + entityName + " {\n");

		try {
			ResultSet rs = conn.createStatement().executeQuery("select * from " + table);
			ResultSetMetaData rsmd;
			rsmd = rs.getMetaData();
			for (int index = 1; index <= rsmd.getColumnCount(); index++) {
				String columnName = rsmd.getColumnName(index);
				String columnType = rsmd.getColumnTypeName(index);

				columnType = changeType(columnType);

				// 拼接实体属性
				sb.append("\tprivate " + columnType + " " + columnName + ";\n");

				// 拼接构造方法参数列表
				constructorValue.append(columnType + " " + columnName + ", ");

				// 拼接构造方法方法体
				constructorContent.append("\t\tthis." + columnName + " = " + columnName + ";\n");

				// 拼接get方法和set方法
				getSetCode.append("\n\n\tpublic " + columnType + " get" + columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1, columnName.length()) + "() {\n" + "\t\treturn " + columnName
						+ ";\n\t}" + "\n\n\tpublic void set" + columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1, columnName.length()) + "(" + columnType + " " + columnName + ") {\n"
						+ "\t\tthis." + columnName + " = " + columnName + ";\n\t}");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 整合构造方法
		if (constructors == true) {
			sb.append("\n\tpublic " + entityName + " () {\n\n\t}");
			sb.append("\n\n\tpublic " + entityName + " (" + constructorValue.substring(0, constructorValue.length() - 2)
					+ ") {\n");
			sb.append(constructorContent + "\t}");
		}

		// 整合get方法和set方法
		if (getSet == true) {
			sb.append(getSetCode);
		}

		// 补充包路径
		sb.insert(0, "package " + packagePath + ";\n\n");
		sb.append("\n}");
	}

	/**
	 * 类型转换
	 * 
	 * @param columnType
	 * @return
	 */
	private static String changeType(String columnType) {
		switch (columnType) {
		case "TINYINT":
			return "Byte";
		case "SMALLINT":
			return "Short";
		case "INT":
			return "Integer";
		case "BIGINT":
			return "Long";

		case "FLOAT":
		case "DOUBLE":
		case "DECIMAL":
			return "Double";

		case "TEXT":
		case "VARCHAR":
		case "CHAR":
			return "String";

		case "DATE":
			Autoimport("Date");
			return "Date";
		case "DATETIME":
			Autoimport("Date");
			return "Date";
		case "TIME":
			return "java.sql.Time";
		case "TIMESTAMP":
			return "java.sql.Timestamp";
			
		case "BIT":
			return "Short";

		default:
			return columnType;
		}
	}

	/**
	 * 自动导包
	 * 
	 * @param className
	 */
	private static void Autoimport(String className) {
		sb.insert(0, "import java.util." + className + ";\n\n");
	}

}