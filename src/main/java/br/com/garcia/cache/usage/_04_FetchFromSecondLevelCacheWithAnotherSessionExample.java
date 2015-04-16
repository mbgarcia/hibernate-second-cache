package br.com.garcia.cache.usage;

import org.hibernate.Session;

import br.com.garcia.cache.HibernateUtil;
import br.com.garcia.cache.model.Person;

public class _04_FetchFromSecondLevelCacheWithAnotherSessionExample {

    public static void main(String[] args) {
        Session session01 = HibernateUtil.getSessionFactory().openSession();
        Session session02 = HibernateUtil.getSessionFactory().openSession();
        
        session01.beginTransaction();
        session02.beginTransaction();
        
        System.out.println((Person) session01.load(Person.class, 1));
        System.out.println((Person) session01.load(Person.class, 1));

        session01.evict((Person) session01.load(Person.class, 1));
        
        System.out.println((Person) session01.load(Person.class, 1));
        System.out.println((Person) session02.load(Person.class, 1));

        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount());           //Prints 1
        
        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());   //Prints 2
        
        session01.getTransaction().commit();
        session02.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
