package com.publicuhc.custombows;

import com.publicuhc.custombows.arrows.FireArrowBehaviour;
import com.publicuhc.custombows.arrows.RandomOffsetArrowBehaviour;
import com.publicuhc.custombows.bows.BowManager;
import com.publicuhc.custombows.bows.DefaultBowManager;
import com.publicuhc.custombows.bows.ShotgunBow;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomBows extends JavaPlugin {

    @Override
	public void onEnable() {
        BowManager manager = new DefaultBowManager();
        getServer().getPluginManager().registerEvents(manager, this);

        ShotgunBow shotgun = new ShotgunBow(this);
        shotgun.addBehaviour(new RandomOffsetArrowBehaviour(this, 10, 10));
        shotgun.addBehaviour(new FireArrowBehaviour());

        manager.addBowType(shotgun);
	}

}
