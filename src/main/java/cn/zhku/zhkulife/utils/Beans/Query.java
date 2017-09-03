package cn.zhku.zhkulife.utils.Beans;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/23 11:21.
 * @E-mail gonefuture@qq.com
 */
public class Query {
    private  int pageNum = 1;

    private int pageSize = 10;

    private String since;

    private String end;


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

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
