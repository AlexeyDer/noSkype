package
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class Application {
    /**
     *  Класс для запуска программы
     *
     *  @author "Alexey Derevtsov"
     *  @version 1.0.0
     *
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
