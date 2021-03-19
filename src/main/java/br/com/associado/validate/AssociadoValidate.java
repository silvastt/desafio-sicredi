package br.com.associado.validate;

import br.com.associado.dto.AssociadoDTO;
import br.com.associado.error.ErroInternoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("AssociadoValidate")
public class AssociadoValidate {

    private static final String NOME_ASSOCIADO_VAZIO = "Nome do Associado não informado!";
    private static final String CPF_ASSOCIADO_VAZIO = "CPF do Associado não informado!";
    private static final String ASSOCIADO_VAZIO = "Dados de associado não informados!";

    public AssociadoValidate() {}

    public void validate(AssociadoDTO dto) throws Exception {
        if (Objects.isNull(dto)) {
            throw new ErroInternoException(ASSOCIADO_VAZIO);
        }

        if (StringUtils.isEmpty(dto.getNome())) {
            throw new ErroInternoException(NOME_ASSOCIADO_VAZIO);
        }

        if (StringUtils.isEmpty(dto.getCpf())) {
            throw new ErroInternoException(CPF_ASSOCIADO_VAZIO);
        }
    }
}
