package com.lzw.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.JOptionPane;

import com.lzw.Item;
import com.lzw.dao.model.*;

public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_database;";
	protected static String dbUser = "sa";
	protected static String dbPwd = "";
	protected static String second = null;
	public static Connection conn = null;
	static {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"请将SQL Server 2012的JDBC驱动包复制到lib文件夹中。");
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Dao() {
	}
	
	public static int restoreOrBackup(String sql) throws Exception {
		int rs = 0;
		if (conn != null) {
			conn.close();
		}
		// 连接到系统数据库
		conn = DriverManager.getConnection(
				"jdbc:sqlserver://127.0.0.1:1433/master", dbUser,
				dbPwd);
		Statement stmt = conn.createStatement();
		// 定义单用户连接模式的SQL语句
		String single = "alter database db_database set single_user"
				+ " with rollback immediate " + sql;
		// 执行数据库备份或恢复的SQL语句
		rs = stmt.executeUpdate(single);
		stmt.close();
		conn.close(); // 关闭数据库连接
		// 恢复原有数据库连接
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		return rs;
	}
	
	// 读取所有客户信息
	public static List getKhInfos() {
		List list = findForList("select id,khname from tb_khinfo");
		return list;
	}
	
	// 读取所有供应商信息
	public static List getGysInfos() {
		List list = findForList("select id,name from tb_gysinfo");
		return list;
	}
	
	// 读取客户信息
	public static TbKhinfo getKhInfo(Item item) {
		String where = "khname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		TbKhinfo info = new TbKhinfo();
		ResultSet set = findForResultSet("select * from tb_khinfo where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setKhname(set.getString("khname").trim());
				info.setJian(set.getString("jian").trim());
				info.setAddress(set.getString("address").trim());
				info.setBianma(set.getString("bianma").trim());
				info.setFax(set.getString("fax").trim());
				info.setHao(set.getString("hao").trim());
				info.setLian(set.getString("lian").trim());
				info.setLtel(set.getString("ltel").trim());
				info.setMail(set.getString("mail").trim());
				info.setTel(set.getString("tel").trim());
				info.setXinhang(set.getString("xinhang").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	// 读取指定供应商信息
	public static TbGysinfo getGysInfo(Item item) {
		String where = "name='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		TbGysinfo info = new TbGysinfo();
		ResultSet set = findForResultSet("select * from tb_gysinfo where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setAddress(set.getString("address").trim());
				info.setBianma(set.getString("bianma").trim());
				info.setFax(set.getString("fax").trim());
				info.setJc(set.getString("jc").trim());
				info.setLian(set.getString("lian").trim());
				info.setLtel(set.getString("ltel").trim());
				info.setMail(set.getString("mail").trim());
				info.setName(set.getString("name").trim());
				info.setTel(set.getString("tel").trim());
				info.setYh(set.getString("yh").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	// 读取经手人
	public static TbJsr getJsr(String name, String password) {
		TbJsr user = new TbJsr();
		ResultSet rs = findForResultSet("select * from tb_jsr where name='"
				+ name + "'");
		try {
			if (rs.next()) {
				user.setSex(name);
				user.setAge(rs.getString("pass"));
				if (user.getAge().equals(password)) {
					user.setName(rs.getString("name"));
					user.setTel(rs.getString("quan"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// 执行指定查询
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}
	
	// 执行删除
	public static int delete(String sql) {
		return update(sql);
	}
	
	// 添加客户信息的方法
	public static boolean addKeHu(TbKhinfo khinfo) {
		if (khinfo == null)
			return false;
		return insert("insert tb_khinfo values('" + khinfo.getId() + "','"
				+ khinfo.getKhname() + "','" + khinfo.getJian() + "','"
				+ khinfo.getAddress() + "','" + khinfo.getBianma() + "','"
				+ khinfo.getTel() + "','" + khinfo.getFax() + "','"
				+ khinfo.getLian() + "','" + khinfo.getLtel() + "','"
				+ khinfo.getMail() + "','" + khinfo.getXinhang() + "','"
				+ khinfo.getHao() + "')");
	}
	
	// 修改客户信息的方法
	public static int updateKeHu(TbKhinfo khinfo) {
		return update("update tb_khinfo set jian='" + khinfo.getJian()
				+ "',address='" + khinfo.getAddress() + "',bianma='"
				+ khinfo.getBianma() + "',tel='" + khinfo.getTel()
				+ "',fax='" + khinfo.getFax() + "',lian='"
				+ khinfo.getLian() + "',ltel='" + khinfo.getLtel()
				+ "',mail='" + khinfo.getMail() + "',xinhang='"
				+ khinfo.getXinhang() + "',hao='" + khinfo.getHao()
				+ "' where id='" + khinfo.getId() + "'");
	}
	
	// 修改库存的方法
	public static int updateKucunDj(TbKucun kcInfo) {
		return update("update tb_kucun set dj=" + kcInfo.getDj()
				+ " where id='" + kcInfo.getId() + "'");
	}
	
	// 修改供应商信息的方法
	public static int updateGys(TbGysinfo gysInfo) {
		return update("update tb_gysinfo set jc='" + gysInfo.getJc()
				+ "',address='" + gysInfo.getAddress() + "',bianma='"
				+ gysInfo.getBianma() + "',tel='" + gysInfo.getTel()
				+ "',fax='" + gysInfo.getFax() + "',lian='"
				+ gysInfo.getLian() + "',ltel='" + gysInfo.getLtel()
				+ "',mail='" + gysInfo.getMail() + "',yh='"
				+ gysInfo.getYh() + "' where id='" + gysInfo.getId() + "'");
	}
	
	// 添加供应商信息的方法
	public static boolean addGys(TbGysinfo gysInfo) {
		if (gysInfo == null)
			return false;
		return insert("insert tb_gysinfo values('" + gysInfo.getId()
				+ "','" + gysInfo.getName() + "','" + gysInfo.getJc()
				+ "','" + gysInfo.getAddress() + "','"
				+ gysInfo.getBianma() + "','" + gysInfo.getTel() + "','"
				+ gysInfo.getFax() + "','" + gysInfo.getLian() + "','"
				+ gysInfo.getLtel() + "','" + gysInfo.getMail() + "','"
				+ gysInfo.getYh() + "')");
	}
	
	// 添加商品
	public static boolean addSp(TbSpinfo spInfo) {
		if (spInfo == null)
			return false;
		return insert("insert tb_spinfo values('" + spInfo.getId() + "','"
				+ spInfo.getSpname() + "','" + spInfo.getJc() + "','"
				+ spInfo.getCd() + "','" + spInfo.getDw() + "','"
				+ spInfo.getGg() + "','" + spInfo.getBz() + "','"
				+ spInfo.getPh() + "','" + spInfo.getPzwh() + "','"
				+ spInfo.getMemo() + "','" + spInfo.getGysname() + "')");
	}
	
	// 更新商品
	public static int updateSp(TbSpinfo spInfo) {
		return update("update tb_spinfo set jc='" + spInfo.getJc()
				+ "',cd='" + spInfo.getCd() + "',dw='" + spInfo.getDw()
				+ "',gg='" + spInfo.getGg() + "',bz='" + spInfo.getBz()
				+ "',ph='" + spInfo.getPh() + "',pzwh='"
				+ spInfo.getPzwh() + "',memo='" + spInfo.getMemo()
				+ "',gysname='" + spInfo.getGysname() + "' where id='"
				+ spInfo.getId() + "'");
	}
	
	// 读取商品信息
	public static TbSpinfo getSpInfo(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_spinfo where "
				+ where);
		TbSpinfo spInfo = new TbSpinfo();
		try {
			if (rs.next()) {
				spInfo.setId(rs.getString("id").trim());
				spInfo.setBz(rs.getString("bz").trim());
				spInfo.setCd(rs.getString("cd").trim());
				spInfo.setDw(rs.getString("dw").trim());
				spInfo.setGg(rs.getString("gg").trim());
				spInfo.setGysname(rs.getString("gysname").trim());
				spInfo.setJc(rs.getString("jc").trim());
				spInfo.setMemo(rs.getString("memo").trim());
				spInfo.setPh(rs.getString("ph").trim());
				spInfo.setPzwh(rs.getString("pzwh").trim());
				spInfo.setSpname(rs.getString("spname").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spInfo;
	}
	
	// 获取所有商品信息
	public static List getSpInfos() {
		List list = findForList("select * from tb_spinfo");
		return list;
	}
	
	// 获取库存商品信息
	public static TbKucun getKucun(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_kucun where "
				+ where);
		TbKucun kucun = new TbKucun();
		try {
			if (rs.next()) {
				kucun.setId(rs.getString("id"));
				kucun.setSpname(rs.getString("spname"));
				kucun.setJc(rs.getString("jc"));
				kucun.setBz(rs.getString("bz"));
				kucun.setCd(rs.getString("cd"));
				kucun.setDj(rs.getDouble("dj"));
				kucun.setDw(rs.getString("dw"));
				kucun.setGg(rs.getString("gg"));
				kucun.setKcsl(rs.getInt("kcsl"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kucun;
	}
	
	// 获取入库单的最大ID，即最大入库票号
	public static String getRuKuMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_ruku_main", "RK", "rkid");
	}
	
	// 在事务中添加入库信息
	public static boolean insertRukuInfo(TbRukuMain ruMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库主表记录
			insert("insert into tb_ruku_main values('" + ruMain.getRkId()
					+ "','" + ruMain.getPzs() + "'," + ruMain.getJe()
					+ ",'" + ruMain.getYsjl() + "','"
					+ ruMain.getGysname() + "','" + ruMain.getRkdate()
					+ "','" + ruMain.getCzy() + "','" + ruMain.getJsr()
					+ "','" + ruMain.getJsfs() + "')");
			Set<TbRukuDetail> rkDetails = ruMain.getTabRukuDetails();
			for (Iterator<TbRukuDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbRukuDetail details = iter.next();
				// 添加入库详细表记录
				insert("insert into tb_ruku_detail values('"
						+ ruMain.getRkId() + "','"
						+ details.getTabSpinfo() + "'," + details.getDj()
						+ "," + details.getSl() + ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getTabSpinfo());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() == null || kucun.getId().isEmpty()) {
						insert("insert into tb_kucun values('"
								+ spInfo.getId() + "','"
								+ spInfo.getSpname() + "','"
								+ spInfo.getJc() + "','" + spInfo.getCd()
								+ "','" + spInfo.getGg() + "','"
								+ spInfo.getBz() + "','" + spInfo.getDw()
								+ "'," + details.getDj() + ","
								+ details.getSl() + ")");
					} else {
						int sl = kucun.getKcsl() + details.getSl();
						update("update tb_kucun set kcsl=" + sl + ",dj="
								+ details.getDj() + " where id='"
								+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 获取退货最大ID
	public static String getRkthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_rkth_main", "RT", "rkthId");
	}
	
	// 在事务中添加入库退货信息
	public static boolean insertRkthInfo(TbRkthMain rkthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库退货主表记录
			insert("insert into tb_rkth_main values('"
					+ rkthMain.getRkthId() + "','" + rkthMain.getPzs()
					+ "'," + rkthMain.getJe() + ",'" + rkthMain.getYsjl()
					+ "','" + rkthMain.getGysname() + "','"
					+ rkthMain.getRtdate() + "','" + rkthMain.getCzy()
					+ "','" + rkthMain.getJsr() + "','"
					+ rkthMain.getJsfs() + "')");
			Set<TbRkthDetail> rkDetails = rkthMain.getTbRkthDetails();
			for (Iterator<TbRkthDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbRkthDetail details = iter.next();
				// 添加入库详细表记录
				insert("insert into tb_rkth_detail values('"
						+ rkthMain.getRkthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl()
						+ ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_kucun set kcsl=" + sl
								+ " where id='" + kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 获取销售主表最大ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_sell_main", "XS", "sellID");
	}
	
	// 在事务中添加销售信息
	public static boolean insertSellInfo(TbSellMain sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售主表记录
			insert("insert into tb_sell_main values('"
					+ sellMain.getSellId() + "','" + sellMain.getPzs()
					+ "'," + sellMain.getJe() + ",'" + sellMain.getYsjl()
					+ "','" + sellMain.getKhname() + "','"
					+ sellMain.getXsdate() + "','" + sellMain.getCzy()
					+ "','" + sellMain.getJsr() + "','"
					+ sellMain.getJsfs() + "')");
			Set<TbSellDetail> rkDetails = sellMain.getTbSellDetails();
			for (Iterator<TbSellDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbSellDetail details = iter.next();
				// 添加销售详细表记录
				insert("insert into tb_sell_detail values('"
						+ sellMain.getSellId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl()
						+ ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_kucun set kcsl=" + sl
								+ " where id='" + kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 获取更类主表最大ID
	private static String getMainTypeTableMaxId(Date date, String table,
			String idChar, String idName) {
		String dateStr = date.toString().replace("-", "");
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table
				+ " where " + idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baseId = baseId == null ? "000" : baseId
				.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}
	
	public static String getXsthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_xsth_main", "XT", "xsthID");
	}
	
	public static List getKucunInfos() {
		List list = findForList("select id,spname,dj,kcsl from tb_kucun");
		return list;
	}
	
	// 在事务中添加销售退货信息
	public static boolean insertXsthInfo(TbXsthMain xsthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售退货主表记录
			insert("insert into tb_xsth_main values('"
					+ xsthMain.getXsthId() + "','" + xsthMain.getPzs()
					+ "'," + xsthMain.getJe() + ",'" + xsthMain.getYsjl()
					+ "','" + xsthMain.getKhname() + "','"
					+ xsthMain.getThdate() + "','" + xsthMain.getCzy()
					+ "','" + xsthMain.getJsr() + "','"
					+ xsthMain.getJsfs() + "')");
			Set<TbXsthDetail> xsthDetails = xsthMain.getTbXsthDetails();
			for (Iterator<TbXsthDetail> iter = xsthDetails.iterator(); iter
					.hasNext();) {
				TbXsthDetail details = iter.next();
				// 添加销售退货详细表记录
				insert("insert into tb_xsth_detail values('"
						+ xsthMain.getXsthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl()
						+ ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() + details.getSl();
						update("update tb_kucun set kcsl=" + sl
								+ " where id='" + kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// 添加用户
	public static int addJsr(TbJsr jsr) {
		String sql = "insert tb_jsr values('" + jsr.getName() + "','"
				+ jsr.getSex() + "','" + jsr.getAge() + "','"
				+ jsr.getTel() + "',1)";
		System.out.println(sql);
		return update(sql);
	}
	
	public static List getJsrs() {
		List list = findForList("select * from tb_jsr where enable=1");
		return list;
	}
	
	// 修改用户方法
	public static int modifyPassword(String oldPass, String pass) {
		return update("update tb_userlist set pass='" + pass
				+ "' where pass='" + oldPass + "'");
	}
	
	// 获取用户对象的方法
	public static TbJsr getUser(Item item) {
		String where = "username='" + item.getName() + "'";
		if (item.getId() != null)
			where = "name='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_userlist where "
				+ where);
		TbJsr user = new TbJsr();
		try {
			if (rs.next()) {
				user.setName(rs.getString("name").trim());
				user.setSex(rs.getString("username").trim());
				user.setAge(rs.getString("pass").trim());
				user.setTel(rs.getString("quan").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean checkLogin(String userStr, String passStr)
			throws SQLException {
		ResultSet rs = findForResultSet("select * from tb_userlist where name='"
				+ userStr + "' and pass='" + passStr + "'");
		if (rs == null)
			return false;
		return rs.next();
	}
}