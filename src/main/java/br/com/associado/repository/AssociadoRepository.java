package br.com.associado.repository;

import br.com.associado.bo.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("AssociadoRepository")
public interface AssociadoRepository extends MongoRepository<Associado, String> {

}
