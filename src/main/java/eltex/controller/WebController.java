package eltex.controller;

// import eltex.config.SpringConfig;
import eltex.TestHibernate;
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
    private PersonService personService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("persondata/{id}")
    public String personData(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));

        return "person";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String addPerson(Model model){
        Person person1 = new Person(1, "lili", "900", "lilii");
        personService.addPerson(person1);

        return "person";
    }

    @RequestMapping(value = "person", method = RequestMethod.GET)
    public String listPerson(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("listPerson", this.personService.listPersons());

        return "person";
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        TestHibernate testHibernate = new TestHibernate();
        model.addAttribute("name", name);

        return "index";
    }

}
