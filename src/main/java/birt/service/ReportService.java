package birt.service;

import org.eclipse.birt.report.engine.util.FileUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Colezea on 08/08/2015.
 */
@Service
public class ReportService {

    public byte[] generateReport(Integer reportId) {
        return FileUtil.getFileContent("CV_Colezea_Madalin.pdf");
    }
}
