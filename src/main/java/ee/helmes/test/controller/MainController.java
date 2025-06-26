package ee.helmes.test.controller;

import ee.helmes.test.dto.UserDataForm;
import ee.helmes.test.dto.UserDataView;
import ee.helmes.test.dto.UserSessionData;
import ee.helmes.test.service.SectorService;
import ee.helmes.test.service.UserDataService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class MainController {

    private final SectorService sectorService;
    private final UserDataService userDataService;

    @GetMapping("/")
    public String showForm(Model model, HttpSession session) {
        var allSectors = sectorService.getSectorsForView();
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        UserDataView formView;

        if (currentUserId != null) {
            UserSessionData userSessionData = userDataService.getUserDataForSession(currentUserId);
            formView = new UserDataView(
                    userSessionData.name(),
                    userSessionData.agreedToTerms(),
                    userSessionData.selectedSectorIds(),
                    allSectors
            );
        } else {
            formView = new UserDataView(
                    "",
                    false,
                    Collections.emptySet(),
                    allSectors
            );
        }
        model.addAttribute("formData", formView);

        return "index";
    }

    @PostMapping("/")
    public String saveForm(@Valid @ModelAttribute("formData") UserDataForm userDataForm, HttpSession session) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        var savedUserData = userDataService.saveOrUpdateUserData(userDataForm, currentUserId);
        session.setAttribute("currentUserId", savedUserData.getId());

        return "redirect:/";
    }
}
