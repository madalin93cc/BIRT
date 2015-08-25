package birt.dto;

/**
 * Report DTO
 */
public class ReportDTO {
    private Integer id;
    private String name;

    public ReportDTO() {
    }

    public ReportDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (o == null || getClass() != o.getClass()) return false;

        ReportDTO reportDTO = (ReportDTO) o;

        if (!id.equals(reportDTO.id)) return false;
        return name.equals(reportDTO.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
