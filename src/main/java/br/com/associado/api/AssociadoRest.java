package br.com.associado.api;

import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "Cria um associado.", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Associado criado com sucesso."),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @PostMapping("/")
    public ResponseEntity<AssociadoDTO> criarAssociado(@RequestBody AssociadoDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(associadoConverter.toDTO(associadoService.criarAssociado(dto)));
    }

    @ApiOperation(value = "Lista todos os associados.", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 422, message = "Erro ao processar dados de entrada"),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<AssociadoDTO>> listarAssociados() throws Exception {
        return ResponseEntity.ok(associadoService.listarAssociados());
    }

    @ApiOperation(value = "Busca associado por ID.", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 422, message = "Erro ao processar dados de entrada"),
            @ApiResponse(code = 500, message = "Erro inesperado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> buscarAssociado(@PathVariable String id) {
        return ResponseEntity.ok(associadoConverter.toDTO(associadoService.buscarAssociado(id)));
    }

}
