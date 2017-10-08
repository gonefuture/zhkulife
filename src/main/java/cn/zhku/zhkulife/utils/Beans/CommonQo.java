package cn.zhku.zhkulife.utils.Beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/23 11:21.
 * @E-mail gonefuture@qq.com
 */
public class CommonQo {
    private  int pageNum = 1;

    private int pageSize = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date since;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CommonQo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", since=" + since +
                ", end=" + end +
                '}';
    }
}
