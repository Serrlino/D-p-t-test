package com.bd_project.organisation_manage.service;

import java.util.List;

import com.bd_project.organisation_manage.model.Series;

public interface SeriesService {
	public Series createSeries(Series series);
	public Series createSeries(String seriesName);
	public List<Series> indexSeries();
    public Series findSeries(Integer code);
    public Series findSeries(String seriesName);
    public Series modifySeries(Integer code, String seriesName);
    public Series deleteSeries(Integer code);
    public void deleteAllSeries();
}
