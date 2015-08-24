package birt.domain;

import javax.persistence.*;

/**
 * Created by Colezea on 19/08/2015.
 */
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "UNIT", nullable = false)
    private Integer unit;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "UNIT_PRICE", nullable = false, precision = 2)
    private Double unitPrice;

    @Column(name = "VAT", nullable = false, precision = 2)
    private Double vat;

//    @Column(name = "VALUE", nullable = false, precision = 2)
//    private Double value;
//
//    @Column(name = "VAT_VALUE", nullable = false, precision = 2)
//    private Double vatValue;
//
//    @Column(name = "TOTAL", nullable = false, precision = 2)
//    private Double total;

    @ManyToOne
    @JoinColumn(name = "INVOICE")
    private Invoice fkInvoice;

    public Service() {
    }

    public Service(String name, Integer unit, Integer quantity, Double unitPrice, Double vat, Double value, Double vatValue, Double total, Invoice invoice) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.vat = vat;
//        this.value = value;
//        this.vatValue = vatValue;
//        this.total = total;
        this.fkInvoice = invoice;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

//    public Double getValue() {
//        return value;
//    }
//
//    public void setValue(Double value) {
//        this.value = value;
//    }
//
//    public Double getVatValue() {
//        return vatValue;
//    }
//
//    public void setVatValue(Double vatValue) {
//        this.vatValue = vatValue;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }

    public Invoice getInvoice() {
        return fkInvoice;
    }

    public void setInvoice(Invoice invoice) {
        this.fkInvoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;

        Service service = (Service) o;

        if (!id.equals(service.id)) return false;
        if (!name.equals(service.name)) return false;
        if (!unit.equals(service.unit)) return false;
        if (!quantity.equals(service.quantity)) return false;
        if (!unitPrice.equals(service.unitPrice)) return false;
        return vat.equals(service.vat);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + unit.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + unitPrice.hashCode();
        result = 31 * result + vat.hashCode();
        return result;
    }
}
