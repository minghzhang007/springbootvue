package com.lewis.sprinbootvue.biz.utils.page;

public class Paginator {

    /**
     * 默认每页数据条目20
     */
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 数据总量
     */
    private long totalCount;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 每页条目数
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 查询起始条目编号，默认从0开始
     */
    private Integer startIndex;

    /**
     * 当前页页码
     */
    private Integer currentPage;

    /**
     * 上一页页码
     */
    private Integer nextPage;

    /**
     * 下一页页码
     */
    private Integer priviousPage;

    /**
     * 默认构造函数
     */
    protected Paginator() {
        super();
    }

    /**
     * 构造函数
     *
     * @param totalCount  数据总条目数
     * @param pageSize    每页条目数
     * @param currentPage 当前页码
     */
    protected Paginator(long totalCount, int pageSize, Integer currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    /**
     * 获取数据总条目数
     *
     * @return 数据总条目数
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置数据总条目数
     *
     * @param totalCount 数据总条目数
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取每页数据条目数
     *
     * @return 每页数据条目数
     */
    public int getPageSize() {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * 设置每页条目数
     *
     * @param pageSize 每页条目数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页码
     *
     * @return 当前页面
     */
    public Integer getCurrentPage() {
        if (currentPage <= 0) {
            return 1;
        } else if (currentPage > getTotalPage()) {
            return getTotalPage();
        }
        return currentPage;
    }

    /**
     * 设置当前页码
     *
     * @param currentPage 当前页码
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public Integer getTotalPage() {
        if (totalCount > 0) {
            if (getPageSize() > 0) {
                totalPage = (int)(totalCount / getPageSize());
                if ((totalCount % getPageSize()) > 0) {
                    totalPage++;
                }
            } else {
                totalPage = 1;
            }
        } else {
            totalPage = 0;
        }
        return totalPage;
    }

    /**
     * 获取查询起始条目编号
     *
     * @return 查询起始条目编号
     */
    public Integer getStartIndex() {
        if (getCurrentPage() > 0) {
            if (getPageSize() > 0) {
                startIndex = (getCurrentPage() - 1) * getPageSize();
            } else {
                startIndex = 0;
            }
        } else {
            startIndex = 0;
        }
        return startIndex;
    }

    /**
     * 获取下一页页码
     *
     * @return 下一页页码
     */
    public Integer getNextPage() {
        if (getCurrentPage() >= getTotalPage()) {
            nextPage = getTotalPage();
        } else {
            if (getTotalPage() == 0) {
                nextPage = 1;
            } else {
                nextPage = getCurrentPage() + 1;
            }

        }
        return nextPage;
    }

    /**
     * 获取上一页页码
     *
     * @return 上一页页码
     */
    public Integer getPriviousPage() {
        if (getCurrentPage() <= 1) {
            priviousPage = 1;
        } else if (getCurrentPage() > getTotalPage()) {
            priviousPage = getTotalPage() - 1;
        } else {
            priviousPage = getCurrentPage() - 1;
        }
        return priviousPage;
    }
}
