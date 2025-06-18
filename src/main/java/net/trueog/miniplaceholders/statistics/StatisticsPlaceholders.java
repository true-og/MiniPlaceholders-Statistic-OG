package net.trueog.miniplaceholders.statistics;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import net.trueog.utilitiesog.UtilitiesOG;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public class StatisticsPlaceholders {

    private static final Statistic SECONDS_PLAYED_STATISTIC = Statistic.PLAY_ONE_MINUTE;
    private static final Statistic SECONDS_SINCE_LAST_DEATH_STATISTIC = Statistic.TIME_SINCE_DEATH;

    StatisticsPlaceholders() {

        registerPlaceholders();
    }

    // Register the global and audience-specific MiniPlaceholders.
    public void registerPlaceholders() {

        // Block/item/entity-related MiniPlaceholders.
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_mine_block", player -> calculateTotal(player, Statistic.MINE_BLOCK));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_use_item", player -> calculateTotal(player, Statistic.USE_ITEM));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_break_item", player -> calculateTotal(player, Statistic.BREAK_ITEM));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_craft_item", player -> calculateTotal(player, Statistic.CRAFT_ITEM));

        // Time played MiniPlaceholders.
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_played", player -> formatTime(getSecondsPlayed(player)));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_played:seconds", player -> Integer.toString(getSecondsPlayed(player) % 60));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_played:minutes",
                player -> Long.toString(TimeUnit.SECONDS.toMinutes(getSecondsPlayed(player)) % 60));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_played:hours",
                player -> Long.toString(TimeUnit.SECONDS.toHours(getSecondsPlayed(player)) % 24));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_played:days",
                player -> Long.toString(TimeUnit.SECONDS.toDays(getSecondsPlayed(player))));

        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_seconds_played", player -> Integer.toString(getSecondsPlayed(player)));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_minutes_played",
                player -> Long.toString(TimeUnit.SECONDS.toMinutes(getSecondsPlayed(player))));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_hours_played", player -> Long.toString(TimeUnit.SECONDS.toHours(getSecondsPlayed(player))));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_days_played", player -> Long.toString(TimeUnit.SECONDS.toDays(getSecondsPlayed(player))));

        // Time since death MiniPlaceholders.
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_time_since_death", player -> formatTime(getSecondsSinceLastDeath(player)));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_seconds_since_death", player -> Integer.toString(getSecondsSinceLastDeath(player)));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_minutes_since_death",
                player -> Long.toString(TimeUnit.SECONDS.toMinutes(getSecondsSinceLastDeath(player))));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_hours_since_death",
                player -> Long.toString(TimeUnit.SECONDS.toHours(getSecondsSinceLastDeath(player))));
        UtilitiesOG.registerAudiencePlaceholder(
                "statistic_days_since_death",
                player -> Long.toString(TimeUnit.SECONDS.toDays(getSecondsSinceLastDeath(player))));

        // Register dynamic statistic MiniPlaceholders with arguments.
        registerMaterialEntityPlaceholders();

        // General statistic MiniPlaceholder without arguments.
        UtilitiesOG.registerAudiencePlaceholder("statistic_<statistic>", (player, args) -> {
            if (args.isEmpty()) {

                return "Statistic identifier is required!";
            }

            String statisticIdentifier = args.get(0).toUpperCase();

            return getStatistic(player, statisticIdentifier);
        });
    }

    private void registerMaterialEntityPlaceholders() {

        // MiniPlaceholder for <statistic_<Statistic>:<Material/Entity>>
        UtilitiesOG.registerAudiencePlaceholder("statistic_<statistic>:<material/entity>", (player, args) -> {
            if (args.size() < 2) {

                return "Invalid arguments! Usage: <statistic>:<material/entity>";
            }

            String statisticIdentifier = args.get(0).toUpperCase();
            String types = args.get(1).toUpperCase();

            Statistic statistic = getStatisticByName(statisticIdentifier);
            if (statistic == null) {

                return "Unknown statistic '" + statisticIdentifier + "'";
            }

            switch (statistic.getType()) {
                case BLOCK:
                case ITEM:
                    Material material = Material.getMaterial(types);
                    if (material != null) {

                        return Integer.toString(player.getStatistic(statistic, material));
                    }

                    break;
                case ENTITY:
                    EntityType entityType = EntityType.valueOf(types);
                    if (entityType != null) {

                        return Integer.toString(player.getStatistic(statistic, entityType));
                    }

                    break;
                default:
                    break;
            }

            return "Invalid material or entity type.";
        });

        // MiniPlaceholder for <statistic_<Statistic>:<Material/Entity>,<Material/Entity>,<Material/Entity>>
        UtilitiesOG.registerAudiencePlaceholder("statistic_<statistic>:<materials/entities>", (player, args) -> {
            if (args.size() < 2) {

                return "Invalid arguments! Usage: <statistic>:<materials/entities>";
            }

            String statisticIdentifier = args.get(0).toUpperCase();
            String[] typesArray = args.get(1).toUpperCase().split(",");

            Statistic statistic = getStatisticByName(statisticIdentifier);
            if (statistic == null) {

                return "Unknown statistic '" + statisticIdentifier + "'";
            }

            AtomicInteger total = new AtomicInteger();
            switch (statistic.getType()) {
                case BLOCK:
                case ITEM:
                    for (String type : typesArray) {

                        Material material = Material.getMaterial(type);
                        if (material != null) {

                            total.addAndGet(player.getStatistic(statistic, material));
                        }
                    }

                    break;

                case ENTITY:
                    for (String type : typesArray) {

                        EntityType entityType = EntityType.valueOf(type);
                        if (entityType != null) {

                            total.addAndGet(player.getStatistic(statistic, entityType));
                        }
                    }

                    break;
                default:
                    break;
            }

            return Integer.toString(total.get());
        });
    }

    private String calculateTotal(OfflinePlayer player, Statistic statistic) {

        final AtomicLong total = new AtomicLong();
        for (Material material : Material.values()) {

            try {

                total.addAndGet(player.getStatistic(statistic, material));

            } catch (IllegalArgumentException ignored) {
            }
        }

        return Long.toString(total.get());
    }

    public static int getSecondsPlayed(final OfflinePlayer player) {

        return player.getStatistic(SECONDS_PLAYED_STATISTIC) / 20;
    }

    public static int getSecondsSinceLastDeath(final OfflinePlayer player) {

        return player.getStatistic(SECONDS_SINCE_LAST_DEATH_STATISTIC) / 20;
    }

    private Statistic getStatisticByName(String name) {

        try {

            return Statistic.valueOf(name);

        } catch (IllegalArgumentException e) {

            return null;
        }
    }

    private String getStatistic(OfflinePlayer player, String identifier) {

        Optional<Statistic> optional = Optional.ofNullable(getStatisticByName(identifier.toUpperCase()));
        if (!optional.isPresent()) {

            return "Unknown statistic '" + identifier + "'";
        }

        Statistic statistic = optional.get();
        if (statistic.getType() != Statistic.Type.UNTYPED) {

            return "The statistic '" + identifier + "' requires an argument.";
        }

        int value = player.getStatistic(statistic);

        return Integer.toString(value);
    }

    public static String formatTime(final long time) {

        if (time < 1) {

            return "";
        }

        long seconds = time;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long weeks = days / 7;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;
        days %= 7;

        StringJoiner joiner = new StringJoiner(" ");

        if (weeks > 0) joiner.add(weeks + "w");
        if (days > 0) joiner.add(days + "d");
        if (hours > 0) joiner.add(hours + "h");
        if (minutes > 0) joiner.add(minutes + "m");
        if (seconds > 0) joiner.add(seconds + "s");

        return joiner.toString().trim();
    }
}
