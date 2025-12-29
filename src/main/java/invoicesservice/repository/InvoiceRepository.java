package invoicesservice.repository;

import invoicesservice.domain.entity.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Comprobante, Integer> {

    Optional<Comprobante> findByTipoComprobanteAndSerieAndNumero(
            String tipoComprobante,
            String serie,
            Integer numero
    );

}
