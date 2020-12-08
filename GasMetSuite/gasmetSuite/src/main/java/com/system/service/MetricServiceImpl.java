package com.system.service;


import com.system.entity.CostSmell;
import com.system.entity.metricDescription;
import com.system.repository.MetricRepoCostSmell;
import com.system.repository.MetricRepoDesc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    @Autowired
    private MetricRepoDesc mRepo;
    @Autowired
    private MetricRepoCostSmell costSmellRepo;

    @Override
    public void insertMetricDesc(metricDescription i) {
        mRepo.save(i);
    }

    @Override
    public List<metricDescription> findAll() {
        return mRepo.findAll();
    }

    @Override
    public metricDescription findByMetric(String id) {

        return mRepo.findById(id).get();
    }

    @Override
    public CostSmell findByCostSmell(String id) {
    		
        return costSmellRepo.findById(id).orElseGet(null);
    }

}
