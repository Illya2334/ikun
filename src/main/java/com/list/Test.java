package com.list;

import com.list.dao.StudentDAO;
import com.list.dao.impl.StudentDAOImpl;
import com.list.pojo.Student;

import java.util.Date;

/**JDBC java database connection
 * @author Rynn
 * @date 2024/7/22 下午2:48
 */
public class Test {
    public static void main(String[] args) {
//        // 驱动加载
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("驱动加载成功");
//        // 获取jdbc连接
//        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf-8";
//        String username = "root";
//        String password = "a12345";
//        Connection connection = null;
//        try { connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("数据库连接成功");
//        // 创建编译语句
//        try {
//            Statement s = connection.createStatement();
//            // 准备一个SQL语句
//            String sql = "INSERT INTO student1 (NAME,gender,birthday,addr,qqnumber) VALUES\n ('小若言酱','女','2003-02-28','江苏南京',1145141919)";
//            // 运行SQL语句
//            s.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
        Student s1 = new Student(0, "Rynn","男",new Date(),"江苏南京", 1145141919L);
        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.insert(s1);
        Student s2 = new Student(1, "可爱若言酱","男",new Date(),"南京金陵", 1145141909L);
        studentDAO.update(s2);
    }
}
