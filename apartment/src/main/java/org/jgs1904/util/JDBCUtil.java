package org.jgs1904.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * jdbc操作工具类2.0
 * @author junki
 * @date 2020年1月15日
 */
public final class JDBCUtil {

	// 定义数据库连接相关参数
	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";
	
	// 创建ThreadLocal类的对象，用于维护每个线程的数据库连接
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	private JDBCUtil() {};
	
	static {
		// 加载配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		driver = bundle.getString("driver");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		
		// 注册驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 获取数据库连接
	public static Connection getConnection() {
		if (threadLocal.get() == null) {
			try {
				threadLocal.set(DriverManager.getConnection(url, user, password));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return threadLocal.get();
	}
	
	// 关闭资源
	public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
				// 销毁threadLocal中的连接对象
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
}
