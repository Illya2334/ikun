package com.list.test;

import com.list.dao.StudentDAO;
import com.list.dao.impl.StudentDAOImpl;
import com.list.pojo.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/** 测试类 用于DAO接口测试
 * @Test 将指定方法标记为测试方法 可以不依赖于main方法直接运行
 * @Before 在所有测试方法运行之前执行的代码 一般用于环境的初始化
 * @After 在所有测试方法运行之后执行的代码 一般用于资源回收
 * @author Rynn
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private StudentDAO studentDAO;
    @Before
    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Test
        public void tsetFindById() {
        Assert.assertNotNull(studentDAO.findById(1));
        // 验证结果是否和预期一致
        System.out.println(studentDAO.findById(1));

        }

        @Test
        public void testCount(){
        Assert.assertEquals(3L,(long)studentDAO.count());
        }

        @Test
        public void testFindAll(){
        Assert.assertNotNull(studentDAO.findAll());
        }

        @Test
        public void testDelete(){
        studentDAO.delete(1);
        Assert.assertEquals(2L,(long)studentDAO.count());
    }

    @Test
    public void testInsert() {
        Student s1 = new Student(0, "Rynnnn","男",new Date(),"江苏南京", 1145141919L);
        studentDAO.insert(s1);
        Assert.assertEquals(3L,(long)studentDAO.count());
    }



    @Test
    public void testFindByNameLike() {
        Assert.assertNotNull(studentDAO.findByNameLike("小"));
    }

    @Test
    public void testFindByNameLikeWithLimit() {
        Assert.assertNotNull(studentDAO.findByNameLikeWithLimit("小若言酱",0,1));
    }

    @Test
    public void testFindWithLimit() {
        Assert.assertNotNull(studentDAO.findWithLimit(0,1));
    }
}
