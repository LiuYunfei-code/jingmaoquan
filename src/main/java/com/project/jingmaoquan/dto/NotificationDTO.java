package com.project.jingmaoquan.dto;

public class NotificationDTO {
    private String notifierUsername;
    private String notifyDesc;
    private String articleTitle;
    private Long articleId;
    private Integer articleType;
    private String content;
    private Long createTime;
    private Long commentId;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotifierUsername() {
        return notifierUsername;
    }

    public void setNotifierUsername(String notifierUsername) {
        this.notifierUsername = notifierUsername;
    }

    public String getNotifyDesc() {
        return notifyDesc;
    }

    public void setNotifyDesc(String notifyDesc) {
        this.notifyDesc = notifyDesc;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }
}
