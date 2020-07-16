package pl.kurs.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.model.BanedPesel;
import pl.kurs.java.repository.PeselBanRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class PeselBanController {

    private final PeselBanRepository peselBanRepository;

    @GetMapping
    public String enterMainPage(ModelMap model) {
        model.addAttribute("pesels", peselBanRepository.findAll());
        return "banView";
    }
    @GetMapping("/get")
    public ResponseEntity<List<BanedPesel>> getPesels(ModelMap model) {
        return ResponseEntity.ok(peselBanRepository.findAll());
    }
    @PostMapping("/")
    public String addBan(ModelMap model, @ModelAttribute BanedPesel pesel) {

        peselBanRepository.saveAndFlush(pesel);
        model.addAttribute("pesels", peselBanRepository.findAll());

        return "redirect:/banService";
    }

    @GetMapping("/delete")
    public String delete(ModelMap model, @RequestParam(value = "id") long id) {
        if (peselBanRepository.findById(id).isPresent()) {
            BanedPesel p = peselBanRepository.findById(id).get();
            peselBanRepository.delete(p);
        }
        model.addAttribute("pesels", peselBanRepository.findAll());

        return "redirect:/banService";
    }
}
