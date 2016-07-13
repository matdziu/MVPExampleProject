package com.example.mateuszdziubek.easysearch.usersearch.model;

public class _metadata
{
    private String page_size;

    private String page;

    private String total_count;

    private String page_count;

    public String getPage_size ()
    {
        return page_size;
    }

    public void setPage_size (String page_size)
    {
        this.page_size = page_size;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_count ()
    {
        return total_count;
    }

    public void setTotal_count (String total_count)
    {
        this.total_count = total_count;
    }

    public String getPage_count ()
    {
        return page_count;
    }

    public void setPage_count (String page_count)
    {
        this.page_count = page_count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [page_size = "+page_size+", page = "+page+", total_count = "+total_count+", page_count = "+page_count+"]";
    }
}
