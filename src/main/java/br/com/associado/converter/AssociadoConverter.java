package br.com.associado.converter;

import br.com.associado.bo.Associado;
import br.com.associado.dto.AssociadoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

@Component("AssociadoConverter")
public class AssociadoConverter {

    private static final String ERRO_CONVERSAO_ASSOCIADO = "Erro ao converter associado!";
    private ModelMapper mapper;

    public AssociadoConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Associado toModel(AssociadoDTO dto) {
        Assert.notNull(dto, ERRO_CONVERSAO_ASSOCIADO);
        return mapper.map(dto, Associado.class);
    }

    public AssociadoDTO toDTO(Associado associado) {
        Assert.notNull(associado, ERRO_CONVERSAO_ASSOCIADO);
        return AssociadoDTO.builder()
                           .id(associado.getId().toString())
                           .cpf(associado.getCpf())
                           .nome(associado.getNome()).build();
    }

}
