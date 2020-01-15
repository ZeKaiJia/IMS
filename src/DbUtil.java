import java.sql.*;
public class DbUtil {
    //连接方法
    public static Connection getConnection() {
        Connection conn = null;
        //数据库初始化驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/local?characterEncoding=UTF-8","root","kevinjzk");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //三个关闭方法
    public static void close (PreparedStatement pstmt){
        if (pstmt != null) {                        //避免出现空指针异常
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close (Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
    public static void close (ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    //增
    public static void addCourse(Integer ID, String name, Integer GP){
        String sql = "insert into subject values(?,?,?)";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = DbUtil.getConnection();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, ID); //给占位符赋值
            pstmt.setString(2, name); //给占位符赋值
            pstmt.setInt(3, GP); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }
}
