package com.project.jingmaoquan.model;

public class CommentWithBLOBs extends Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.parent_content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    private String parentContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.content
     *
     * @return the value of comment.content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.content
     *
     * @param content the value for comment.content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.parent_content
     *
     * @return the value of comment.parent_content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    public String getParentContent() {
        return parentContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.parent_content
     *
     * @param parentContent the value for comment.parent_content
     *
     * @mbg.generated Fri May 01 14:55:40 CST 2020
     */
    public void setParentContent(String parentContent) {
        this.parentContent = parentContent == null ? null : parentContent.trim();
    }
}