package eltex.been;

import eltex.entity.User;
import eltex.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {

    private static final Logger log = Logger.getLogger(UserService.class.getName());

    @Bean
    @Scope("prototype")
    public User getUserBeen() {
        return new User();
    }

    @Bean
    public UserService getUserServiceBean() {
        return new UserService();
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            getUserServiceBean().registNewUser(new User("u", "p"), false);
            log.info("User regist ['u', 'p']");
            getUserServiceBean().registNewUser(new User("admin", "p"), true);
            log.info("Admin regist ['admin', 'p']");
        };
    }


}
