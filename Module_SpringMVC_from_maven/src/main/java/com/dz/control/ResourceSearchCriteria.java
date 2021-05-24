package com.dz.control;

import java.io.Serializable;

public class ResourceSearchCriteria implements Serializable {

    private int pageSize;

    private int page;;

    private String titleSearch;

    private long idSearch;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTitleSearch() {
        return titleSearch;
    }

    public void setTitleSearch(String titleSearch) {
        this.titleSearch = titleSearch;
    }

    public long getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(long idSearch) {
        this.idSearch = idSearch;
    }
}
