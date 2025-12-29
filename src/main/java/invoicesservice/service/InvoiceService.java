package invoicesservice.service;

import invoicesservice.dto.InvoiceRequestDTO;
import invoicesservice.dto.InvoiceResponseDTO;
import invoicesservice.domain.entity.Comprobante;
import invoicesservice.domain.entity.ComprobanteDetalle;
import invoicesservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO request) {

        Comprobante comprobante = new Comprobante();
        comprobante.setTipoComprobante(request.tipoComprobante);
        comprobante.setSerie(request.serie);
        comprobante.setNumero(request.numero);
        comprobante.setFechaEmision(request.fechaEmision);
        comprobante.setHoraEmision(LocalTime.now());
        comprobante.setMoneda(request.moneda);

        comprobante.setTipoDocCliente(request.tipoDocCliente);
        comprobante.setNroDocCliente(request.nroDocCliente);
        comprobante.setRazonSocialCliente(request.razonSocialCliente);

        comprobante.setTotalGravado(request.totalGravado);
        comprobante.setTotalIgv(request.totalIgv);
        comprobante.setTotal(request.total);

        comprobante.setEstadoSunat("01"); // REGISTRADO

        comprobante.setDetalles(
                request.detalles.stream().map(d -> {
                    ComprobanteDetalle det = new ComprobanteDetalle();
                    det.setComprobante(comprobante);
                    det.setDescripcion(d.descripcion);
                    det.setCantidad(d.cantidad);
                    det.setValorUnitario(d.valorUnitario);
                    det.setPrecioUnitario(d.precioUnitario);
                    det.setTipoAfectacionIgv(d.tipoAfectacionIgv);
                    return det;
                }).collect(Collectors.toList())
        );

        Comprobante guardado = repository.save(comprobante);

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        response.id = guardado.getId();
        response.uuid = guardado.getUuid();
        response.tipoComprobante = guardado.getTipoComprobante();
        response.serie = guardado.getSerie();
        response.numero = guardado.getNumero();
        response.fechaEmision = guardado.getFechaEmision();
        response.total = guardado.getTotal();
        response.estadoSunat = guardado.getEstadoSunat();

        return response;
    }

    @Transactional(readOnly = true)
    public InvoiceResponseDTO getForId(Integer id) {
        Comprobante comprobante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante no encontrado"));

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        response.id = comprobante.getId();
        response.uuid = comprobante.getUuid();
        response.tipoComprobante = comprobante.getTipoComprobante();
        response.serie = comprobante.getSerie();
        response.numero = comprobante.getNumero();
        response.fechaEmision = comprobante.getFechaEmision();
        response.total = comprobante.getTotal();
        response.estadoSunat = comprobante.getEstadoSunat();

        return response;
    }

    @Transactional(readOnly = true)
    public List<InvoiceResponseDTO> getAll() {
        List<Comprobante> comprobantes = repository.findAll();

        return comprobantes.stream().map(comprobante -> {
            InvoiceResponseDTO response = new InvoiceResponseDTO();
            response.id = comprobante.getId();
            response.uuid = comprobante.getUuid();
            response.tipoComprobante = comprobante.getTipoComprobante();
            response.serie = comprobante.getSerie();
            response.numero = comprobante.getNumero();
            response.fechaEmision = comprobante.getFechaEmision();
            response.total = comprobante.getTotal();
            response.estadoSunat = comprobante.getEstadoSunat();
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InvoiceResponseDTO getForSerieAndNumber(String invoiceType, String serie, Integer number) {
        Comprobante comprobante = repository.findByTipoComprobanteAndSerieAndNumero(invoiceType, serie, number)
                .orElseThrow(() -> new RuntimeException("Comprobante no encontrado"));

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        response.id = comprobante.getId();
        response.uuid = comprobante.getUuid();
        response.tipoComprobante = comprobante.getTipoComprobante();
        response.serie = comprobante.getSerie();
        response.numero = comprobante.getNumero();
        response.fechaEmision = comprobante.getFechaEmision();
        response.total = comprobante.getTotal();
        response.estadoSunat = comprobante.getEstadoSunat();

        return response;
    }



}
