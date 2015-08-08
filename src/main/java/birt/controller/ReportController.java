package birt.controller;

import birt.service.ReportService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
        String filename = "CV_Colezea_Madalin.pdf";
        FileInputStream fileInputStream = new FileInputStream(new File(filename));
        byte [] content = IOUtils.toByteArray(fileInputStream);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));

        httpHeaders.setContentDispositionFormData(filename, filename);
//        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        byte [] fileAsArray = reportService.generateReport(reportId);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(content, httpHeaders, HttpStatus.OK);
        return  responseEntity;
    }

    @RequestMapping(value = "/downloadReport",method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("CV_Colezea_Madalin.pdf"));
        byte [] content = IOUtils.toByteArray(fileInputStream);

        streamReport(response, content, "my_report.pdf");
    }

    protected void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}
