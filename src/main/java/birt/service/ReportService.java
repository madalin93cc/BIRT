package birt.service;

import birt.dto.ReportDTO;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Madalin.Colezea on 8/10/2015.
 */
public interface ReportService {
    ResponseEntity<byte[]> generateReport(Integer reportId) throws FileNotFoundException;

    List<ReportDTO> getAllReportNames();
}
