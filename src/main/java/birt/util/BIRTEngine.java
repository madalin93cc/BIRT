package birt.util;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;

import java.util.logging.Level;

/**
 * Created by Colezea on 09/08/2015.
 */
public enum BIRTEngine {
    INSTANCE;

    private IReportEngine engine = null;

    BIRTEngine() {
        EngineConfig config = new EngineConfig();

//        for non Maven projects
//        config.setEngineHome(".../birt-runtime-4_5_0/ReportEngine");
        config.setLogConfig(null, Level.ALL);
        try {
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = factory.createReportEngine(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generatePdf(String rptName, Integer invoiceId){
        try {
            IReportRunnable iReportRunnable = engine.openReportDesign(Constants.RPT_DIR + rptName + Constants.RPT_EXT);
            IRunAndRenderTask task = engine.createRunAndRenderTask(iReportRunnable);
            task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, BIRTEngine.class.getClassLoader());
//            Set parameters
            task.setParameterValue("Invoice Id", invoiceId);
            task.setParameterValue("DB_URL", Constants.DB_URL.toString());
            task.setParameterValue("DB_Username", Constants.DB_USER.toString());
            task.setParameterValue("DB_Password", Constants.DB_PASSWORD.toString());
//            Validate parameters
            task.validateParameters();
//            Rendering tot PDF
            PDFRenderOption options = new PDFRenderOption();
            options.setOutputFileName(Constants.PDF_DIR + rptName + Constants.PDF_EXT);
            options.setOutputFormat("pdf");
            options.setEmbededFont(false);
            task.setRenderOption(options);

            //run and render report
            task.run();
            task.close();
        } catch (EngineException e){
            e.printStackTrace();
        }
    }
}
