package br.com.associado.service;

import br.com.associado.bo.Associado;
import br.com.associado.converter.AssociadoConverter;
import br.com.associado.dto.AssociadoDTO;
import br.com.associado.error.ErroInternoException;
import br.com.associado.error.ObjetoNaoEncontradoException;
import br.com.associado.repository.AssociadoRepository;
import br.com.associado.validate.AssociadoValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("AssociadoService")
public class AssociadoService {

    Logger logger = LoggerFactory.getLogger(AssociadoService.class);

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
            logger.info("Salvando associado.");
            return associadoRepository.save(associadoConverter.toModel(dto));
        } catch (Exception e) {
            logger.error("Erro ao salvar associado: " + dto.getCpf());
            throw new ErroInternoException(ERRO_CRIAR_USUARIO);
        }
    }

    public List<AssociadoDTO> listarAssociados() throws Exception {
        try {
            List<AssociadoDTO> listaDTO = new ArrayList<>();
            logger.info("Buscando lista de associados.");
            associadoRepository.findAll().forEach(a -> adicionaLista(listaDTO, a));
            return listaDTO;
        } catch (Exception e) {
            logger.error("Erro ao buscar lista de associados.");
            throw new ErroInternoException(ERRO_LISTAR_ASSOCIADOS);
        }
    }

    private void adicionaLista(List<AssociadoDTO> listaDTO, Associado a) {
        listaDTO.add(associadoConverter.toDTO(a));
    }

    public Associado buscarAssociado(String id) {
        try {
            logger.info("Buscando associado: " + id);
            return associadoRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            logger.error("Associado não encontrado: " + id);
            throw new ObjetoNaoEncontradoException(ERRO_ASSOCIADO_NAO_ENCONTRADO);
        } catch (Exception e) {
            logger.error("Erro ao buscar associado: " + id);
            throw new ErroInternoException(ERRO_BUSCAR_ASSOCIADO);
        }
    }

    private void validaDuplicidade(AssociadoDTO dto) {
        Optional<Associado> associado;
        try {
            logger.info("Buscando associado por cpf: " + dto.getCpf());
             associado = associadoRepository.findByCpf(dto.getCpf());
        } catch (Exception e) {
            logger.error("Erro ao buscar associado: " + dto.getCpf());
            throw new ErroInternoException(ERRO_BUSCAR_ASSOCIADO);
        }

        if (associado.isPresent()) {
            logger.error("Associado já cadastrado: " + dto.getCpf());
            throw new ErroInternoException(ERRO_ASSOCIADO_JA_CADASTRADO);
        }
    }

}
