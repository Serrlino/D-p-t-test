package com.bd_project.organisation_manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd_project.organisation_manage.model.Series;
import com.bd_project.organisation_manage.repository.SeriesRepository;

@Service
public class SeriesServiceImplementation implements SeriesService {
	
	@Autowired
	private SeriesRepository seriesrepository;

	@Override
	public Series createSeries(Series series) {
		// TODO Auto-generated method stub
        if(seriesrepository.findBySeriesName(series.getSeriesName()) != null)
            return null;

		return seriesrepository.save(series);
	}

	@Override
	public Series createSeries(String seriesName)
    {
        if(seriesrepository.findBySeriesName(seriesName) != null)
            return null;

        Series series = new Series();

        series.setSeriesName(seriesName);

        return seriesrepository.save(series);
    }

	@Override
	public List<Series> indexSeries() {
		// TODO Auto-generated method stub
		return seriesrepository.findAll();
	}

	@Override
    public Series findSeries(Integer code)
    {
        Series series = seriesrepository.findByCode(code);

        return series;
    }

    @Override
    public Series findSeries(String seriesName)
    {
        Series series = seriesrepository.findBySeriesName(seriesName);

        return series;
    }

	@Override
    public Series modifySeries(Integer code, String seriesName)
    {
        if(seriesrepository.findBySeriesName(seriesName) != null)
            return null;

        Series series = seriesrepository.findByCode(code);

        series.setSeriesName(seriesName);

        return seriesrepository.save(series);
    }

	@Override
    public Series deleteSeries(Integer code)
    {
        Series series = seriesrepository.findByCode(code);
        seriesrepository.deleteById(code);

        return series;
    }

	@Override
    public void deleteAllSeries()
    {
        seriesrepository.deleteAll();
    }

}
