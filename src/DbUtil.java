import java.sql.*;

public class DbUtil {
    //连接方法
    public static Connection getConnection() {
        Connection conn = null;
        //数据库初始化驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/local?characterEncoding=UTF-8","root","kevinjzk");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //三个关闭方法
    public static void close (PreparedStatement pstmt){
        //避免出现空指针异常
        if (pstmt != null) {
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

    //增加课程信息表
    public static void addSubject(Subject sub) {
        String sql = "insert into subject values(?,?,?)";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sub.ID); //给占位符赋值
            pstmt.setString(2, sub.name); //给占位符赋值
            pstmt.setInt(3, sub.GP); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //增加学生信息表
    public static void addStudent(Student stu) {
        String sql = "insert into student values(?,?,?,?,?)";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stu.getID()); //给占位符赋值
            pstmt.setString(2, stu.getName()); //给占位符赋值
            pstmt.setInt(3, stu.getSubjectScore("语文")); //给占位符赋值
            pstmt.setInt(4, stu.getSubjectScore("数学")); //给占位符赋值
            pstmt.setInt(5, stu.getSubjectScore("英语")); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //修改学生成绩信息表
    public static void update(Student stu, String name, Integer score) {
        String sql = "update student set " + name + " = ? where id = ?";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score); //给占位符赋值
            pstmt.setInt(2, stu.getID()); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //删除学生信息表
    public static void delete(Student stu) {
        String sql = "delete from student where id = ?";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stu.getID()); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //查询信息
    public static void findStudentList(Integer ID) {
        String sql = "select * from student where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        //创建一个集合对象用来存放查询到的数据
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ID); //给占位符赋值
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("姓名");
                int Chinese = rs.getInt("语文");
                int Maths = rs.getInt("数学");
                int English = rs.getInt("英语");
                System.out.println("---ID: " + ID);
                System.out.println("---姓名: " + name);
                System.out.println("---语文: " + Chinese);
                System.out.println("---数学: " + Maths);
                System.out.println("---英语: " + English);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            DbUtil.close(pstmt);
            DbUtil.close(conn);        //必须关闭
        }
    }
}
