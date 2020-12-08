
package com.system.service;

import com.system.entity.CostSmell;
import com.system.entity.metricDescription;
import java.util.List;

public interface MetricService {

    public void insertMetricDesc(metricDescription i);

    public List<metricDescription> findAll();

    public metricDescription findByMetric(String id);

    public CostSmell findByCostSmell(String id);
}
