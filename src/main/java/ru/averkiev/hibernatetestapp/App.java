package ru.averkiev.hibernatetestapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.averkiev.hibernatetestapp.model.Item;
import ru.averkiev.hibernatetestapp.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        // Подключение конфигурации hibernate.properties
        // Указываем класс, помеченный @Entity, тем самым указывая hibernate класс являющийся нашей сущностью
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        // Создаем фабрику сессии из конфигурации
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            // Получаем сессию для работы с hibernate
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();

            // Работа с данными

            Person person = session.get(Person.class, 4);
            Item item = session.get(Item.class, 1);

            item.getOwner().getItems().remove(item);

            item.setOwner(person);
            person.getItems().add(item);


            // Завершение транзакции
            session.getTransaction().commit();

            // Закрытие фабрики сессии автоматически
        }


    }
}
