package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.TaskDTO;
import com.project.jingmaoquan.mapper.TaskMapper;
import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.Task;
import com.project.jingmaoquan.model.TaskExample;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import com.project.jingmaoquan.service.TaskService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询任务列表
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO<TaskDTO> list(Integer page, Integer size) {
        int offset=size*(page-1);

        // 查询数据
        List<Task> tasks = taskMapper.selectByExampleWithBLOBsWithRowbounds(new TaskExample(), new RowBounds(offset, size));

        List<TaskDTO> taskDTOS=new ArrayList<>();

        for (Task task : tasks) {
          TaskDTO taskDTO=new TaskDTO();
            BeanUtils.copyProperties(task,taskDTO);
            // 接取描述
            String desc = task.getDetail().replaceAll("<[^>]+>", "");
            if (desc.length()>100){ // 长度小于100个字符
                desc=desc.substring(0,100);
            }

            taskDTO.setDesc(desc);
            taskDTOS.add(taskDTO);
        }
        // 获取总记录数
        Long totalCount=taskMapper.countByExample(new TaskExample());
        // 设置分页
        PaginationDTO<TaskDTO> paginationDTO=new PaginationDTO<>();
        paginationDTO.setData(taskDTOS);
        paginationDTO.setPagination(totalCount,page,size);

        return paginationDTO;
    }

    /**
     * 根据id查询任务信息
     * @param taskId
     * @return
     */
    @Override
    public TaskDTO findById(Long taskId) {

        // 查询数据
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andTaskIdEqualTo(taskId);
        List<Task> tasks = taskMapper.selectByExampleWithBLOBs(taskExample);


        // 查询发布人信息
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(tasks.get(0).getPublisherId());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        TaskDTO taskDTO=new TaskDTO();
        BeanUtils.copyProperties(tasks.get(0),taskDTO);
        taskDTO.setUserInfo(userInfos.get(0));

        return taskDTO;
    }
}
