package invoicesservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceResponseDTO {

    public Integer id;
    public String uuid;
    public String tipoComprobante;
    public String serie;
    public Integer numero;
    public LocalDate fechaEmision;
    public BigDecimal total;
    public String estadoSunat;

}
