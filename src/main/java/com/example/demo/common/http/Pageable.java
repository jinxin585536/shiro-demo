package com.example.demo.common.http;

/**
 * @author huangyy@zjiec.com
 * @date 2020/11/18
 */
public class Pageable {

    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        if(null == page){
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if(null == pageSize){
            return 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
