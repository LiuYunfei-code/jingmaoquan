package com.project.jingmaoquan.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO<T> {
    private List<T> data;
    private Integer page;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer totalPage;
    private List<Integer> pages=new ArrayList<>();
    private Long totalCount;

    public void setPagination(Long totalCount, Integer page, Integer size) {
        this.totalCount=totalCount;
        // 计算总页数
        if (totalCount%size==0){ // 总数能被每页条数整除
            totalPage=(int)(totalCount/size);
        }else { // 总数不能被每页条数整除
            totalPage=(int)(totalCount/size+1);
        }
        // 处理异常页数
        if (page < 1) {
            this.page = 1;
        } else if (page > totalPage) {
            this.page= totalPage;
        }else {
            this.page=page;
        }

        pages.add(this.page);


        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }

        // 显示上一页
        if (this.page>1){
            showPrevious=true;
        }
        // 显示下一页
        if (this.page<totalPage){
            showNext=true;
        }

        // 显示第一页
        if (!pages.contains(1)){
            showFirstPage=true;
        }

        // 显示最后一页
        if (!pages.contains(totalPage)){
            showEndPage=true;
        }



    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
