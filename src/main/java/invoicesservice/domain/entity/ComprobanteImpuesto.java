package invoicesservice.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "comprobante_impuesto")
public class ComprobanteImpuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    @Column(name = "tipo_impuesto")
    private String tipoImpuesto; // 1000

    @Column(name = "codigo_tributo")
    private String codigoTributo;

    @Column(name = "nombre_tributo")
    private String nombreTributo;

    @Column
    private BigDecimal monto;

}
