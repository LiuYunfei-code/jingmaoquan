package com.project.jingmaoquan.model;

public class SecondWithBLOBs extends Second {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column second_hand.detail
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private String detail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column second_hand.photo
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    private String photo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column second_hand.detail
     *
     * @return the value of second_hand.detail
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column second_hand.detail
     *
     * @param detail the value for second_hand.detail
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column second_hand.photo
     *
     * @return the value of second_hand.photo
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column second_hand.photo
     *
     * @param photo the value for second_hand.photo
     *
     * @mbg.generated Tue May 12 17:38:09 CST 2020
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }
}