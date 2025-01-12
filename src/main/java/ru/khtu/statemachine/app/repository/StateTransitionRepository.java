package ru.khtu.statemachine.app.repository;

import org.springframework.data.repository.Repository;
import ru.khtu.statemachine.app.data.entity.StateTransitionEntity;

import java.util.List;

public interface StateTransitionRepository extends Repository<StateTransitionEntity, Long> {

    List<StateTransitionEntity> findByWoName(String woName);

}
