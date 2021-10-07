package raven.flightanchor.tiles;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import raven.flightanchor.blocks.RavenBlocks;

import javax.annotation.Nonnull;

import java.util.function.Supplier;

import static raven.flightanchor.FlightAnchor.MOD_ID;

public final class RavenTiles {
    private RavenTiles() {
        throw new AssertionError();
    }

    private static final DeferredRegister<TileEntityType<?>> TILES
        = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    public static final RegistryObject<TileEntityType<FlightAnchorTile>> FLIGHT_ANCHOR_TILE = TILES.register("block_flight", make(FlightAnchorTile::new, RavenBlocks.FLIGHT_ANCHOR_BLOCK));

    public static void register(@Nonnull IEventBus bus) {
        TILES.register(bus);
    }

    @SuppressWarnings("ConstantConditions")
    private static <T extends TileEntity> Supplier<TileEntityType<T>> make(@Nonnull Supplier<? extends T> factory, @Nonnull Supplier<Block> validBlock) {
        return () -> TileEntityType.Builder.<T>of(factory, validBlock.get()).build(null);
    }
}
