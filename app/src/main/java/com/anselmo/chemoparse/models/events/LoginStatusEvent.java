package com.anselmo.chemoparse.models.events;

/**
 * Created by naranya on 11/4/15.
 */
public class LoginStatusEvent {
    private boolean loginOk;

    public LoginStatusEvent(boolean loginOk) {
        this.loginOk = loginOk;
    }

    public boolean isLoginOk() {
        return loginOk;
    }

    public void setLoginOk(boolean loginOk) {
        this.loginOk = loginOk;
    }
}
