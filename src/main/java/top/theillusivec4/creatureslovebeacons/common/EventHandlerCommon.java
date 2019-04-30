/*
 * Copyright (C) 2018-2019  C4
 *
 * This file is part of Creatures Love Beacons, a mod made for Minecraft.
 *
 * Creatures Love Beacons is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Creatures Love Beacons is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Creatures Love Beacons.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.creatureslovebeacons.common;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.IAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.theillusivec4.creatureslovebeacons.util.ReflectionAccessor;

import java.util.List;

public class EventHandlerCommon {

    private static final Predicate<EntityLivingBase> VALID_CREATURE = living -> !(living instanceof EntityPlayer)
            && isValidCreature(living);

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent evt) {
        World world = evt.world;

        if (!world.isRemote && evt.phase == TickEvent.Phase.END && world.getGameTime() % 80L == 0L) {

            for (TileEntity tileentity : world.tickableTileEntities) {

                if (tileentity instanceof TileEntityBeacon && !tileentity.isRemoved() && tileentity.hasWorld()) {
                    BlockPos blockpos = tileentity.getPos();

                    if (world.isBlockLoaded(blockpos, false) && world.getWorldBorder().contains(blockpos)) {
                        addBeaconEffectsToCreatures((TileEntityBeacon) tileentity, world);
                    }
                }
            }
        }
    }

    private static boolean isValidCreature(EntityLivingBase entityLivingBase) {
        boolean flag1 = false;

        switch (CreaturesLoveBeaconsConfig.SERVER.creatureType.get()) {
            case ALL_TAMED : flag1 = entityLivingBase instanceof EntityTameable && ((EntityTameable) entityLivingBase).isTamed();
                break;
            case ALL_PASSIVE: flag1 = entityLivingBase instanceof IAnimal && !(entityLivingBase instanceof IMob);
                break;
            case ALL: flag1 = true;
                break;
        }
        ResourceLocation rl = EntityType.getId(entityLivingBase.getType());
        boolean flag2 = false;

        if (rl != null) {

            for (String s : CreaturesLoveBeaconsConfig.SERVER.additionalCreatures.get()) {

                if (s.equals(rl.toString())) {
                    flag2 = true;
                    break;
                }
            }
        }
        return flag1 || flag2;
    }

    private static void addBeaconEffectsToCreatures(TileEntityBeacon beacon, World world) {
        int levels = beacon.getLevels();
        Potion primaryEffect = ReflectionAccessor.getPrimaryEffect(beacon);
        Potion secondaryEffect = ReflectionAccessor.getSecondaryEffect(beacon);

        if (ReflectionAccessor.isComplete(beacon) && levels > 0 && primaryEffect != null) {
            double d0 = (double)(levels * 10 + 10);
            int i = 0;

            if (levels >= 4 && primaryEffect == secondaryEffect) {
                i = 1;
            }

            int j = (9 + levels * 2) * 20;
            int k = beacon.getPos().getX();
            int l = beacon.getPos().getY();
            int i1 = beacon.getPos().getZ();
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).grow(d0).expand(0.0D, (double)world.getHeight(), 0.0D);
            List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb,
                    VALID_CREATURE);

            for (EntityLivingBase entityLivingBase : list) {
                entityLivingBase.addPotionEffect(new PotionEffect(primaryEffect, j, i, true, true));
            }

            if (levels >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null) {

                for (EntityLivingBase entityLivingBase : list) {
                    entityLivingBase.addPotionEffect(new PotionEffect(secondaryEffect, j, 0, true, true));
                }
            }
        }
    }
}
