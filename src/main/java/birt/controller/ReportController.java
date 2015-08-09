package birt.controller;

import birt.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Colezea on 08/08/2015.
 */
@Controller
public class ReportController {
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/downloadReport/{reportId}", method = RequestMethod.GET, produces="application/pdf")
    public ResponseEntity<byte[]> downloadReport(@PathVariable("reportId") Integer reportId) throws IOException{
        return  reportService.generateReport(reportId);
    }

}
