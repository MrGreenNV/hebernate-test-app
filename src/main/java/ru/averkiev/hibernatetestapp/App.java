package ru.averkiev.hibernatetestapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.averkiev.hibernatetestapp.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

            Movie movie = session.get(Movie.class, 2);
            Actor actor = session.get(Actor.class, 3);
            movie.getActors().remove(actor);
            actor.getMovies().remove(movie);


            // Завершение транзакции
            session.getTransaction().commit();

            // Закрытие фабрики сессии автоматически
        }


    }
}
