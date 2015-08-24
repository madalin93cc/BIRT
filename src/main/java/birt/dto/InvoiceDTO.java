package birt.dto;

/**
 * Created by Madalin.Colezea on 8/24/2015.
 */
public class InvoiceDTO {
    private Long id;

    private String name;

    public InvoiceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public InvoiceDTO(Long id) {
        this.id = id;
        this.name = "Invoice nr." + this.id;
    }

    public InvoiceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceDTO)) return false;

        InvoiceDTO that = (InvoiceDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
