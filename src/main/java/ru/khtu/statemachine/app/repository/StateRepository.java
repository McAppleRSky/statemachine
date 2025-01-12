package ru.khtu.statemachine.app.repository;

import org.springframework.data.repository.Repository;
import ru.khtu.statemachine.app.data.entity.StateEntity;

import java.util.List;

public interface StateRepository extends Repository<StateEntity, Long> {

    List<StateEntity> findByWoName(String woName);

}
