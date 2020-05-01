package com.project.jingmaoquan.dto;

public class CommentCreateDTO {
    private Long parentId; // 对于一级评论是帖子的 id，对于二级评论的是对应的一级评论的 id
    private Integer type; // 评论的类型，1-一级评论，2-二级评论
    private String content;
    private Integer parentType; // 1-讨论帖 2-二手贴 3-任务贴
    private String parentUsername;
    private String parentContent;
    private Long parentUserId; // 被回复人的 id
    private Long articleId; // 所属帖子的 id

    @Override
    public String toString() {
        return "CommentCreateDTO{" +
                "parentId=" + parentId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", parentType=" + parentType +
                ", parentUsername='" + parentUsername + '\'' +
                ", parentContent='" + parentContent + '\'' +
                ", parentUserId=" + parentUserId +
                '}';
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
