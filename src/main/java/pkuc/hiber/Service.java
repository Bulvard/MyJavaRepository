package pkuc.hiber;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Services")
public class Service {

	private long id;
	private Date dt;
	
	private Car car;
	private Mechanic mech;
	
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="svc_id")
	public long getId() {
		return id;
	}
	
	@Column(name="dt")
	public Date getDt() {
		return dt;
	}
	
	@ManyToOne
    @JoinColumn(name="car_id")
	public Car getCar() {
		return car;
	}
	
	@ManyToOne
    @JoinColumn(name="mech_id")
	public Mechanic getMech() {
		return mech;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public void setMech(Mechanic mech) {
		this.mech = mech;
	}

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", dt=" + dt +
                ", car=" + car +
                ", mech=" + mech +
                '}';
    }
}
