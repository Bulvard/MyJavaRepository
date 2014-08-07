package pkuc.hiber;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class Main {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            fillDatabase(session);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        //=======================================================================================
        try {
            session = sf.openSession();
            //------------------------------------
            Criteria crit = session.createCriteria(Mechanic.class);
            Mechanic exaMechanic = new Mechanic();
            System.out.println("Input name of mechanic: ");
            exaMechanic.setName(new Scanner(System.in).nextLine().trim());
            Example exa = Example.create(exaMechanic);
            //exa.excludeZeroes(); //for primitive types, which the Mechanic class doesn't have.
            crit.add(exa);
            Mechanic mechanic = (Mechanic)crit.uniqueResult();
            if (mechanic != null) {
                Set<Service> svcs = mechanic.getServices();
                System.out.printf("--------- %s's automobiles ---------%n", mechanic.getName());
                for (Service svc : svcs) {
                    System.out.println(svc.getCar().toString());
                }
                System.out.println("---------------------------------------");
            } else {
                System.out.println("Sorry, there is no mechanic with such name.");
            }
            //----------------------------------------------------------------------------
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        //=======================================================================================
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sf.close();
    }

    private static void fillDatabase(Session session) {
        session.beginTransaction();

        Car car = new Car("opel", 20000);
        session.save(car);
        long car_id = car.getId();

        Car car2 = new Car("BMW", 20000);
        session.save(car2);
        long car2_id = car2.getId();

        Car car3 = new Car("Audi", 20000);
        session.save(car3);
        long car3_id = car3.getId();

        Car car4 = new Car("Zapor", 20000);
        session.save(car4);
        long car4_id = car4.getId();

        Mechanic m1 = new Mechanic();
        m1.setName("Andrey");
        m1.setPhone("+38050-112-23-34");

        Mechanic m2 = new Mechanic();
        m2.setName("Peter");
        m2.setPhone("+38093-777-88-81");

        Service s1 = new Service();
        s1.setCar(car);
        s1.setMech(m1);
        s1.setDt(new Date(2014 - 1900, 2, 3));

        Service s2 = new Service();
        s2.setCar(car);
        s2.setMech(m2);
        s2.setDt(new Date(2014 - 1900, 4, 20));

        Service s3 = new Service();
        s3.setCar(car2);
        s3.setMech(m1);
        s3.setDt(new Date(2014 - 1900, 8, 25));

        Service s4 = new Service();
        s4.setCar(car3);
        s4.setMech(m1);
        s4.setDt(new Date(2014 - 1900, 9, 20));

        Service s5 = new Service();
        s5.setCar(car4);
        s5.setMech(m1);
        s5.setDt(new Date(2014 - 1900, 10, 20));

        m1.getServices().add(s1);
        m1.getServices().add(s3);
        m1.getServices().add(s4);
        m1.getServices().add(s5);
        m2.getServices().add(s2);

        car.getServices().add(s1);
        car2.getServices().add(s3);
        car3.getServices().add(s4);
        car4.getServices().add(s5);
        car.getServices().add(s2);

        session.save(m1);
        session.save(m2);

        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);
        session.save(s5);

        session.getTransaction().commit();
    }
}
