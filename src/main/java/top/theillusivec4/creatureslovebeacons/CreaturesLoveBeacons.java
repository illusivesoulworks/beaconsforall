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

package top.theillusivec4.creatureslovebeacons;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.creatureslovebeacons.common.CreaturesLoveBeaconsConfig;
import top.theillusivec4.creatureslovebeacons.common.EventHandlerCommon;

@Mod(CreaturesLoveBeacons.MODID)
public class CreaturesLoveBeacons {

    public static final String MODID = "creatureslovebeacons";

    public CreaturesLoveBeacons() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CreaturesLoveBeaconsConfig.serverSpec);
    }

    private void setup(FMLCommonSetupEvent evt) {
        MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
    }
}
