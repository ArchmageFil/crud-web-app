package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LandingController {
    private final DaoUser daoUser;
@Autowired
    public LandingController(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @GetMapping("/")
    public String landing(){
        return "index";
    }
    @PostMapping("/")
    public String clear(Model model){
        model.addAttribute("result", daoUser.clearDB());
        return "index";
    }
//    GET /css.html HTTP/1.1
//Host: localhost:8081
//User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0
//Accept: text/css,*/*;q=0.1
//Accept-Language: ru,ru-RU;q=0.8,en;q=0.5,en-US;q=0.3
//Accept-Encoding: gzip, deflate
//Connection:
    @GetMapping(value = "/crud.css", headers = "Accept=text/css")
    public String css(){
    return "crud/css";
    }

}
