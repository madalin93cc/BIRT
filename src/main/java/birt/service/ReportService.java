package birt.service;

import birt.dto.InvoiceDTO;
import birt.dto.ReportDTO;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

/**
 * Report service
 */
public interface ReportService {
    ResponseEntity<byte[]> generateReport(Integer reportId, Integer invoiceId) throws FileNotFoundException;

    List<ReportDTO> getAllReportNames();

    List<InvoiceDTO> getAllInvoices();
}
