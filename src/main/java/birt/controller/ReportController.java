package birt.controller;

import birt.dto.InvoiceDTO;
import birt.dto.ReportDTO;
import birt.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Colezea on 08/08/2015.
 */
@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

    @RequestMapping(value = "/downloadReport/{reportId}/{invoiceId}", method = RequestMethod.GET, produces="application/pdf")
    public ResponseEntity<byte[]> downloadReport(@PathVariable("reportId") Integer reportId, @PathVariable("invoiceId") Integer invoiceId) throws IOException{
        return  reportService.generateReport(reportId, invoiceId);
    }

    @RequestMapping(value = "/getAllReportNames", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ReportDTO> getAllReportNames(){
        return reportService.getAllReportNames();
    }

    @RequestMapping(value = "/getAllInvoices", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<InvoiceDTO> getAllInvoices(){
        return reportService.getAllInvoices();
    }
}
