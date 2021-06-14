package com.jacksonleonardo.unpaper.model.services;

import com.jacksonleonardo.unpaper.model.entities.IUser;
import com.jacksonleonardo.unpaper.model.exceptions.NonExistentSessionException;
import com.jacksonleonardo.unpaper.model.exceptions.NullArgumentException;
import com.jacksonleonardo.unpaper.model.exceptions.SessionAlreadyHasUserException;

import java.time.Duration;
import java.time.Instant;

public class SessionService {
    private static final int INACTIVITY_ALLOWED_IN_MINUTES = 1;
    private static final int SECONDS = 60;
    private static SessionService INSTANCE;
    private Instant lastInteraction;
    private IUser currentUser;

    private SessionService() {
        this.lastInteraction = Instant.now();
    }

    private static SessionService getInstance() {
        if (INSTANCE == null) {
            synchronized (SessionService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SessionService();
                }
            }
        }
        return INSTANCE;
    }

    private void keepsSessionActive() {
        this.lastInteraction = Instant.now();
    }

    public static void killSession() {
        INSTANCE = null;
    }

    public static boolean isValid() {
        if (getInstance().currentUser == null)
            return false;
        Duration interval = Duration.between(getInstance().lastInteraction, Instant.now());
        return interval.getSeconds() <= (INACTIVITY_ALLOWED_IN_MINUTES * SECONDS);
    }

    public static void keepActive() {
        if (getInstance().currentUser == null)
            throw new NonExistentSessionException();
        getInstance().keepsSessionActive();
    }

    public static IUser getCurrentUser() {
        if (getInstance().currentUser == null)
            throw new NonExistentSessionException();
        getInstance().keepsSessionActive();
        return getInstance().currentUser;
    }

    public static void addUserInSession(IUser user) {
        if (user == null)
            throw new NullArgumentException();
        if (getInstance().currentUser != null)
            throw new SessionAlreadyHasUserException();
        getInstance().currentUser = user.clone();
    }
}
