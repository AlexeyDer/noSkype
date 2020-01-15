package eltex.controller;

// import eltex.config.SpringConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WebController {
//    private PersonService personService;
//
//    @Autowired(required = true)
//    @Qualifier(value = "personService")
//    public void setPersonService(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @RequestMapping("persondata/{id}")
//    public String personData(@PathVariable("id") int id, Model model){
//        model.addAttribute("person", this.personService.getPersonById(id));
//
//        return "person";
//    }
//
//    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
//    public String addPerson(Model model){
//        Person person1 = new Person(1, "lili", "900", "lilii");
//        personService.addPerson(person1);
//
//        return "person";
//    }
//
//    @RequestMapping(value = "person", method = RequestMethod.GET)
//    public String listPerson(Model model){
//        model.addAttribute("person", new Person());
//        model.addAttribute("listPerson", this.personService.listPersons());
//
//        return "person";
//    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

}
