package invoicesservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "comprobante_detalle")
public class ComprobanteDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    @Column(name = "codigo_producto")
    private String codigoProducto;

    @Column
    private String descripcion;

    @Column
    private BigDecimal cantidad;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "tipo_afectacion_igv")
    private String tipoAfectacionIgv; // Catálogo 07

    @Column(name = "porcentaje_igv")
    private BigDecimal porcentajeIgv;

    @Column
    private BigDecimal igv;

    @Column(name = "total_linea")
    private BigDecimal totalLinea;

}
