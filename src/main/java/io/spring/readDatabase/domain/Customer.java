package io.spring.readDatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Created by USER on 2017-11-24.
 */
@AllArgsConstructor
@Data
public class Customer {
    private final long id;

    private final String firstName;

    private final String lastName;

    private final Date birthdate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
