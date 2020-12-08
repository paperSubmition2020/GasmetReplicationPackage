/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.repository;

import com.system.entity.CostSmell;
import com.system.entity.Metriche;
import com.system.entity.metricDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Michele Fredella
 */
@Repository("MetricRepoCostSmell")
public interface MetricRepoCostSmell extends JpaRepository<CostSmell, String> {

}
