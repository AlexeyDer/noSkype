package eltex.controller;

import eltex.config.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import eltex.entity.Person;
import eltex.service.PersonService;

@Controller
class WebController {

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String addPerson(Model model){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonService personService = context.getBean(PersonService.class);

        Person person1 = new Person(1, "lili", "900", "lilii");
        personService.addPerson(person1);

        return "person";
    }

    @RequestMapping(value = "person", method = RequestMethod.GET)
    public String listPerson(Model model){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonService personService = context.getBean(PersonService.class);

        model.addAttribute("person", new Person());
        model.addAttribute("listPerson", personService.listPersons());

        System.out.println("-----------H E L L O----------");
        return "person";
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

}
