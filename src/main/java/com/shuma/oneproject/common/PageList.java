package com.shuma.oneproject.common;

import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class PageList<E> {

    /**
     * 默认每页个数
     */
    public final static int DEFAULT_PAGESIZE = 20;

    /**
     * 当前页
     */
    private int page = 1;

    /**
     * 每页个数
     */
    private int pagesize = DEFAULT_PAGESIZE;

    /**
     * 记录总数
     */
    private int totalItems = 0;

    /**
     * 详细数据
     */
    private List<E> data;

    public PageList() {}

    public PageList(int page, int pagesize, int totalItems, List<E> data) {
        this.setPage(page);
        this.setPagesize(pagesize);
        this.setTotalItems(totalItems);
        this.setData(data);
    }

    public int getPage() { return this.page; }

    public int getPagesize() { return this.pagesize; }

    public int getTotalItems() { return this.totalItems; }

    public List<E> getData() { return this.data; }

    public int getTotalPage() {
        return this.totalItems / this.pagesize +
                (this.totalItems % this.pagesize == 0 ? 0 : 1);
    }

    public int getPreviousPage() {
        return this.page - 1 <= 0 ? 1 : this.page - 1;
    }

    public int getNextPage() {
        return this.page + 1 >= getTotalPage() ? getTotalPage() : this.page + 1;
    }

    public void setPage(int page) {
        this.page = page;
        if(this.page <= 0) this.page = 1;
        if(this.page >= getTotalPage()) this.page = getTotalPage();
    }

    public void setPagesize(int pagesize) { this.pagesize = pagesize; }

    public void setTotalItems(int totalItems) { this.totalItems = totalItems; }

    public void setData(List<E> data) { this.data = data; }

}
