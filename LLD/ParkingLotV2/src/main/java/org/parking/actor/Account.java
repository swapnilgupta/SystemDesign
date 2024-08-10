package org.parking.actor;

import org.parking.enums.AccountStatus;

public abstract class Account {

    private String userName;
    private String password;
    private AccountStatus status;
    private Person person;

    public boolean resetPassword() {
        return false;
    }
}
