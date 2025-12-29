package invoicesservice.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emisor")
public class Emisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String ruc;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column
    private String ubigeo;

    @Column
    private String direccion;

    @Column
    private String departamento;

    @Column
    private String provincia;

    @Column
    private String distrito;

}
