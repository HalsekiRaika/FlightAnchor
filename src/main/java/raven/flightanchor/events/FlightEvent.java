package raven.flightanchor.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import raven.flightanchor.FlightAnchor;

public final class FlightEvent {
    private FlightEvent() { }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLimitedFlight(TickEvent.PlayerTickEvent event) {
        if (!event.player.isCreative() && !event.player.isSpectator()) {
            if (event.phase == TickEvent.Phase.END){
                if (isFlightLimit(event.player)) {
                    if (shouldSkipEvent(event.player)) {
                        event.player.abilities.flying = false;
                        event.player.abilities.mayfly = false;
                        skipEvent(event.player);
                    }
                } else {
                    event.player.abilities.mayfly = true;
                    decreaseLimitedFlightTime(event.player);
                }
            }
        }
    }

    private static void skipEvent(PlayerEntity player) {
        player.getPersistentData().putBoolean("flight_anchor_range_out", true);
    }

    private static boolean shouldSkipEvent(PlayerEntity player) {
        //FlightAnchor.getLogger().debug("EventObserve (isSkipped): " + player.getPersistentData().getBoolean("flight_anchor_range_out"));
        return !player.getPersistentData().getBoolean("flight_anchor_range_out");
    }

    private static void decreaseLimitedFlightTime(PlayerEntity player) {
        //FlightAnchor.getLogger().debug("EventObserve (Flight Time): " + player.getPersistentData().getInt("flight_limit"));
        player.getPersistentData().putInt("flight_limit",
                player.getPersistentData().getInt("flight_limit") - 1);
    }

    private static boolean isFlightLimit(PlayerEntity player) {
        //FlightAnchor.getLogger().debug("EventObserve (canFlight): " + (player.getPersistentData().getInt("flight_limit") == 0));
        return 0 == player.getPersistentData().getInt("flight_limit");
    }

    public static void register(IEventBus eventBus) {
        eventBus.addListener(FlightEvent::onLimitedFlight);
    }
}
