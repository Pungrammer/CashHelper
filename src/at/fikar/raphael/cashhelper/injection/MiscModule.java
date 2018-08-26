package at.fikar.raphael.cashhelper.injection;

import com.google.inject.AbstractModule;

import java.time.Clock;

public class MiscModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Clock.class).toInstance(Clock.systemUTC());
    }

}
