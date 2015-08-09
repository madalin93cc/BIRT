package birt.util;

/**
 * Created by Colezea on 09/08/2015.
 */
public enum ReportEnum {

    FACTURA(1, "FACTURA", "Factura.rptdesign"),
    REPORT(2, "REPORT", "CV_Colezea_Madalin.pdf");

    private Integer id;
    private String code;
    private String name;

    ReportEnum(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public static String getFilenameById(Integer id){
        for (ReportEnum reportEnum: ReportEnum.values()){
            if (reportEnum.id.equals(id)){
                return reportEnum.name;
            }
        }
        return null;
    }
}
