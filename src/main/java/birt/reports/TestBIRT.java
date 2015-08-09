package birt.reports;

import com.ibm.icu.util.ULocale;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.engine.util.FileUtil;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.activity.SemanticException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Simple BIRT Design Engine API (DEAPI) demo.
 */
public class TestBIRT {
    public static void main(String[] args) {
        try {
            FileUtil.saveFile(new File("exp.pdf"), FileUtil.getFileContent("CV_Colezea_Madalin.pdf"));

//            buildReport();
//            generateReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void generateReport(){
        //Configure the Engine and start the Platform
        EngineConfig config = new EngineConfig();

        config.setEngineHome("D:/Tools/birt-runtime-4_5_0/ReportEngine");
        config.setLogConfig(null, Level.ALL);
        IReportEngine engine = null;
        try {
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = factory.createReportEngine(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            IReportRunnable iReportRunnable = engine.openReportDesign("Customers.rptdesign");
            IRunAndRenderTask task = engine.createRunAndRenderTask(iReportRunnable);
            task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, TestBIRT.class.getClassLoader());
            //Set parameter values and validate
            task.setParameterValue("Top Percentage", (new Integer(3)));
            task.setParameterValue("Top Count", (new Integer(5)));
            task.validateParameters();

            //Setup rendering to HTML
            HTMLRenderOption options = new HTMLRenderOption();
            options.setOutputFileName("Customers.html");
            options.setOutputFormat("html");

            //Setting this to true removes html and body tags
            options.setEmbeddable(false);
            task.setRenderOption(options);

            //run and render report
            task.run();
            task.close();
        } catch (EngineException e){
            e.printStackTrace();
        }

    }

    // This method shows how to build a very simple BIRT report with a
    // minimal set of content: a simple grid with an image and a label.

    static void buildReport() throws IOException, SemanticException {
        // Create a session handle. This is used to manage all open designs.
        // Your app need create the session only once.

        //Configure the Engine and start the Platform
        DesignConfig config = new DesignConfig();

        config.setProperty("BIRT_HOME", "D:/Tools/birt-runtime-4_5_0/ReportEngine");
        IDesignEngine engine = null;
        try {
            Platform.startup(config);
            IDesignEngineFactory factory = (IDesignEngineFactory) Platform.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
            engine = factory.createDesignEngine(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SessionHandle session = engine.newSessionHandle(ULocale.ENGLISH);

        // Create a new report design.
        ReportDesignHandle design = session.createDesign();

        // The element factory creates instances of the various BIRT elements.
        ElementFactory efactory = design.getElementFactory();

        // Create a simple master page that describes how the report will appear when printed.
        //
        // Note: The report will fail to load in the BIRT designer unless you create a master page.
        DesignElementHandle element = efactory.newSimpleMasterPage("Page Master");
        design.getMasterPages().add(element);

        // Create a grid and add it to the "body" slot of the report design.
        GridHandle grid = efactory.newGridItem(null, 2 /* cols */, 1 /* row */);
        design.getBody().add(grid);

        // Note: Set the table width to 100% to prevent the label
        // from appearing too narrow in the layout view.
        grid.setWidth("100%");

        // Get the first row.
        RowHandle row = (RowHandle) grid.getRows().get(0);

        // Create an image and add it to the first cell.
        ImageHandle image = efactory.newImage(null);
        CellHandle cell = (CellHandle) row.getCells().get(0);
        cell.getContent().add(image);
        image.setURL("\"urlofimage\"");

        // Create a label and add it to the second cell.
        LabelHandle label = efactory.newLabel(null);
        cell = (CellHandle) row.getCells().get(1);
        cell.getContent().add(label);
        label.setText("Hello, world!");

        // Save the design and close it.
        design.saveAs("sample.rptdesign");
        design.close();
        System.out.println("Finished");

        // We're done!
    }
}
