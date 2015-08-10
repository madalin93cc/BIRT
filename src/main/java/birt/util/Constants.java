package birt.util;

/**
 * Created by Madalin.Colezea on 8/10/2015.
 */
public enum Constants {
    PDF_DIR("Pdf/"),
    PDF_EXT(".pdf"),
    RPT_DIR("Rptdesign/"),
    RPT_EXT(".rptdesign");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
