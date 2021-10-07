package raven.flightanchor.tiles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
//import raven.flightanchor.FlightAnchor;

import java.util.List;

final class FlightAnchorTile extends TileEntity implements ITickableTileEntity {
    private static final int RANGE = 32;

    FlightAnchorTile() {
        super(RavenTiles.FLIGHT_ANCHOR_TILE.get());
    }

    @Override
    public void tick() {
        if (this.getLevel() != null) {
            List<PlayerEntity> players = this.getLevel().getEntitiesOfClass(PlayerEntity.class,
                    new AxisAlignedBB(this.getBlockPos().getX() - RANGE ,this.getBlockPos().getY() - RANGE, this.getBlockPos().getZ() - RANGE,
                            this.getBlockPos().getX() + RANGE, this.getBlockPos().getY() + RANGE, this.getBlockPos().getZ() + RANGE));
            for (PlayerEntity player : players) {
                if (shouldApplyFlightStatus(player)) {
                    inEffect(player);
                    applyLimitedFlight(player);
                    //FlightAnchor.getLogger().debug("TileObserve (Tick): " + player.getPersistentData().getInt("flight_limit"));
                }
            }
        }
    }

    private static void inEffect(PlayerEntity player) {
        //FlightAnchor.getLogger().debug("TileObserve (inEffect): " + player.getPersistentData().getBoolean("flight_anchor_range_out"));
        player.getPersistentData().putBoolean("flight_anchor_range_out", false);
    }

    private static void applyLimitedFlight(PlayerEntity player) {
        player.getPersistentData().putInt("flight_limit", 200);
        //FlightAnchor.getLogger().debug("TileObserve (Applier): " + player.getPersistentData().getInt("flight_limit"));
    }

    private static boolean shouldApplyFlightStatus(PlayerEntity player) {
        return player.getPersistentData().getInt("flight_limit") < 100;
    }
}
