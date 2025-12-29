package invoicesservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comprobante",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tipo_comprobante", "serie", "numero"})
        })
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 36)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "tipo_comprobante")
    private String tipoComprobante; // 01, 03, 07, 08

    @Column
    private String serie;

    @Column
    private Integer numero;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "hora_emision")
    private LocalTime horaEmision;

    @Column
    private String moneda; // PEN, USD

    @Column(name = "tipo_doc_cliente")
    private String tipoDocCliente; // Catálogo 06

    @Column(name = "nro_doc_cliente")
    private String nroDocCliente;

    @Column(name = "razon_social_cliente")
    private String razonSocialCliente;

    @Column(name = "total_gravado")
    private BigDecimal totalGravado;

    @Column(name = "total_exonerado")
    private BigDecimal totalExonerado;

    @Column(name = "total_inafecto")
    private BigDecimal totalInafecto;

    @Column(name = "total_igv")
    private BigDecimal totalIgv;

    @Column
    private BigDecimal total;

    @Column(name = "estado_sunat")
    private String estadoSunat; // 01-06

    @Column(name = "respuesta_sunat")
    private String respuestaSunat;

    @Column(name = "hash_cpe")
    private String hashCpe;

    @Column(name = "qr_cpe")
    private String qrCpe;

    /* ================= Relaciones ================= */

    @OneToMany(
            mappedBy = "comprobante",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ComprobanteDetalle> detalles;

    @OneToMany(
            mappedBy = "comprobante",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ComprobanteImpuesto> impuestos;

    /* ================= Auditoría ================= */

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column
    private Boolean activo = true;

    /* ================= Callbacks ================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
