package com.greenfox.errorreporter.services;

import com.greenfox.errorreporter.models.Report;
import com.greenfox.errorreporter.repositories.ReportRepo;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepo reportRepo;

    @Autowired
    public ReportServiceImpl(ReportRepo reportRepo) {
        this.reportRepo = reportRepo;
    }

    @Override
    public List<Report> showAll() {
        return reportRepo.findAll();
    }

    @Override
    public void addNewReport(Report report) {
        reportRepo.save(report);
    }

    @Override
    public void deleteTicket(long id) {
        reportRepo.deleteById(id);
    }

    @Override
    public Report getById(long id) {
        Optional<Report> optionalReport = reportRepo.findById(id);
        Report result;

        if (optionalReport.isPresent()) {
            result = optionalReport.get();
            return result;
        }

        return null;
    }

    @Override
    public List<Report> findReportsByManufacturer(String manufacturer) {
        List<Report> retrievedList = reportRepo.findReportsByManufacturer(manufacturer);

        if (retrievedList.isEmpty()) {
            return new ArrayList<>();
        }

        else {
            return reportRepo.findReportsByManufacturer(manufacturer);
        }
    }

    @Override
    public List<Report> findReportsByReporter(String reporter) {
        List<Report> retrievedList = reportRepo.findReportsByReporter(reporter);

        if (retrievedList.isEmpty()) {
            return new ArrayList<>();
        }

        else {
            return reportRepo.findReportsByReporter(reporter);
        }
    }

    @Override
    public List<String> findDistinctReporter() {
        return reportRepo.findDistinctReporter();
    }

}
