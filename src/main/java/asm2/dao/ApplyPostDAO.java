package asm2.dao;

import asm2.entity.ApplyPost;

public interface ApplyPostDAO {
    int countApplyPosts();
    public void saveApplyPost(ApplyPost applyPost);

}
