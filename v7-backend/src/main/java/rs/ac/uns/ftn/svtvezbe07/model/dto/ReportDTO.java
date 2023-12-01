package rs.ac.uns.ftn.svtvezbe07.model.dto;

import rs.ac.uns.ftn.svtvezbe07.model.entity.Report;
import rs.ac.uns.ftn.svtvezbe07.model.entity.ReportReason;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportDTO {

    private Long id;
    @NotNull
    private ReportReason reportReason;
    @NotNull
    private LocalDate timestamp;

    @NotNull
    private User byUser;
    private Boolean accepted;

    public ReportDTO (Report report) {
        this.id = report.getId();
        this.reportReason = report.getReportReason();
        this.timestamp = report.getTimestamp();
        this.accepted = report.getAccepted();
    }

    public ReportDTO () {}

}
