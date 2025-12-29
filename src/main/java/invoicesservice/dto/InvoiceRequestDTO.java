package invoicesservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceRequestDTO {

    public String tipoComprobante;
    public String serie;
    public Integer numero;
    public LocalDate fechaEmision;
    public String moneda;

    public String tipoDocCliente;
    public String nroDocCliente;
    public String razonSocialCliente;

    public BigDecimal totalGravado;
    public BigDecimal totalIgv;
    public BigDecimal total;

    public List<InvoiceDetailDTO> detalles;

}
