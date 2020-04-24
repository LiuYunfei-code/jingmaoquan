package com.project.jingmaoquan.mapper;

import com.project.jingmaoquan.model.Question;
import com.project.jingmaoquan.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
   void incView(Long id);
}