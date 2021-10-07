package raven.flightanchor;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import raven.flightanchor.blocks.RavenBlocks;
import raven.flightanchor.events.FlightEvent;
import raven.flightanchor.items.RavenItems;
import raven.flightanchor.tiles.RavenTiles;

import static raven.flightanchor.FlightAnchor.MOD_ID;

@Mod(MOD_ID)
public final class FlightAnchor {
    public static final String MOD_ID = "flightanchor";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public FlightAnchor() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RavenBlocks.register(eventBus);
        RavenTiles.register(eventBus);
        RavenItems.register(eventBus);

        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        FlightEvent.register(forgeEventBus);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
