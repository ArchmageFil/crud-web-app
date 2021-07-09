package io.github.archmagefil.crudwebapp.контроллер;

import io.github.archmagefil.crudwebapp.дао.ДаоПользователяJPA;
import io.github.archmagefil.crudwebapp.модель.Пользователь;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class СОПУконтроллер {
    @GetMapping("/usereditor")
    public String редакторПользователей(Model модель, ДаоПользователяJPA дао){
        List<Пользователь> список = дао.получитьВсех();
        модель.addAttribute("пользователи", список);
        return "usereditor";
    }
}
//TODO реализовать