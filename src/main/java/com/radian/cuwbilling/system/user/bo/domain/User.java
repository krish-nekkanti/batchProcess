package com.radian.cuwbilling.system.user.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Person;

/**
 * This class represents the persistent Axiom system User.
 * 
 * An Axiom system User represents the login identity of a system User and has a
 * reference to the Person assigned to this identity.
 * 
 * @author Rick Mohr
 * @version 1.0
 */

public interface User extends BaseDomainObject
{
    /**
     * @Return The encrypted password for this User identity.
     */
    public String getPassword();

    /**
     * @Return The User's answer to their selected Password Hint Question.
     */
    public String getPasswordHintAnswer();

    /**
     * @Return The login user name for this User's identity.
     */
    public String getUsername();

    /**
     * Assign the User's answer to their selected Password Hint Question.
     * 
     * @param passwordHintAnswer
     *            Answer to the Password Hint Question.
     */
    public void setPasswordHintAnswer(final String passwordHintAnswer);

    /**
     * Assign the encrypted password for this User identity.
     * 
     * @param password
     *            Encrypted password for this User identity.
     */
    public void setPassword(final String password);

    /**
     * Assign the user login name for this User identity.
     * 
     * @param username
     *            User login name.
     */
    public void setUsername(final String username);

    public Person getPerson();

    public void setPerson(Person person);

    public Collection getSystemLogins();
}
