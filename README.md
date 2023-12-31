# NXStrikePracticeBotFix

## Description
NXStrikePracticeBotFix is a Bukkit plugin that fixes the issue where players lose to bots on StrikePractice servers. The plugin listens for the `PlayerDeathEvent` and checks if the player was killed by a non-player entity (i.e. a bot) using the StrikePractice API. If the killer is a bot, the plugin uses the `getLose()` method to make the victim player lose the PvP match.

The plugin also registers a `/nxpracticebotfix` command that can be used to show a description of the plugin and reload the `config.yml` file. The `config.yml` file can be edited to change the plugin's behavior.

## Installation
~~1. Download the latest release of the plugin from the Releases page on GitHub.~~
1. Download all the files, and use Maven to complie it into a .jar file.

Command is: `maven clean package`
For some reason I cant complie it for you guys.
`[ERROR] Failed to execute goal on project NXStrikePracticeBotFix: Could not resolve dependencies for project com.example:NXStrikePracticeBotFix:jar:1.0: The following artifacts could not be resolved: org.bukkit:bukkit:jar:1.17.1-R0.1-SNAPSHOT (absent), net.frozenorb:StrikePracticeAPI:jar:1.1.4-SNAPSHOT (absent): Could not find artifact org.bukkit:bukkit:jar:1.17.1-R0.1-SNAPSHOT -> [Help 1]
[ERROR] `

2. Place the ~~downloaded~~ complied JAR file in the `plugins` folder of your Bukkit server.
3. Start or restart your Bukkit server.

## Usage
The plugin automatically listens for the `PlayerDeathEvent` and checks if the player was killed by a bot. If the killer is a bot, the plugin uses the StrikePractice API to make the victim player lose the PvP match.

The plugin also registers a `/nxpracticebotfix` command that can be used to show a description of the plugin and reload the `config.yml` file. To use the command, type `/nxpracticebotfix` in the chat. If you have the permission `nxpracticebotfix.reload`, you can use the command `/nxpracticebotfix reload` to reload the `config.yml` file.

## Configuration
The plugin generates a `config.yml` file in the plugin's folder, called `NXStrikePracticeBotFix`. You can edit this file to change the plugin's behavior. The following options are available:

- `example-option`: An example option that you can modify.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
