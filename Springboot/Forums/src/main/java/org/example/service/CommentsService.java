package org.example.service;

import org.example.pojo.CommentsDTO;

import java.util.List;

public interface CommentsService {
    List<CommentsDTO> selectByIdComments(String articleId);
}
