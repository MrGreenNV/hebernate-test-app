package ru.averkiev.hibernatetestapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.averkiev.hibernatetestapp.model.*;

public class App {
    public static void main(String[] args) {
        // Подключение конфигурации hibernate.properties
        // Указываем класс, помеченный @Entity, тем самым указывая hibernate класс являющийся нашей сущностью
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        // Создаем фабрику сессии из конфигурации
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            // Получаем сессию для работы с hibernate
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();

            // Работа с данными

            Item item = session.get(Item.class, 7);
            System.out.println("Get Item");

            System.out.println(item.getOwner());

            // Завершение транзакции
            session.getTransaction().commit();

            // Закрытие фабрики сессии автоматически
        }
    }
}
