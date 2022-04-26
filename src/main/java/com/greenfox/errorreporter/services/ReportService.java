package com.greenfox.errorreporter.services;

import com.greenfox.errorreporter.models.Report;

import java.util.List;

public interface ReportService {

    List<Report> showAll();

    void addNewReport(Report report);

    void deleteTicket(long id);

    Report getById(long id);

    List<Report> findReportsByManufacturer(String manufacturer);

    List<Report> findReportsByReporter(String reporter);

    List<String> findDistinctReporter();
}
