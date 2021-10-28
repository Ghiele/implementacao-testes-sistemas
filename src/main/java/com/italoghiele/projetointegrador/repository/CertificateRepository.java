package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.Certificate;
import org.springframework.data.repository.CrudRepository;

public interface CertificateRepository extends CrudRepository <Certificate, Long> {
}
