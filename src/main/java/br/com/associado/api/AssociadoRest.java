package br.com.associado.api;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoRest {

    private final AssociadoService associadoService;
    private final AssociadoConverter associadoConverter;

    @Autowired
    public AssociadoRest(AssociadoService associadoService,
                         AssociadoConverter associadoConverter) {
        this.associadoService = associadoService;
        this.associadoConverter = associadoConverter;
    }

    @PostMapping("/")
    public ResponseEntity<Associado> criarAssociado(@RequestBody AssociadoDTO dto) throws Exception {
        return ResponseEntity.ok(associadoService.criarAssociado(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Associado>> listarAssociados() throws Exception {
        return ResponseEntity.ok(associadoService.listarAssociados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Associado> buscarAssociado(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(associadoService.buscarAssociado(id));
    }

}
