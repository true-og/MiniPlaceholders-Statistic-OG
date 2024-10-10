# MiniPlaceholders-Statistics

Adds MiniPlaceholders for minecraft statistics. A fork of [Statistics-Expansion](https://github.com/carmMars/Statistics-Expansion) crafted by [TrueOG Network](https://true-og.net/).

**Available MiniPlaceholders:**

General Statistics

    <statistic_mine_block>
    Description: Total number of blocks mined by the player.
    Example Usage: Blocks Mined: <statistic_mine_block>
    Example Output: Blocks Mined: 1500

    <statistic_use_item>
    Description: Total number of items used by the player.
    Example Usage: Items Used: <statistic_use_item>
    Example Output: Items Used: 3000

    <statistic_break_item>
    Description: Total number of items broken by the player.
    Example Usage: Items Broken: <statistic_break_item>
    Example Output: Items Broken: 750

    <statistic_craft_item>
    Description: Total number of items crafted by the player.
    Example Usage: Items Crafted: <statistic_craft_item>
    Example Output: Items Crafted: 500

    <statistic_<statistic>>
    Description: Retrieves the value of an untimed statistic.
    Example Usage: Custom Stat: <statistic_kills>
    Example Output: Custom Stat: 25

Time Played

    <statistic_time_played>
    Description: Formatted total playtime (e.g., 1d 2h 30m).
    Example Usage: Play Time: <statistic_time_played>
    Example Output: Play Time: 1d 2h 30m

    <statistic_time_played:seconds>
    Description: Seconds component of total playtime.
    Example Usage: Seconds: <statistic_time_played:seconds>
    Example Output: Seconds: 30

    <statistic_time_played:minutes>
    Description: Minutes component of total playtime.
    Example Usage: Minutes: <statistic_time_played:minutes>
    Example Output: Minutes: 30

    <statistic_time_played:hours>
    Description: Hours component of total playtime.
    Example Usage: Hours: <statistic_time_played:hours>
    Example Output: Hours: 2

    <statistic_time_played:days>
    Description: Days component of total playtime.
    Example Usage: Days: <statistic_time_played:days>
    Example Output: Days: 1

    <statistic_seconds_played>
    Description: Total seconds played.
    Example Usage: Total Seconds: <statistic_seconds_played>
    Example Output: Total Seconds: 9000

    <statistic_minutes_played>
    Description: Total minutes played.
    Example Usage: Total Minutes: <statistic_minutes_played>
    Example Output: Total Minutes: 150

    <statistic_hours_played>
    Description: Total hours played.
    Example Usage: Total Hours: <statistic_hours_played>
    Example Output: Total Hours: 2

    <statistic_days_played>
    Description: Total days played.
    Example Usage: Total Days: <statistic_days_played>
    Example Output: Total Days: 1

Time Since Last Death

    <statistic_time_since_death>
    Description: Formatted time since the player's last death (e.g., 3h 15m).
    Example Usage: Time Since Death: <statistic_time_since_death>
    Example Output: Time Since Death: 3h 15m

    <statistic_seconds_since_death>
    Description: Seconds since the player's last death.
    Example Usage: Seconds Since Death: <statistic_seconds_since_death>
    Example Output: Seconds Since Death: 900

    <statistic_minutes_since_death>
    Description: Minutes since the player's last death.
    Example Usage: Minutes Since Death: <statistic_minutes_since_death>
    Example Output: Minutes Since Death: 15

    <statistic_hours_since_death>
    Description: Hours since the player's last death.
    Example Usage: Hours Since Death: <statistic_hours_since_death>
    Example Output: Hours Since Death: 3

    <statistic_days_since_death>
    Description: Days since the player's last death.
    Example Usage: Days Since Death: <statistic_days_since_death>
    Example Output: Days Since Death: 0

Dynamic Statistics with Arguments

MiniPlaceholders-Statistics allows for dynamic placeholders where you can specify the statistic and optionally the material or entity type.
Single Statistic

    <statistic_<statistic>>
    Description: Retrieves the value of an untimed statistic.
    Example Usage: Kills: <statistic_KILLS>
    Example Output: Kills: 25

Statistic with Material or Entity

    <statistic_<statistic>:<material/entity>>
    Description: Retrieves the statistic value for a specific material or entity.
    Example Usage: Zombie Kills: <statistic_ENTITY_KILL:zombie>
    Example Output: Zombie Kills: 10

Statistic with Multiple Materials or Entities

    <statistic_<statistic>:<type1>,<type2>,...>
    Description: Retrieves the total statistic value for multiple materials or entities.
    Example Usage: Total Stone Mined: <statistic_MINE_BLOCK:STONE,COBBLESTONE,DIORITE>
    Example Output: Total Stone Mined: 500

Note: Replace <statistic>, <material>, and <entity> with the appropriate identifiers. Statistics are case-insensitive but are typically in uppercase.
Placeholder Examples

    Total Blocks Mined: <statistic_mine_block>
    Total Items Used: <statistic_use_item>
    Total Play Time: <statistic_time_played>
    Hours Played: <statistic_time_played:hours>
    Time Since Last Death: <statistic_time_since_death>
    Total Zombie Kills: <statistic_ENTITY_KILL:zombie>
    Total Stone and Cobblestone Mined: <statistic_MINE_BLOCK:STONE,COBBLESTONE>

License: GPLv3