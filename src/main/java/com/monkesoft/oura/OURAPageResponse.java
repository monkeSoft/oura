package com.monkesoft.oura;

public class OURAPageResponse<T> extends OURADataResponse<T> {

    private long rowStart;
    private long rowEnd;
    private long rowTotal;

    private int pageNum;
    private int pageSize;
    private int pageTotal;

    public long getRowStart() {
        return rowStart;
    }

    public OURAPageResponse<T> setRowStart(long rowStart) {
        this.rowStart = rowStart;
        return this;
    }

    public long getRowEnd() {
        return rowEnd;
    }

    public OURAPageResponse<T> setRowEnd(long rowEnd) {
        this.rowEnd = rowEnd;
        return this;
    }

    public long getRowTotal() {
        return rowTotal;
    }

    public OURAPageResponse<T> setRowTotal(long rowTotal) {
        this.rowTotal = rowTotal;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public OURAPageResponse<T> setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public OURAPageResponse<T>  setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;

    }

    public int getPageTotal() {
        return pageTotal;
    }

    public OURAPageResponse<T>  setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
        return this;

    }
}
