package raven.flightanchor.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import static raven.flightanchor.FlightAnchor.MOD_ID;

final public class RavenBlocks {
    private RavenBlocks() {
        throw new AssertionError();
    }

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> FLIGHT_ANCHOR_BLOCK = BLOCKS.register("block_flight", FlightAnchorBlock::new);

    public static void register(@Nonnull IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
