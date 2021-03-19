package br.com.associado.api;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
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
    private final AssociadoConverter associadoConverter;

    @Autowired
    public AssociadoRest(AssociadoService associadoService,
                         AssociadoConverter associadoConverter) {
        this.associadoService = associadoService;
        this.associadoConverter = associadoConverter;
    }

    @ApiOperation(value = "Salvar associado", response = AssociadoDTO.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Associado criado com sucesso"),
            @ApiResponse(code = 500, message = "Erro inesperado")
    })
    @PostMapping
    public ResponseEntity<Associado> criarAssociado(@RequestBody AssociadoDTO dto) throws Exception {
        return ResponseEntity.ok(associadoService.criarAssociado(dto));
    }
}
