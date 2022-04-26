package com.greenfox.errorreporter.repositories;

import com.greenfox.errorreporter.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

    List<Report> findReportsByManufacturer(String manufacturer);

    List<Report> findReportsByReporter(String reporter);

    @Query(value = "SELECT distinct reporter FROM report", nativeQuery = true)
    List<String> findDistinctReporter();
}
