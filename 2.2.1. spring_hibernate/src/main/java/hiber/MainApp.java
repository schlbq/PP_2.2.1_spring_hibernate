package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Audi", 4);
        Car car2 = new Car("Mazda", 6);
        Car car3 = new Car("Bmw", 550);
        Car car4 = new Car("Nissan", 34);

        userService.add(new User("Ivan", "Ivanov", "Ivanov72@mail.ru", car1));
        userService.add(new User("Petr", "Petrov", "Petrov61@mail.ru", car2));
        userService.add(new User("Anton", "Antonov", "Antonov77@mail.ru", car3));
        userService.add(new User("Dmitriy", "Dmitriev", "Dmitriev24@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        userService.getByModelAndSeries("Audi", 4);
        userService.getByModelAndSeries("Mazda", 6);
        userService.getByModelAndSeries("Bmw", 550);
        userService.getByModelAndSeries("Nissan", 34);

        context.close();
    }
}
