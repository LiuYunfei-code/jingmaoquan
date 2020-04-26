package com.project.jingmaoquan.dto;

public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
    private Integer parentType; // 1-讨论帖 2-二手贴 3-任务贴
    private String parentUsername;
    private String parentContent;

    @Override
    public String toString() {
        return "CommentCreateDTO{" +
                "parentId=" + parentId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", parentType=" + parentType +
                ", parentUsername='" + parentUsername + '\'' +
                ", parentContent='" + parentContent + '\'' +
                '}';
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
