package br.com.associado.converter;

import br.com.associado.bo.Associado;
import br.com.associado.dto.AssociadoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

@Component("AssociadoConverter")
public class AssociadoConverter {

    private ModelMapper mapper;

    public AssociadoConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Associado toModel(AssociadoDTO dto) {
        Assert.notNull(dto, "AssociadoDTO não pode ser nulo para a conversão!");
        return mapper.map(dto, Associado.class);
    }

    public AssociadoDTO toDTO(Associado associado) {
        Assert.notNull(associado, "Associado não pode ser nulo para a conversão");
        AssociadoDTO associadoDTO = mapper.map(associado, AssociadoDTO.class);
        return associadoDTO;
    }
}
