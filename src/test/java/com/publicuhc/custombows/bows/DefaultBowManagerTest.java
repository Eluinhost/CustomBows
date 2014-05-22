package com.publicuhc.custombows.bows;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.BlockProjectileSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyZeroInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class DefaultBowManagerTest {

    private DefaultBowManager manager;
    private BowType bowType;

    @Before
    public void onStartUp() {
        manager = new DefaultBowManager();
        bowType = mock(BowType.class);
        when(bowType.getType()).thenReturn("TestBow");
        manager.addBowType(bowType);
    }

    @Test
    public void testProjectileLaunchEventNonArrow() {
        Entity entity = mock(Fireball.class);
        ProjectileLaunchEvent ple = new ProjectileLaunchEvent(entity);

        manager.onProjectileLaunchEvent(ple);

        verifyZeroInteractions(bowType);
        verifyZeroInteractions(entity);
        assertThat(ple.isCancelled()).isFalse();
    }

    @Test
    public void testProjectileLaunchEventNonLiving() {
        Arrow entity = mock(Arrow.class);
        //emulate dispenser shot
        when(entity.getShooter()).thenReturn(mock(BlockProjectileSource.class));

        ProjectileLaunchEvent ple = new ProjectileLaunchEvent(entity);

        manager.onProjectileLaunchEvent(ple);

        verifyZeroInteractions(bowType);
        verify(entity, times(1)).getShooter();
        verifyNoMoreInteractions(entity);

        assertThat(ple.isCancelled()).isFalse();
    }

    @Test
    public void testProjectileLaunchEventWrongItemInHand() {
        Arrow entity = mock(Arrow.class);
        LivingEntity source = mock(LivingEntity.class);
        EntityEquipment equipment = mock(EntityEquipment.class);
        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, 1);

        when(entity.getShooter()).thenReturn(source);
        when(source.getEquipment()).thenReturn(equipment);
        when(equipment.getItemInHand()).thenReturn(goldIngot);

        ProjectileLaunchEvent ple = new ProjectileLaunchEvent(entity);

        manager.onProjectileLaunchEvent(ple);

        verifyZeroInteractions(bowType);
        verify(entity, times(1)).getShooter();
        verifyNoMoreInteractions(entity);

        assertThat(ple.isCancelled()).isTrue();
    }

    @Test
    public void testProjectileLaunchEventRegularBow() {
        Arrow entity = mock(Arrow.class);
        LivingEntity source = mock(LivingEntity.class);
        EntityEquipment equipment = mock(EntityEquipment.class);
        ItemStack bow = new ItemStack(Material.BOW, 1);

        when(entity.getShooter()).thenReturn(source);
        when(source.getEquipment()).thenReturn(equipment);
        when(equipment.getItemInHand()).thenReturn(bow);

        ProjectileLaunchEvent ple = new ProjectileLaunchEvent(entity);

        manager.onProjectileLaunchEvent(ple);

        verifyZeroInteractions(bowType);
        verify(entity, times(1)).getShooter();
        verifyNoMoreInteractions(entity);

        assertThat(ple.isCancelled()).isFalse();
    }

}
