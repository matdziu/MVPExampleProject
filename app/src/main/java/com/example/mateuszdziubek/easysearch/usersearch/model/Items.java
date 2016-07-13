package com.example.mateuszdziubek.easysearch.usersearch.model;


public class Items
{
    private String region;

    private _debug _debug;

    private String urlizedRegion;

    private String countryCode;

    private String country;

    private String id;

    private String timezone;

    private String source;

    private String forecast_id;

    private String name;

    private String continent;

    private String urlizedName;

    private Coordinates coordinates;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public _debug get_debug ()
    {
        return _debug;
    }

    public void set_debug (_debug _debug)
    {
        this._debug = _debug;
    }

    public String getUrlizedRegion ()
    {
        return urlizedRegion;
    }

    public void setUrlizedRegion (String urlizedRegion)
    {
        this.urlizedRegion = urlizedRegion;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getForecast_id ()
    {
        return forecast_id;
    }

    public void setForecast_id (String forecast_id)
    {
        this.forecast_id = forecast_id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getContinent ()
    {
        return continent;
    }

    public void setContinent (String continent)
    {
        this.continent = continent;
    }

    public String getUrlizedName ()
    {
        return urlizedName;
    }

    public void setUrlizedName (String urlizedName)
    {
        this.urlizedName = urlizedName;
    }

    public Coordinates getCoordinates ()
    {
        return coordinates;
    }

    public void setCoordinates (Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", _debug = "+_debug+", urlizedRegion = "+urlizedRegion+", countryCode = "+countryCode+", country = "+country+", id = "+id+", timezone = "+timezone+", source = "+source+", forecast_id = "+forecast_id+", name = "+name+", continent = "+continent+", urlizedName = "+urlizedName+", coordinates = "+coordinates+"]";
    }
}
