package br.com.garcia.cache.usage;

import org.hibernate.Session;

import br.com.garcia.cache.HibernateUtil;
import br.com.garcia.cache.model.Person;

public class _03_FetchFromSecondLevelCacheExample {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        System.out.println((Person) session.load(Person.class, 1));
        System.out.println((Person) session.load(Person.class, 1));

        session.evict((Person) session.load(Person.class, 1));
        
        System.out.println((Person) session.load(Person.class, 1));

        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount());           //Prints 1
        
        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());   //Prints 1
        
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
