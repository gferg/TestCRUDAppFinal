package app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private Boolean isAdmin;

    private Timestamp timestamp = new Timestamp(new Date().getTime());

    public User(){

    }

    public User(String name, String age, UserStatus userStatus) {
        this.name = name;
        this.age = Integer.parseInt(age);
        if(userStatus.equals(UserStatus.TRUE)) isAdmin = true;
        else isAdmin = false;
    }

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getAge() {
        return "" + age;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public UserStatus getStatus() {
        if(isAdmin.equals(true)) return UserStatus.TRUE;
        else return UserStatus.FALSE;
    }

    public void setStatus(UserStatus userStatus) {
        if(userStatus.equals(UserStatus.TRUE)) this.isAdmin = true;
        else isAdmin = false;
    }

    public String getTimestamp() {
        return timestamp.toLocalDateTime().toString();
    }

    @Override
    public String toString() {
        return String.format("User [id='%d' name='%s', age='%s', isAdmin='%s', date='%s']", id,
                name, age, isAdmin, timestamp);
    }

}

