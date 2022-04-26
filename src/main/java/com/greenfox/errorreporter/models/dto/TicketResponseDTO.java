package com.greenfox.errorreporter.models.dto;

import com.greenfox.errorreporter.models.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private String result;
    private List<Report> tickets;
}
