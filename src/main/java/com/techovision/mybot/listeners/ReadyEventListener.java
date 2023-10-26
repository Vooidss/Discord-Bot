package com.techovision.mybot.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyEventListener implements EventListener {
    @Override
    public void onEvent(GenericEvent genericEvent) {
        System.out.println("Бот запушен!");
    }
}
