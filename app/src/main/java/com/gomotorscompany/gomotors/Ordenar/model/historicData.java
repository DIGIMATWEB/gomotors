package com.gomotorscompany.gomotors.Ordenar.model;

public class historicData {
    private Integer Id;
    private Integer iteration;
    private Double lat;
    private Double longitude;
    public historicData( Integer Id, Integer iteration,Double lat, Double longitude)
    {
        this.Id=Id;
        this.iteration=iteration;
        this.lat=lat;
        this.longitude=longitude;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

}
