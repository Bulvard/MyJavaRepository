package pkuc.hiber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Restrictions;



@Entity
@Table(name="Cars")
public class Car {

    private long id;
    private String name;
    private int mileage;
    private Set<Service> services = new HashSet<Service>(0);

    public Car() {}

    public Car(String name, int mileage) {
    this.name = name;
    this.mileage = mileage;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="car_id")
    public long getId() {
    return id;
    }

    @Column(name="name")
    public String getName() {
    return name;
    }

    @Column(name="mileage")
    public int getMileage() {
    return mileage;
    }

    @OneToMany(mappedBy = "car")
    public Set<Service> getServices() {
    return services;
    }

    public void setName(String name) {
    this.name = name;
    }

    public void setId(long id) {
    this.id = id;
    }

    public void setMileage(int mileage) {
    this.mileage = mileage;
    }

    public void setServices(Set<Service> services) {
    this.services = services;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
