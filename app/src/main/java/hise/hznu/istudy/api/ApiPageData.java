package hise.hznu.istudy.api;

import java.util.List;

/**
 * Created by lenove on 2016/7/15.
 */
public class ApiPageData<T> {
    private int totalPage;
    private int currentPage;
    private List<T> dataList;

    public ApiPageData() {
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
