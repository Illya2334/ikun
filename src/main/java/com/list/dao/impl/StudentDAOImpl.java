package com.list.dao.impl;

import com.list.course.DBUtil;
import com.list.course.dao.StudentDAO;
import com.list.course.pojo.Student;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

/**
 * @author Rynn
 * @date 2024/7/22 下午3:52
 */
public class StudentDAOImpl implements StudentDAO {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void insert(Student student) {

        try (
            Connection c = DBUtil.getConnection();
            Statement statementInsert = c.createStatement();
            // 1.传参麻烦
            // 2.性能较差
            // 3.存在SQL注入攻击问题  // 先传参 后编译

            // preparedStatement
            // 1.先编译 后传参 效率更高
            // 2.传参方便
            // 3.能够有效防止SQL注入攻击的问题
            ){
            String sql = "INSERT INTO student1 (NAME,gender,birthday,addr,qqnumber)"
                +" VALUES('%s','%s','%s','%s','%d')" ;
            sql = String.format(sql,student.getName(),student.getGender(),sdf.format(student.getBirthday()),student.getAddr(),student.getQqnumber());
            statementInsert.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from student1 where id=?";
        try (
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ){
            ps.setInt(1,id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
            String sql = "update student1 set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try (
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ){
            c.setAutoCommit(false);
            c.rollback();
            ps.setString(1,student.getName());
            ps.setString(2,student.getGender());
            ps.setDate(3,new Date(student.getBirthday().getTime()));
            ps.setString(4,student.getAddr());
            ps.setLong(5,student.getQqnumber());
            ps.setInt(6,student.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        String sql = "select count(*) from student1";
        try(
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ){
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                // 每次从结果集读取一行数据
                // 根据读取的字段数据不同 使用不同的get方法
                // 方法参数有两种 一种是获取的字段在查询结果中出现的顺序
                // 可以不写字段出现新婚下 而写字段的名称
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(Integer id) {
        String sql = "select * from student1 where id=?";
        Student student = null;
        try (
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return findWithLimit(0, Integer.MAX_VALUE);

    }

    @Override
    public List<Student> findByNameLike(String name) {
        return findByNameLikeWithLimit(name,0,Integer.MAX_VALUE);
    }


    @Override
    public List<Student> findByNameLikeWithLimit(String name, int start, int limit) {
        String sql = "select * from student1 where sname like concat('%',?,'%')limit ?,?";
        List<Student> student1 = Collections.emptyList();
        try(
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){
            ps.setString(1,name);
            ps.setInt(2,start);
            ps.setInt(3,limit);
            ResultSet  rs = ps.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setBirthday(rs.getDate("birthday"));
                s.setAddr(rs.getString("addr"));
                s.setQqnumber(rs.getLong("qqnumber"));
                student1.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return student1;
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql = "select * from student1 limit ?,?";
        List<Student> student1 = Collections.emptyList();
        try (
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            ps.setInt(1,start);
            ps.setInt(2,limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                student1.add(student);
            }

        } catch (Exception e) {
           printStackTrace();
        }
        return student1;
    }
}
