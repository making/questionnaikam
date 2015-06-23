package questionnaikam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionnaireItemRepository extends JpaRepository<QuestionnaireItem, Long> {
    @Query("UPDATE QuestionnaireItem x SET x.value = x.value+1, x.version = x.version + 1 WHERE x.id = :id")
    @Modifying
    @Transactional
    int incrementValue(@Param("id") Long id);
}
