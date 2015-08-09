package birt.controller;

import birt.dto.ReportDTO;
import birt.service.ReportService;
import birt.util.ReportEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/getAllReportNames", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ReportDTO> getAllReportNames(){
        List<ReportDTO> reportDTOs = new ArrayList<>();
        for (ReportEnum reportEnum: ReportEnum.values()){
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(reportEnum.getId());
            reportDTO.setName(reportEnum.getName());
            reportDTOs.add(reportDTO);
        }
        return reportDTOs;
    }

}
