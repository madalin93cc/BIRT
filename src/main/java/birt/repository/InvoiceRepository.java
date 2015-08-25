package birt.repository;

import birt.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository pentru Invoice
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    /**
     * Metoda ce aduce toate id-urile de invoice
     * @return lista id-uri
     */
    @Query("select inv.id from Invoice inv")
    List<Long> getAllIds();
}
