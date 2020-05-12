package com.project.jingmaoquan.model;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notifier_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long notifierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notifier_username
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private String notifierUsername;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.article_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long articleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.article_type
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Integer articleType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.receiver_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.comment_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long commentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.create_time
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.state
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.id
     *
     * @return the value of notification.id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.id
     *
     * @param id the value for notification.id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notifier_id
     *
     * @return the value of notification.notifier_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getNotifierId() {
        return notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notifier_id
     *
     * @param notifierId the value for notification.notifier_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setNotifierId(Long notifierId) {
        this.notifierId = notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notifier_username
     *
     * @return the value of notification.notifier_username
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public String getNotifierUsername() {
        return notifierUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notifier_username
     *
     * @param notifierUsername the value for notification.notifier_username
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setNotifierUsername(String notifierUsername) {
        this.notifierUsername = notifierUsername == null ? null : notifierUsername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.article_id
     *
     * @return the value of notification.article_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.article_id
     *
     * @param articleId the value for notification.article_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.article_type
     *
     * @return the value of notification.article_type
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Integer getArticleType() {
        return articleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.article_type
     *
     * @param articleType the value for notification.article_type
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.receiver_id
     *
     * @return the value of notification.receiver_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.receiver_id
     *
     * @param receiverId the value for notification.receiver_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.comment_id
     *
     * @return the value of notification.comment_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.comment_id
     *
     * @param commentId the value for notification.comment_id
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.create_time
     *
     * @return the value of notification.create_time
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.create_time
     *
     * @param createTime the value for notification.create_time
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.state
     *
     * @return the value of notification.state
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.state
     *
     * @param state the value for notification.state
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setState(Integer state) {
        this.state = state;
    }
}