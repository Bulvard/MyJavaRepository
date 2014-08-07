package pkuc.hiber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Mechanics")
public class Mechanic {
	
	private long id;
	private String name;
	private String phone;
	
	private Set<Service> services = new HashSet<Service>(0);
	
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="mech_id")
	public long getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	
	@OneToMany(mappedBy = "mech")
	public Set<Service> getServices() {
		return services;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setServices(Set<Service> services) {
		this.services = services;
	}

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", services=" + services +
                '}';
    }
}
