package questionnaikam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@CrossOrigin
@SessionAttributes(value = "questionnairesForm")
public class QuestionnairesEditController {
    @Autowired
    QuestionnairesRepository questionnairesRepository;

    @ModelAttribute("questionnairesForm")
    QuestionnairesForm setupForm() {
        QuestionnairesForm questionnairesForm = new QuestionnairesForm();
        questionnairesForm.setValues(new ArrayList<>());
        questionnairesForm.setExpiredAt(Date.from(LocalDate.now()
                .plusWeeks(1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));

        QuestionnaireForm questionnaireForm = new QuestionnaireForm();
        questionnaireForm.setItems(new ArrayList<>());

        QuestionnaireItemForm questionnaireItemForm1 = new QuestionnaireItemForm();
        QuestionnaireItemForm questionnaireItemForm2 = new QuestionnaireItemForm();

        questionnairesForm.getValues().add(questionnaireForm);
        questionnaireForm.getItems().addAll(Arrays.asList(questionnaireItemForm1, questionnaireItemForm2));
        return questionnairesForm;
    }

    @RequestMapping(method = RequestMethod.GET, value = "q", params = "form")
    String createForm(QuestionnairesForm questionnairesForm) {
        return "createForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "addQuestionnaire")
    String addQuestionnaire(QuestionnairesForm questionnairesForm) {
        QuestionnaireForm questionnaireForm = new QuestionnaireForm();
        questionnaireForm.setItems(new ArrayList<>());

        QuestionnaireItemForm questionnaireItemForm1 = new QuestionnaireItemForm();
        QuestionnaireItemForm questionnaireItemForm2 = new QuestionnaireItemForm();

        questionnairesForm.getValues().add(questionnaireForm);
        questionnaireForm.getItems().addAll(Arrays.asList(questionnaireItemForm1, questionnaireItemForm2));
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "addItem")
    String addItem(@RequestParam Integer row, QuestionnairesForm questionnairesForm) {
        QuestionnaireItemForm questionnaireItemForm = new QuestionnaireItemForm();
        questionnairesForm.getValues().get(row).getItems().add(questionnaireItemForm);
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "removeQuestionnaire")
    String removeQuestionnaire(@RequestParam Integer row, QuestionnairesForm questionnairesForm) {
        questionnairesForm.getValues().remove(row.intValue());
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "removeItem")
    String removeItem(@RequestParam Integer row, @RequestParam Integer itemRow, QuestionnairesForm questionnairesForm) {
        questionnairesForm.getValues().get(row).getItems().remove(itemRow.intValue());
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "moveQuestionnaire")
    String moveQuestionnaire(@RequestParam Integer row, QuestionnairesForm questionnairesForm) {
        QuestionnaireForm target = questionnairesForm.getValues().get(row);
        questionnairesForm.getValues().remove(row.intValue());
        questionnairesForm.getValues().add(row - 1, target);
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q", params = "moveItem")
    String moveItem(@RequestParam Integer row, @RequestParam Integer itemRow, QuestionnairesForm questionnairesForm) {
        QuestionnaireItemForm target = questionnairesForm.getValues().get(row).getItems().get(itemRow);
        questionnairesForm.getValues().get(row).getItems().remove(itemRow.intValue());
        questionnairesForm.getValues().get(row).getItems().add(itemRow - 1, target);
        return "redirect:/q?form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q")
    String create(@Validated QuestionnairesForm questionnairesForm, BindingResult result,
                  SessionStatus sessionStatus, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "createForm";
        }

        Questionnaires questionnaires = Questionnaires.builder()
                .title(questionnairesForm.getTitle())
                .createdAt(new Date())
                .expiredAt(questionnairesForm.getExpiredAt())
                .values(questionnairesForm.getValues().stream()
                        .map(v -> Questionnaire.builder()
                                .title(v.getTitle())
                                .items(v.getItems().stream()
                                        .map(item -> QuestionnaireItem.builder()
                                                .label(item.getItemName())
                                                .value(0L)
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
        Questionnaires created = questionnairesRepository.saveAndFlush(questionnaires);
        attributes.addFlashAttribute(created);
        sessionStatus.setComplete();
        return "redirect:/q/?finish";
    }

    @RequestMapping(method = RequestMethod.GET, value = "q", params = "finish")
    String createFinish() {
        return "createFinish";
    }

    @RequestMapping(method = RequestMethod.GET, value = "q/{id}", params = "form")
    String updateForm(@PathVariable UUID id, QuestionnairesForm questionnairesForm, Model model) {
        Questionnaires questionnaires = questionnairesRepository.fetchOne(id);
        questionnairesForm.setTitle(questionnaires.getTitle());
        questionnairesForm.setExpiredAt(questionnaires.getExpiredAt());
        questionnairesForm.setValues(questionnaires.getValues().stream()
                .map(v -> {
                    QuestionnaireForm form = new QuestionnaireForm();
                    form.setId(v.getId());
                    form.setTitle(v.getTitle());
                    form.setItems(v.getItems().stream()
                            .map(item -> {
                                QuestionnaireItemForm itemForm = new QuestionnaireItemForm();
                                itemForm.setItemId(item.getItemId());
                                itemForm.setItemName(item.getLabel());
                                return itemForm;
                            })
                            .collect(Collectors.toList()));
                    return form;
                })
                .collect(Collectors.toList()));
        model.addAttribute("id", id);
        return "updateForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q/{id}", params = "addQuestionnaire")
    String addQuestionnaireForUpdate(@PathVariable UUID id, QuestionnairesForm questionnairesForm, Model model) {
        QuestionnaireForm questionnaireForm = new QuestionnaireForm();
        questionnaireForm.setItems(new ArrayList<>());

        QuestionnaireItemForm questionnaireItemForm1 = new QuestionnaireItemForm();
        QuestionnaireItemForm questionnaireItemForm2 = new QuestionnaireItemForm();

        questionnairesForm.getValues().add(questionnaireForm);
        questionnaireForm.getItems().addAll(Arrays.asList(questionnaireItemForm1, questionnaireItemForm2));
        model.addAttribute("id", id);
        return "updateForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q/{id}", params = "addItem")
    String addItemForUpdate(@PathVariable UUID id, @RequestParam Integer row,
                            QuestionnairesForm questionnairesForm, Model model) {
        QuestionnaireItemForm questionnaireItemForm = new QuestionnaireItemForm();
        questionnairesForm.getValues().get(row).getItems().add(questionnaireItemForm);
        model.addAttribute("id", id);
        return "updateForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q/{id}", params = "removeQuestionnaire")
    String removeQuestionnaireForUpdate(@PathVariable UUID id, @RequestParam Integer row,
                                        QuestionnairesForm questionnairesForm, Model model) {
        questionnairesForm.getValues().remove(row.intValue());
        model.addAttribute("id", id);
        return "updateForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q/{id}", params = "removeItem")
    String removeItemForUpdate(@PathVariable UUID id, @RequestParam Integer row, @RequestParam Integer itemRow,
                               QuestionnairesForm questionnairesForm, Model model) {
        questionnairesForm.getValues().get(row).getItems().remove(itemRow.intValue());
        model.addAttribute("id", id);
        return "updateForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "q/{id}")
    @Transactional
    String update(@PathVariable UUID id, @Validated QuestionnairesForm questionnairesForm, BindingResult result,
                  SessionStatus sessionStatus, RedirectAttributes attributes, Model model) {
        model.addAttribute("id", id);
        if (result.hasErrors()) {
            System.out.println(result);
            return "updateForm";
        }

        Questionnaires original = questionnairesRepository.fetchOne(id);
        Map<Long, Long> valueMap = original.getValues().stream()
                .flatMap(v -> v.getItems().stream())
                .collect(Collectors.toMap(QuestionnaireItem::getItemId, QuestionnaireItem::getValue));
        Map<Long, Long> versionMap = original.getValues().stream()
                .flatMap(v -> v.getItems().stream())
                .collect(Collectors.toMap(QuestionnaireItem::getItemId, QuestionnaireItem::getVersion));

        Questionnaires questionnaires = Questionnaires.builder()
                .id(id)
                .title(questionnairesForm.getTitle())
                .expiredAt(questionnairesForm.getExpiredAt())
                .values(questionnairesForm.getValues().stream()
                        .map(v -> Questionnaire.builder()
                                .id(v.getId())
                                .title(v.getTitle())
                                .items(v.getItems().stream()
                                        .map(item -> QuestionnaireItem.builder()
                                                .itemId(item.getItemId())
                                                .label(item.getItemName())
                                                .value(valueMap.getOrDefault(item.getItemId(), 0L))
                                                .version(versionMap.getOrDefault(item.getItemId(), 0L))
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();

        System.out.println(questionnaires);
        questionnairesRepository.flush();
        questionnairesRepository.saveAndFlush(questionnaires);
        sessionStatus.setComplete();
        attributes.addAttribute("id", id);
        return "redirect:/q/{id}";
    }


    @Data
    public static class QuestionnairesForm {
        @NotEmpty
        private String title;
        @Valid
        @Size(min = 1, max = 100)
        private List<QuestionnaireForm> values;
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private Date expiredAt;
    }

    @Data
    public static class QuestionnaireForm {
        private Long id;
        @NotEmpty
        private String title;
        @Valid
        @Size(min = 2, max = 100)
        private List<QuestionnaireItemForm> items;
    }

    @Data
    public static class QuestionnaireItemForm {
        private Long itemId;
        @NotEmpty
        private String itemName;
    }
}