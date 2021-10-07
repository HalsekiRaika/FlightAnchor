package raven.flightanchor.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import raven.flightanchor.tiles.RavenTiles;

import javax.annotation.Nullable;

final class FlightAnchorBlock extends Block {
    FlightAnchorBlock() {
        super(Properties.copy(Blocks.IRON_BLOCK));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RavenTiles.FLIGHT_ANCHOR_TILE.get().create();
    }
}
