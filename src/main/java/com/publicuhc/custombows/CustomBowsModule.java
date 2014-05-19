package com.publicuhc.custombows;

import com.publicuhc.custombows.bows.BowManager;
import com.publicuhc.custombows.bows.DefaultBowManager;
import com.publicuhc.pluginframework.shaded.inject.AbstractModule;

public class CustomBowsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BowManager.class).to(DefaultBowManager.class);
    }
}
