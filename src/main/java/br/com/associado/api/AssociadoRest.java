package br.com.associado.api;

import br.com.associado.dto.AssociadoDTO;
import br.com.associado.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/associado/")
public class AssociadoRest {

    private final AssociadoService associadoService;

    @Autowired
    public AssociadoRest(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @ApiOperation(value = "Salvar associado", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Associado criado com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado")
    })
    @PostMapping
    public ResponseEntity<String> criarAssociado(@RequestBody AssociadoDTO dto) {
        return ResponseEntity.ok(associadoService.adicionaAssociado(dto));
    }
}
