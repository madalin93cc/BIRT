package birt.util;

/**
 * Constante
 */
public enum Constants {
    PDF_DIR("Pdf/"),
    PDF_EXT(".pdf"),
    RPT_DIR("Rptdesign/"),
    RPT_EXT(".rptdesign"),
    DB_URL("jdbc:mysql://localhost:3306/birt"),
    DB_USER("root"),
    DB_INIT_SCRIPT("/src/populate.sql"),
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
