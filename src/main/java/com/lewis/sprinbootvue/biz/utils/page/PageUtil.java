package com.lewis.sprinbootvue.biz.utils.page;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {

    public static <T> PageList<T> getPageList(Paginator paginator, List<T> data) {
        PageInfo<T> pageInfo = new PageInfo<>(data);
        paginator.setCurrentPage(pageInfo.getPageNum());
        paginator.setTotalCount(pageInfo.getTotal());
        return new PageList<>(data, paginator);
    }
}
