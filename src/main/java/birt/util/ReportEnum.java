package birt.util;

/**
 * Enum cu rapoartele disponibile
 */
public enum ReportEnum {

    CUSTOMERS(1, "CUSTOMERS", "Customers", false),
    TEMPLATE_ATLAS(2, "TEMPLATE_ATLAS", "Factura Atlas", true),
    TEMPLATE_ATLAS_POJO(3, "TEMPLATE_ATLAS_POJP", "Factura Atlas POJO", false);

    private Integer id;
    private String code;
    private String name;
    private Boolean requireDB;

    ReportEnum(Integer id, String code, String name, Boolean requireDB) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.requireDB = requireDB;
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

    public static Boolean requireDBByName(String name){
        for (ReportEnum reportEnum: ReportEnum.values()){
            if (reportEnum.name.equals(name)){
                return reportEnum.requireDB;
            }
        }
        return false;
    }
}
