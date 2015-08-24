package birt.repository;

import birt.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Madalin.Colezea on 8/24/2015.
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select inv.id from Invoice inv")
    List<Long> getAllIds();
}
