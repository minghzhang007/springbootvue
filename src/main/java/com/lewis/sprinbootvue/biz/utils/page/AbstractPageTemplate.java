package com.lewis.sprinbootvue.biz.utils.page;

import com.github.pagehelper.PageHelper;

import java.util.List;

public abstract class AbstractPageTemplate<T> {

    public PageList<T> getItemsByPage(Paginator paginator) {
        PageHelper.startPage(paginator.getCurrentPage(), paginator.getPageSize());
        List<T> items = queryItems();
        PageList<T> pageList = PageUtil.getPageList(paginator, items);
        PageHelper.clearPage();
        return pageList;
    }

    protected abstract List<T> queryItems();
}
