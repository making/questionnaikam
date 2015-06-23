package questionnaikam;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
@CrossOrigin
public class QuestionnairesController {
    @Autowired
    QuestionnairesRepository questionnairesRepository;
    @Autowired
    QuestionnaireItemRepository questionnaireItemRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) {
        List<Questionnaires> questionnairesList = questionnairesRepository.findAll();
        model.addAttribute("questionnairesList", questionnairesList);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "q/{id}")
    String get(@PathVariable UUID id, Model model) throws JsonProcessingException {
        Questionnaires questionnaires = questionnairesRepository.fetchOne(id);
        List<QuestionnaireWithColor> data = new ArrayList<>();
        for (Questionnaire v : questionnaires.getValues()) {
            int itemSize = v.getItems().size();
            List<QuestionnaireItemWithColor> items = new ArrayList<>(itemSize);
            float i = 0;
            for (QuestionnaireItem item : v.getItems()) {
                items.add(new QuestionnaireItemWithColor(item, Color.getHSBColor(i++ / itemSize, 0.9f, 0.9f)));
            }
            data.add(new QuestionnaireWithColor(v.getId(), v.getTitle(), items));
        }
        model.addAttribute("data", objectMapper.writeValueAsString(data));
        model.addAttribute("questionnaires", questionnaires);
        return "questionnaire";
    }


    @MessageMapping(value = "vote/{id}")
    void vote(@DestinationVariable Long id) {
        questionnaireItemRepository.incrementValue(id);
        QuestionnaireItem item = questionnaireItemRepository.findOne(id);
        simpMessagingTemplate.convertAndSend("/topic/questionnaires/" + id, item);
    }

    @Data
    public static class QuestionnaireWithColor implements Serializable {
        private final Long id;
        private final String title;
        private final List<QuestionnaireItemWithColor> items;
    }

    @AllArgsConstructor
    public static class QuestionnaireItemWithColor implements Serializable {
        @Getter
        @JsonUnwrapped
        private final QuestionnaireItem questionnaireItem;
        private final Color color;

        public String getColor() {
            return String.format("#%06X", (0xFFFFFF & this.color.getRGB()));
        }

        public String getHighlight() {
            return String.format("#%06X", (0xFFFFFF & this.color.brighter().getRGB()));
        }
    }
}
