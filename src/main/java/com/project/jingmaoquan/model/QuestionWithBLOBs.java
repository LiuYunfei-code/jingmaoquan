package com.project.jingmaoquan.model;

public class QuestionWithBLOBs extends Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.content
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.desc
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    private String desc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.content
     *
     * @return the value of question.content
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.content
     *
     * @param content the value for question.content
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.desc
     *
     * @return the value of question.desc
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.desc
     *
     * @param desc the value for question.desc
     *
     * @mbg.generated Fri Apr 24 14:16:34 CST 2020
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}