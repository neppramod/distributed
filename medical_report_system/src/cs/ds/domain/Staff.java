package cs.ds.domain;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
*/

import java.io.Serializable;

/**
 * Staff: Person who collects, modifiles, search Patient's treatment
 */
//@Entity
//@Table(name = "staff")
public class Staff implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String password;

    //@Id
    //@GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
