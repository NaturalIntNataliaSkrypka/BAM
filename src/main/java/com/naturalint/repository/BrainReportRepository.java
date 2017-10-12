package com.naturalint.repository;

import com.naturalint.entity.BrainReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yka on 12/10/2017.
 */
public interface BrainReportRepository extends JpaRepository<BrainReport, Long>{
}
