package com.project.jingmaoquan.mapper;

import com.project.jingmaoquan.model.Comment;
import com.project.jingmaoquan.model.CommentExample;
import com.project.jingmaoquan.model.CommentWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
@Mapper
public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    long countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int insert(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int insertSelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    List<CommentWithBLOBs> selectByExampleWithBLOBsWithRowbounds(CommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    List<CommentWithBLOBs> selectByExampleWithBLOBs(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    List<Comment> selectByExampleWithRowbounds(CommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    CommentWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByExampleSelective(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByPrimaryKeySelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 26 20:54:16 CST 2020
     */
    int updateByPrimaryKey(Comment record);
}