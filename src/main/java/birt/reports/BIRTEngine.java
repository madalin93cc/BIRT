package birt.reports;

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

        config.setEngineHome("D:/Tools/birt-runtime-4_5_0/ReportEngine");
        config.setLogConfig(null, Level.ALL);
        try {
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = factory.createReportEngine(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generatePdf(String rptName){
        try {
            IReportRunnable iReportRunnable = engine.openReportDesign(rptName + ".rptdesign");
            IRunAndRenderTask task = engine.createRunAndRenderTask(iReportRunnable);
            task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, TestBIRT.class.getClassLoader());
            //Set parameter values and validate
            task.setParameterValue("Top Percentage", (new Integer(3)));
            task.setParameterValue("Top Count", (new Integer(5)));
            task.validateParameters();

            //Setup rendering to HTML
//            HTMLRenderOption options = new HTMLRenderOption();
//            options.setOutputFileName("Customers.html");
//            options.setOutputFormat("html");

            PDFRenderOption options = new PDFRenderOption();
            options.setOutputFileName(rptName + ".pdf");
            options.setOutputFormat("pdf");
            options.setEmbededFont(false);
            //Setting this to true removes html and body tags
//            options.setEmbeddable(false);
            task.setRenderOption(options);

            //run and render report
            task.run();
            task.close();
        } catch (EngineException e){
            e.printStackTrace();
        }
    }
}
