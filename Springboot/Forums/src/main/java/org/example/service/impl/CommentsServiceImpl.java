package org.example.service.impl;

import org.example.mapper.CommentsMapper;
import org.example.pojo.CommentsDTO;
import org.example.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<CommentsDTO> selectByIdComments(String articleId) {
        List<CommentsDTO> commentsDTOS=  commentsMapper.selectByIdComments(articleId);

        return commentsDTOS;
    }
}
