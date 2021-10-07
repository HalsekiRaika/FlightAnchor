package raven.flightanchor.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import raven.flightanchor.blocks.RavenBlocks;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static raven.flightanchor.FlightAnchor.MOD_ID;

public final class RavenItems {
    private RavenItems() {
        throw new AssertionError();
    }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> FLIGHT_ANCHOR_BLOCK_ITEM = ITEMS.register("block_flight", blockItemSupplier(RavenBlocks.FLIGHT_ANCHOR_BLOCK));

    public static void register(@Nonnull IEventBus bus) {
        ITEMS.register(bus);
    }

    private static Supplier<BlockItem> blockItemSupplier(Supplier<Block> blockSupplier) {
        return () -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(ItemGroup.TAB_MISC));
    }
}