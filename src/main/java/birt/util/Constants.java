package birt.util;

/**
 * Created by Madalin.Colezea on 8/10/2015.
 */
public enum Constants {
    PDF_DIR("Pdf/"),
    PDF_EXT(".pdf"),
    RPT_DIR("Rptdesign/"),
    RPT_EXT(".rptdesign"),
    DB_PRE("jdbc:h2:file:"),
    DB_SUF("/H2/BIRT;AUTO_SERVER=TRUE"),
    DB_USER("root"),
    DB_PASSWORD("");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
