package ru.khtu.statemachine.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.khtu.statemachine.app.data.entity.StateTransitionSubactionInclusionEntity;

import java.util.List;

public interface StateTransitionSubactionInclusionRepository extends JpaRepository<StateTransitionSubactionInclusionEntity, Long> {

    @Query("SELECT i FROM StateTransitionSubactionInclusionEntity i " +
            "WHERE i.idStateTransition = :stateTransitionId AND i.idStateTransitionSubaction = :stateTransitionSubactionId")
    List<StateTransitionSubactionInclusionEntity> findByStateTransitionIdAndStateTransitionSubactionId(Long stateTransitionId, Long stateTransitionSubactionId);

}
