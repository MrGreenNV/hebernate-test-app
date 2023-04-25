package ru.averkiev.hibernatetestapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.averkiev.hibernatetestapp.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Подключение конфигурации hibernate.properties
        // Указываем класс, помеченный @Entity, тем самым указывая hibernate класс являющийся нашей сущностью
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        // Создаем фабрику сессии из конфигурации
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // Получаем сессию для работы с hibernate
        Session session = sessionFactory.getCurrentSession();

        try {
            // Начало транзакции
            session.beginTransaction();

            // Работа с данными
            Person person = session.get(Person.class, 1);
            System.out.println(person.getName() + person.getAge());

            // Завершение транзакции
            session.getTransaction().commit();
        } finally {
            // Закрытие фабрики сессии
            sessionFactory.close();
        }

    }
}
