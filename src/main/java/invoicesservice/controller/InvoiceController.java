package invoicesservice.controller;

import invoicesservice.dto.InvoiceRequestDTO;
import invoicesservice.dto.InvoiceResponseDTO;
import invoicesservice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponseDTO createInvoice(@RequestBody InvoiceRequestDTO request) {
        return service.createInvoice(request);
    }

    @GetMapping("/{id}")
    public InvoiceResponseDTO getForId(@PathVariable Integer id) {
        return service.getForId(id);
    }

    @GetMapping
    public List<InvoiceResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{invoiceType}/{serie}/{number}")
    public InvoiceResponseDTO getForSerieAndNumber(
            @PathVariable String invoiceType,
            @PathVariable String serie,
            @PathVariable Integer number) {
        return service.getForSerieAndNumber(invoiceType, serie, number);
    }
}
