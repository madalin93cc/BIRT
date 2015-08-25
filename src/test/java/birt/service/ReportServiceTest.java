package birt.service;

import birt.BirtApplication;
import birt.dto.InvoiceDTO;
import birt.dto.ReportDTO;
import birt.repository.InvoiceRepository;
import birt.util.ReportEnum;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Mock
    InvoiceRepository invoiceRepositoryMock = Mockito.mock(InvoiceRepository.class);

    @Rule
    public TestName testName = new TestName();

    List<InvoiceDTO> invoiceDTOs;
    List<Long> longs;

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

    @Before
    public void setup(){
        if (testName.getMethodName().equals("test_get_all_invoices")) {
            longs = new ArrayList<>();
            longs.add(1L);
            longs.add(2L);
            invoiceDTOs = new ArrayList<>();
            invoiceDTOs.add(new InvoiceDTO(1L));
            invoiceDTOs.add(new InvoiceDTO(2L));

            Mockito.when(invoiceRepositoryMock.getAllIds()).thenReturn(longs);
        }
    }

    @Test
    public void test_get_all_invoices(){
        Assume.assumeTrue(reportService.getAllInvoices().equals(invoiceDTOs));
    }

}
