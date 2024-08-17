package asm2.service;

import asm2.entity.ApplyPost;

public interface ApplyPostService {
    int countApplyPosts();
    public void saveApplyPost(ApplyPost applyPost);
}
