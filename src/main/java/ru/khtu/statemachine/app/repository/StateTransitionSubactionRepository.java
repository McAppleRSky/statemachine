package ru.khtu.statemachine.app.repository;

import org.springframework.data.repository.Repository;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionEntity;

import java.util.List;

public interface StateTransitionSubactionRepository extends Repository<StateTransitionSubactionEntity, Long> {

    StateTransitionSubactionEntity findById(Long id);

}
