package br.com.associado.service;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AssociadoService")
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    private final AssociadoConverter associadoConverter;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository,
                            AssociadoConverter associadoConverter) {
        this.associadoRepository = associadoRepository;
        this.associadoConverter = associadoConverter;
    }

    public String adicionaAssociado(AssociadoDTO dto) {
        Associado associado = associadoRepository.save(associadoConverter.toModel(dto));
        return associado.getId().toString();
    }
}
