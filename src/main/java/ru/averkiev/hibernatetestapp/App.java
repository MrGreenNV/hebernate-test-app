package ru.averkiev.hibernatetestapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.averkiev.hibernatetestapp.model.Item;
import ru.averkiev.hibernatetestapp.model.Passport;
import ru.averkiev.hibernatetestapp.model.Person;

import java.util.ArrayList;
import java.util.Collections;

public class App
{
    public static void main( String[] args )
    {
        // Подключение конфигурации hibernate.properties
        // Указываем класс, помеченный @Entity, тем самым указывая hibernate класс являющийся нашей сущностью
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class);

        // Создаем фабрику сессии из конфигурации
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            // Получаем сессию для работы с hibernate
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();

            // Работа с данными
            Person person = new Person("TestPassport", 50);
            Passport passport = new Passport(999777);
            person.setPassport(passport);

            session.persist(person);

            // Завершение транзакции
            session.getTransaction().commit();

            // Закрытие фабрики сессии автоматически
        }


    }
}
