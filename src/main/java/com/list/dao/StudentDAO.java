package com.list.dao;

import com.list.course.pojo.Student;

import java.util.List;

/**
 * @author Rynn
 * @date 2024/7/22 下午3:34
 */
public interface StudentDAO {

    /** 插入一个学生信息
     * @param student 要插入的学生信息 主键无需提供
     */
    void insert(Student student);

    /** 根据主键删除学生信息
     * @param id  要删除的学生信息的id
     */
    void delete(Integer id);

    /** 根据学生的id修改学生的所有信息
     * @param student 要修改的学生id 和需要被修改的字段值
     */
    void update(Student student);

    /** 统计表记录总数
     * @return 记录总是 如果为零 则说明没有数据
     */
    Integer count();// select count(*) from student;

    /** 根据学生的id查找对应的学生的信息
     * @param id 要找的学生的id
     * @return id对应的学生记录 如果对应的学生记录不存在 则返回null
     */
    Student findById(Integer id);

    /** 获取所有学生信息
     * @return 所有学生对应的集合
     */
    List<Student> findAll();// select * from student;

    /** 根据名称模糊查找学生名称
     * @param name 模糊查询关键字
     * @return 模糊查询的学生结果
     */
    List<Student> findByNameLike(String name);// select * from student where name like '%name%';


    /** 根据学生名称 进行模糊分页查询
     * @param name 模糊查询的关键字
     * @param start limit起始参数
     * @param limit 要分页截取的行数
     * @return 分页模糊查询的学生数据结果 若找不到 则返回为null
     */
    List<Student> findByNameLikeWithLimit(String name,int start,int limit);

    /** 分页查询学生
     * @param start limit起始参数
     * @param limit 要分页截取的行数
     * @return 分页查询的结果 若找不到 则返回null
     */
    List<Student> findWithLimit(int start,int limit);

}
