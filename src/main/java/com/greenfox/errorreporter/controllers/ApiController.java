package com.greenfox.errorreporter.controllers;

import com.greenfox.errorreporter.models.Report;
import com.greenfox.errorreporter.models.dto.SecretDTO;
import com.greenfox.errorreporter.models.dto.TicketResponseDTO;
import com.greenfox.errorreporter.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    private ReportService reportService;

    @Autowired
    public ApiController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = {"/complete/{id}"})
    public ResponseEntity<?> postComplete(@PathVariable long id, @RequestBody(required = false) SecretDTO secretDTO) {
        Report report = reportService.getById(id);

        if (report == null) {
            return ResponseEntity.status(404).body("not found");
        } else if (secretDTO == null) {
            return ResponseEntity.status(400).body("secret is not part of the request");
        } else if (!secretDTO.getSecret().equals("voala")) {
            return ResponseEntity.status(403).body("secret is not voala");
        } else {

            reportService.deleteTicket(id);
            return ResponseEntity.status(204).body("successfully deleted");
        }
    }

    @GetMapping(value = {"/api/ticket"})
    public ResponseEntity<?> getApiTicket(@RequestParam (required = false) String manufacturer, @RequestParam (required = false) String reporter) {
        TicketResponseDTO ticketResponseDTO;

        if (manufacturer != null) {
            reportService.findReportsByManufacturer(manufacturer);
            ticketResponseDTO = new TicketResponseDTO("ok", reportService.findReportsByManufacturer(manufacturer));
            return new ResponseEntity<>(ticketResponseDTO, HttpStatus.OK);
        }

        if (reporter != null) {
            reportService.findReportsByReporter(reporter);
            ticketResponseDTO = new TicketResponseDTO("ok", reportService.findReportsByReporter(reporter));
            return new ResponseEntity<>(ticketResponseDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("bla", HttpStatus.OK);
    }
}
