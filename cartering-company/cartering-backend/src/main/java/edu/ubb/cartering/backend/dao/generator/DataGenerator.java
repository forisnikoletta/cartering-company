package edu.ubb.cartering.backend.dao.generator;

import edu.ubb.cartering.backend.dao.FoodDAO;
import edu.ubb.cartering.backend.dao.MenuDAO;
import edu.ubb.cartering.backend.dao.MenuOrdersDAO;
import edu.ubb.cartering.backend.dao.UserDAO;
import edu.ubb.cartering.backend.enums.USER_ROLES;
import edu.ubb.cartering.backend.model.Food;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.model.User;
import edu.ubb.cartering.backend.password.PasswordEncrypter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Singleton
@Startup
public class DataGenerator {

    @EJB
    private UserDAO userDao;

    @EJB
    private MenuDAO menuDao;

    @EJB
    private FoodDAO foodDao;

    @EJB
    private PasswordEncrypter passwordEncrypter;

    @PostConstruct
    public void initilize() {
        User adminUser = new User();
        adminUser.setAddress("Cluj-Napoca, Strada Mihai Kogalniceanu, nr 20-21 400000");
        adminUser.setEmail("admin@ubb.com");
        adminUser.setFirst("Cartering");
        adminUser.setLast("Owner");
        adminUser.setPassword(passwordEncrypter.createHash("password"));
        adminUser.setPhone("+40712312312");
        adminUser.setRole(USER_ROLES.ADMIN);
        userDao.create(adminUser);

        User normalUser = new User();
        normalUser.setAddress("Valahol Kolozsvaron de megtalaltok");
        normalUser.setEmail("jancsika@fiktivemail.hu");
        normalUser.setFirst("Kelemen");
        normalUser.setLast("Janos");
        normalUser.setPassword(passwordEncrypter.createHash("mypass"));
        normalUser.setPhone("+407534636");
        normalUser.setRole(USER_ROLES.USER);
        userDao.create(normalUser);


        Food food1 = new Food();
        food1.setTime(20);
        food1.setName("Hus leves");
        foodDao.create(food1);

        Food food2 = new Food();
        food2.setTime(30);
        food2.setName("Szezanmagos csirkehus");
        foodDao.create(food2);

        Food food3 = new Food();
        food3.setTime(25);
        food3.setName("Fott krumpli");
        foodDao.create(food3);

        Food food4 = new Food();
        food4.setTime(15);
        food4.setName("Cezar salata");
        foodDao.create(food4);

        Food food5 = new Food();
        food5.setTime(20);
        food5.setName("Palacsinta");
        foodDao.create(food5);

        Food food6 = new Food();
        food6.setTime(20);
        food6.setName("Gombakremleves");
        foodDao.create(food6);

        Food food7 = new Food();
        food7.setTime(20);
        food7.setName("Zoldsegleves");
        foodDao.create(food7);


        List<Food> foods = foodDao.findAll();
        Random rand = new Random();
        String [] types = {"Reggeli", "Ebed", "Vacsora"};
        for(int i = 0; i < foods.size(); ++i) {
            Menu menu = new Menu();
            Set<Food> menuF = new HashSet<>();
            for(int j = 0; j < 3; ++j) {
                Food fd = foods.get(rand.nextInt(foods.size()));
                while(menuF.contains(fd)) {
                    fd = foods.get(rand.nextInt(foods.size()));
                }
                menuF.add(fd);
            }
            menu.setName(types[rand.nextInt(3)] + i);
            menu.setFoods(menuF);
            menuDao.create(menu);
        }




    }
}
