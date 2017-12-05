package io.spring.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

import java.util.Date;

/**
 * Created by USER on 2017-11-24.
 */
//@AllArgsConstructor
@Data
public class Customer implements ResourceAware {
    private final long id;

    private final String firstName;

    private final String lastName;

    private final Date birthdate;

    private Resource resource;

    public Customer(long id, String firstName, String lastName, Date birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

//    @Override
//    public void setResource(Resource resource) {
//        this.resource = resource;
//    }

}
