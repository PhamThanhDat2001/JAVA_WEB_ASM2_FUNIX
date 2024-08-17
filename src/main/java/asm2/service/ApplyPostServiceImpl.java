package asm2.service;

import asm2.dao.ApplyPostDAO;
import asm2.entity.ApplyPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApplyPostServiceImpl implements ApplyPostService{
    @Autowired
    ApplyPostDAO applyPostDAO;
    @Override
    public int countApplyPosts() {
        return applyPostDAO.countApplyPosts();
    }

    @Override
    @Transactional
    public void saveApplyPost(ApplyPost applyPost) {
        applyPostDAO.saveApplyPost(applyPost);
    }
}
