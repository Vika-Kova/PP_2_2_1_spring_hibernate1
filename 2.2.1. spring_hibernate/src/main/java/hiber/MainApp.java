package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User vasya = new User("Vasya", "Vasechkin", "vasechkin@mail.io");
        User petya = new User("Petya", "Sidorov", "sidorov@mail.io");
        User olga = new User("Olga", "Petrova", "petrova@mail.io");
        User sveta = new User("Svetlana", "Ivanova", "ivanova@mail.io");

        Car volvo = new Car("volvo", 1, vasya);
        Car bmw = new Car("bmw", 2, petya);
        Car suzuki = new Car("suzuki", 3, olga);
        Car lada = new Car("lada", 4, sveta);

        vasya.setCar(volvo);
        petya.setCar(bmw);
        olga.setCar(suzuki);
        sveta.setCar(lada);

        userService.add(vasya);
        userService.add(petya);
        userService.add(olga);
        userService.add(sveta);

        // пользователи с машинами
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        // достать юзера, владеющего машиной по модели и серии
        User user = (User) userService.getUserByModelAndSeries("volvo", 1);
        System.out.println(user);

        context.close();
    }
}

