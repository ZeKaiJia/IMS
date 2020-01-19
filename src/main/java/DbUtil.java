import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    private static Connection conn = null;
    final private static String user = "root";
    final private static String password = "kevinjzk";
    //连接方法
    public static Connection getConnection() {
        Connection conn = null;
        //数据库初始化驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/local?characterEncoding=UTF-8&useSSL=false",user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //初始化获取数据
    public static void initialMySQL() {
        List<Subject> subjects = getSubjectData();
        subjects.forEach(Manager::addAPISub);
        List<Student> students = getStuData();
        students.forEach(Manager::addAPI);
    }

    //获取成绩信息
    static List<Subject> getSubjectData() {
        List<Subject> subjects = new ArrayList<>();
        String sqlsub = "select * from subject";
        conn = DbUtil.getConnection();
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        try {
            pst2 = conn.prepareStatement(sqlsub);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {
                int ID = rs2.getInt("ID");
                String name = rs2.getString("name");
                int GP = rs2.getInt("GP");
                Subject sub = new Subject(ID, name, GP);
                subjects.add(sub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return subjects;
        }finally {
            close(pst2);
            close(rs2);
        }
        return subjects;
    }


    //获取学生信息
    private static List<Student> getStuData() {
        List<Student> students = new ArrayList<>();
        String sqlstu = "select * from student";
        conn = DbUtil.getConnection();
        PreparedStatement pst1 = null;
        ResultSet rs1 = null;
        List<String> subjectName = new ArrayList<>();
        List<Integer> subjectScore = new ArrayList<>();
        try {
            pst1 = conn.prepareStatement(sqlstu);
            rs1 = pst1.executeQuery();
            while (rs1.next()) {
                subjectName.clear();
                subjectScore.clear();
                int ID = rs1.getInt("ID");
                String name = rs1.getString("姓名");
                for (int i=0; i<Manager.getSubjectCage().size(); i++) {
                    subjectName.add(getSubjectData().get(i).name);
                    subjectScore.add(rs1.getInt(subjectName.get(i)));
                }
                Student stu = new Student(ID,name);
                for (int i=0; i<Manager.getSubjectCage().size(); i++) {
                    stu.setSubject(subjectName.get(i),subjectScore.get(i));
                }
                students.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return students;
        }finally {
            close(pst1);
            close(rs1);
        }
        return students;
    }

    //三个关闭方法
    public static void close (PreparedStatement pstmt) {
        //避免出现空指针异常
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close (Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
    public static void close (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    //增加教学科目表
    public static void addSubject(Subject sub) {
        String sql = "insert into subject values(?,?,?)";
        String sql_2 = "alter table student add column " + sub.name + " int";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        PreparedStatement pstmt_2;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt_2 = conn.prepareStatement(sql_2);
            pstmt.setInt(1, sub.ID); //给占位符赋值
            pstmt.setString(2, sub.name); //给占位符赋值
            pstmt.setInt(3, sub.GP); //给占位符赋值
            pstmt.executeUpdate();			//执行
            pstmt_2.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //增加学生信息表
    public static void addStudent(Student stu) {
        StringBuilder string=new StringBuilder("?)");
        List<Integer> studentScore = new ArrayList<>();
        List<String> subjectName = new ArrayList<>();
        for (int i=0; i<Manager.getSubjectCage().size(); i++) {
            string.insert(0,"?,");
            subjectName.add(getSubjectData().get(i).name);
            studentScore.add(stu.getSubjectScore(subjectName.get(i)));
        }
        String sql = "insert into student values(?,"+ string;
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stu.getID()); //给占位符赋值
            pstmt.setString(2, stu.getName()); //给占位符赋值
            for (int i=0; i<Manager.getSubjectCage().size(); i++) {
                pstmt.setInt(i+3, studentScore.get(i));
            }
            pstmt.executeUpdate();			//执行
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
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
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score); //给占位符赋值
            pstmt.setInt(2, stu.getID()); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
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
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stu.getID()); //给占位符赋值
            pstmt.executeUpdate();			//执行
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //删除教学课程表
    public static void deleteSubject(Subject sub) {
        String sql = "delete from subject where id = ?";
        String sql_2 = "alter table student drop column " + sub.name;
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        PreparedStatement pstmt_2;
        try {
            conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt_2 = conn.prepareStatement(sql_2);
            pstmt.setInt(1, sub.ID); //给占位符赋值
            pstmt.executeUpdate();			//执行
            pstmt_2.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstmt);
            DbUtil.close(conn);		//必须关闭
        }
    }

    //查询学生信息
    //TODO "这里调用了刚刚写的getStuData()"
    public static void findStudentList(Integer ID) {
        List<Student> stuList;
        stuList = getStuData();
        for (Student stu : stuList) {
            if (stu.getID() == ID) {
                System.out.println("---ID: " + ID);
                System.out.println("---姓名: " + stu.getName());
                for (int i=0; i<Manager.getSubjectCage().size(); i++) {
                    System.out.println("---" + getSubjectData().get(i).name + stu.getSubjectScore(getSubjectData().get(i).name));
                }
            }
        }
    }

    //查询课程信息
    public static void findSubjectList(Integer ID) {
        List<Subject> subList;
        subList = getSubjectData();
        for (Subject sub : subList) {
            if (sub.ID == ID) {
                System.out.println("---ID: " + ID);
                System.out.println("---名称: " + sub.name);
                System.out.println("---GP: " + sub.GP);
            }
        }
    }
}
