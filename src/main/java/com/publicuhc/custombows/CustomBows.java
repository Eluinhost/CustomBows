package com.publicuhc.custombows;

import com.publicuhc.custombows.arrows.FireArrowBehaviour;
import com.publicuhc.custombows.arrows.RandomOffsetArrowBehaviour;
import com.publicuhc.custombows.bows.BowManager;
import com.publicuhc.custombows.bows.ShotgunBow;
import com.publicuhc.pluginframework.FrameworkJavaPlugin;
import com.publicuhc.pluginframework.shaded.inject.AbstractModule;
import com.publicuhc.pluginframework.shaded.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class CustomBows extends FrameworkJavaPlugin {

    private BowManager m_manager;

    @Override
	public void onFrameworkEnable() {
        getServer().getPluginManager().registerEvents(m_manager, this);

        ShotgunBow shotgun = new ShotgunBow(this);
        shotgun.addBehaviour(new RandomOffsetArrowBehaviour(this, 10, 10));
        shotgun.addBehaviour(new FireArrowBehaviour());

        m_manager.addBowType(shotgun);
	}

    @Inject
    protected void setBowManager(BowManager manager) {
        m_manager = manager;
    }

    @Override
    public List<AbstractModule> initialModules() {
        List<AbstractModule> modules = new ArrayList<AbstractModule>();
        modules.add(new CustomBowsModule());
        return modules;
    }

}
