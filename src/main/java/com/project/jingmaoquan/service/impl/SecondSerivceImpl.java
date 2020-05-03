package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.dto.PaginationDTO;
import com.project.jingmaoquan.dto.SecondDTO;
import com.project.jingmaoquan.mapper.SecondMapper;
import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.*;
import com.project.jingmaoquan.service.SecondService;
import com.project.jingmaoquan.util.SecondPhotoUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecondSerivceImpl implements SecondService {
    @Autowired
    private SecondMapper secondMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public PaginationDTO<SecondDTO> list(Integer page, Integer size) {

        // 查询数据
        int offset = size * (page - 1);

        SecondExample secondExample = new SecondExample();
        secondExample.setOrderByClause("publish_time desc");
        List<SecondWithBLOBs> seconds = secondMapper.selectByExampleWithBLOBsWithRowbounds(secondExample, new RowBounds(offset, size));

        List<SecondDTO> secondDTOS = new ArrayList<>();
        for (SecondWithBLOBs second : seconds) {
            SecondDTO secondDTO = new SecondDTO();
            BeanUtils.copyProperties(second, secondDTO);

            // 获取图片元素列表
            List<String> photos = SecondPhotoUtil.getPhotos(second.getPhoto());
            secondDTO.setPhotos(photos);

            // 获取发布人用户名
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andUserIdEqualTo(second.getPublisherId());
            List<UserInfo> userInfo = userInfoMapper.selectByExample(userInfoExample);
            secondDTO.setPublishUsername(userInfo.get(0).getUsername());


            secondDTOS.add(secondDTO);

        }
        PaginationDTO<SecondDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setData(secondDTOS);

        // 获取总记录数
        Long totalCount = secondMapper.countByExample(new SecondExample());
        // 设置分页
        paginationDTO.setPagination(totalCount, page, size);

        return paginationDTO;
    }

    @Override
    public SecondDTO findBySecondId(Long secondId) {
        // 查询帖子信息
        SecondExample secondExample = new SecondExample();
        secondExample.createCriteria().andSecondIdEqualTo(secondId);
        List<SecondWithBLOBs> secondWithBLOBs = secondMapper.selectByExampleWithBLOBs(secondExample);

        // 查询发布人信息
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(secondWithBLOBs.get(0).getPublisherId());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        SecondDTO secondDTO = new SecondDTO();
        BeanUtils.copyProperties(secondWithBLOBs.get(0), secondDTO);

        secondDTO.setPublishUsername(userInfos.get(0).getUsername());

        return secondDTO;
    }

    /**
     * 编辑帖子
     *
     * @param name
     * @param price
     * @param detail
     * @param secondId
     */
    @Override
    public void update(String name, String price, String detail, Long secondId) {
        SecondExample secondExample = new SecondExample();
        secondExample.createCriteria().andSecondIdEqualTo(secondId);

        SecondWithBLOBs secondWithBLOBs = new SecondWithBLOBs();
        secondWithBLOBs.setName(name);
        secondWithBLOBs.setPrice(price);
        secondWithBLOBs.setDetail(detail);

        secondMapper.updateByExampleSelective(secondWithBLOBs, secondExample);
    }

    /**
     * 发布帖子
     * @param name
     * @param price
     * @param detail
     * @param userInfo
     * @return
     */
    @Override
    public Long create(String name, String price, String detail, UserInfo userInfo) {
        SecondWithBLOBs secondWithBLOBs = new SecondWithBLOBs();
        secondWithBLOBs.setName(name);
        secondWithBLOBs.setPrice(price);
        secondWithBLOBs.setDetail(detail);
        // 提取照片
        List<String> photos = SecondPhotoUtil.getPhotos(detail);

        if (photos != null) {
            StringBuilder stringBuilder = new StringBuilder();
            int i=0;
            for (String photo : photos) {
                i++;
                if (i>4){
                    break;
                }
                stringBuilder.append(photo);

            }
            secondWithBLOBs.setPhoto(stringBuilder.toString());
        }
        secondWithBLOBs.setPublisherId(userInfo.getUserId());
        secondWithBLOBs.setPublishTime(System.currentTimeMillis());
        secondWithBLOBs.setState(false);
        secondMapper.insertSelective(secondWithBLOBs);

        return secondWithBLOBs.getSecondId();
    }
}
