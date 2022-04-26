package com.greenfox.errorreporter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String reporter;

    @Column(nullable = false)
    private String manufacturer;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creation_date;

    public Report(String reporter, String manufacturer, String serialNumber, String description) {
        this.reporter = reporter;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.description = description;
        this.creation_date = LocalDate.now();
    }
}
