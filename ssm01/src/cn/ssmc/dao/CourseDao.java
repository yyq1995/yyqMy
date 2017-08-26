package cn.ssmc.dao;

import java.util.List;

import cn.ssmc.entity.Course;

public interface CourseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    int insert(Course course);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    int insertSelective(Course course);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    Course selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    int updateByPrimaryKeySelective(Course course);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Sun Aug 20 13:05:37 CST 2017
     */
    int updateByPrimaryKey(Course course);
    
    List<Course> selectCourseWithCollege(Course course);
    //根据collegeid查courselist
    List<Course> selectCourseByCollegeId(Integer collegeId);
    
    
    int selectCountByChoos(int id);
}