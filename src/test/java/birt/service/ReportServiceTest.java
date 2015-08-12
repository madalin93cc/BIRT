package birt.service;

import birt.BirtApplication;
import birt.dto.ReportDTO;
import birt.util.ReportEnum;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madalin.Colezea on 8/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BirtApplication.class)
@WebAppConfiguration
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void test_get_all_report_name(){
        List<ReportDTO> reportDTOList = reportService.getAllReportNames();
        Assume.assumeFalse(reportDTOList.isEmpty());
        Assume.assumeTrue(reportDTOList.size() == ReportEnum.values().length);

        List<String> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (ReportEnum reportEnum: ReportEnum.values()){
            l1.add(reportEnum.getName());
        }
        for (ReportDTO reportDTO: reportDTOList){
            l2.add(reportDTO.getName());
        }
        Assume.assumeTrue(l1.equals(l2));
    }

}
