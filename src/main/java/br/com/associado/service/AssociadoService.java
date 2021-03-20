package br.com.associado.service;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.error.ErroInternoException;
import br.com.associado.error.ObjetoNaoEncontradoException;
import br.com.associado.repository.AssociadoRepository;
import br.com.associado.validate.AssociadoValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("AssociadoService")
public class AssociadoService {

    private static final String ERRO_CRIAR_USUARIO = "Erro ao criar associado!";
    private static final String ERRO_LISTAR_ASSOCIADOS = "Erro ao listar associados!";
    private static final String ERRO_BUSCAR_ASSOCIADO = "Erro ao buscar associado!";
    private static final String ERRO_ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado!";
    private static final String ERRO_ASSOCIADO_JA_CADASTRADO = "Associado já cadastrado!";

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
        validaDuplicidade(dto);
        try {
            return associadoRepository.save(associadoConverter.toModel(dto));
        } catch (Exception e) {
            throw new ErroInternoException(ERRO_CRIAR_USUARIO);
        }
    }

    public List<Associado> listarAssociados() throws Exception {
        try {
            return associadoRepository.findAll();
        } catch (Exception e) {
            throw new ErroInternoException(ERRO_LISTAR_ASSOCIADOS);
        }
    }

    public Associado buscarAssociado(String id) {
        try {
            return associadoRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ObjetoNaoEncontradoException(ERRO_ASSOCIADO_NAO_ENCONTRADO);
        } catch (Exception e) {
            throw new ErroInternoException(ERRO_BUSCAR_ASSOCIADO);
        }
    }

    private void validaDuplicidade(AssociadoDTO dto) {
        Optional<Associado> associado;
        try {
             associado = associadoRepository.findByCpf(dto.getCpf());
        } catch (Exception e) {
            throw new ErroInternoException(ERRO_BUSCAR_ASSOCIADO);
        }

        if (associado.isPresent()) {
            throw new ErroInternoException(ERRO_ASSOCIADO_JA_CADASTRADO);
        }
    }

}
