package br.com.associado.service;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.repository.AssociadoRepository;
import br.com.associado.validate.AssociadoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AssociadoService")
public class AssociadoService {

    private static final String ERRO_CRIAR_USUARIO = "Erro ao criar associado!";

    private final AssociadoRepository associadoRepository;
    private final AssociadoConverter associadoConverter;
    private final AssociadoValidate associadoValidate;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository,
                            AssociadoConverter associadoConverter,
                            AssociadoValidate associadoValidate) {
        this.associadoRepository = associadoRepository;
        this.associadoConverter = associadoConverter;
        this.associadoValidate = associadoValidate;
    }

    public Associado criarAssociado(AssociadoDTO dto) throws Exception {
        associadoValidate.validate(dto);
        try {
            return associadoRepository.save(associadoConverter.toModel(dto));
        } catch (Exception e) {
            throw new Exception(ERRO_CRIAR_USUARIO);
        }
    }
}
