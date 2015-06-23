package questionnaikam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QuestionnairesRepository extends JpaRepository<Questionnaires, UUID> {

    @Query(value = "SELECT DISTINCT x FROM Questionnaires x JOIN FETCH x.values WHERE x.id=:id")
    Questionnaires fetchOne(@Param("id") UUID id);
}
