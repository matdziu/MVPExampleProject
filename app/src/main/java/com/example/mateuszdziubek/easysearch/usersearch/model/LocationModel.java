package com.example.mateuszdziubek.easysearch.usersearch.model;

public class LocationModel
{
    private Items[] items;

    private _metadata _metadata;

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public _metadata get_metadata ()
    {
        return _metadata;
    }

    public void set_metadata (_metadata _metadata)
    {
        this._metadata = _metadata;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [items = "+items+", _metadata = "+_metadata+"]";
    }
}