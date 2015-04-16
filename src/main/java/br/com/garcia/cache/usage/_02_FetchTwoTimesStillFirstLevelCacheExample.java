package br.com.garcia.cache.usage;

import org.hibernate.Session;

import br.com.garcia.cache.HibernateUtil;
import br.com.garcia.cache.model.Person;

public class _02_FetchTwoTimesStillFirstLevelCacheExample {

    public static void main(String[] args) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        
        sessionOne.beginTransaction();
        
        System.out.println((Person) sessionOne.load(Person.class, 1));
        System.out.println((Person) sessionOne.load(Person.class, 1));
        
        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount());           //Prints 1
        
        System.out.println(HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());   //Prints 0
        
        sessionOne.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
