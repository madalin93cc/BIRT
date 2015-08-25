package birt.util;

/**
 * Enum cu rapoartele disponibile
 */
public enum ReportEnum {

    CUSTOMERS(1, "CUSTOMERS", "Customers"),
    TEMPLATE_ATLAS(2, "TEMPLATE_ATLAS", "Factura Atlas");

    private Integer id;
    private String code;
    private String name;

    ReportEnum(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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
