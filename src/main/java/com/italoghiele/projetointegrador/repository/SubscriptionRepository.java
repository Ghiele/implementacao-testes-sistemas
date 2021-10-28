package com.italoghiele.projetointegrador.repository;

import com.italoghiele.projetointegrador.domain.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository <Subscription, Long> {
}
