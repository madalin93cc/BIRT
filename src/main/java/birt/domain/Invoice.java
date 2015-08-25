package birt.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Invoice JPA Entity
 */
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    private User fkProvider;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private User fkCustomer;

    @OneToMany(mappedBy = "fkInvoice")
    private List<Service> fkServices;

    public Invoice() {
    }

    public Invoice(Date date, Integer number, User fkProvider, User fkCustomer) {
        this.date = date;
        this.number = number;
        this.fkProvider = fkProvider;
        this.fkCustomer = fkCustomer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getFkProvider() {
        return fkProvider;
    }

    public void setFkProvider(User fkProvider) {
        this.fkProvider = fkProvider;
    }

    public User getFkCustomer() {
        return fkCustomer;
    }

    public void setFkCustomer(User fkCustomer) {
        this.fkCustomer = fkCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (!id.equals(invoice.id)) return false;
        if (!date.equals(invoice.date)) return false;
        if (!number.equals(invoice.number)) return false;
        if (!fkProvider.equals(invoice.fkProvider)) return false;
        return fkCustomer.equals(invoice.fkCustomer);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + fkProvider.hashCode();
        result = 31 * result + fkCustomer.hashCode();
        return result;
    }
}
