package birt.service;

import birt.dto.ReportDTO;
import birt.util.BIRTEngine;
import birt.util.Constants;
import birt.util.ReportEnum;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Colezea on 08/08/2015.
 */
@Service
public class ReportServiceImpl implements ReportService{

    public ResponseEntity<byte[]> generateReport(Integer reportId) throws FileNotFoundException{
        String filename = ReportEnum.getFilenameById(reportId);
        if (filename == null) throw new FileNotFoundException();
        BIRTEngine.INSTANCE.generatePdf(filename);
        byte [] content = null;
        try {
            filename = Constants.PDF_DIR + filename + Constants.PDF_EXT;
            FileInputStream fileInputStream = new FileInputStream(new File(filename));
            content = IOUtils.toByteArray(fileInputStream);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));

        httpHeaders.setContentDispositionFormData(filename, filename);
//        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(content, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    public List<ReportDTO> getAllReportNames(){
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
