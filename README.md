# WorldCreator

#### Description: 
```
This repository contains the source code for a Minecraft plugin called WorldCreator.  
WorldCreator is a plugin designed to allow players to create and delete worlds using commands.
```

## Features:


- #### 💾 Automatic Save & Load :
Each world created by the player is saved in a `YAMLConfiguration` file and loaded at the server startup.


- #### 🛏️ Spawn Storage System:
When a player executes the `/setspawn` command, their location is automatically saved in a `YAMLConfiguration` file. 
The `/spawn` command then teleports them back to the saved location.

- #### 🌍 World Creation :
Worlds are created using the `WorldCreator` class from the Spigot API.


- #### 🗑️ World Deletion :
When a player deletes a world, all players logged into it are first teleported to the spawn.
Then, the world is unloaded and deleted. Once this process is executed, the world’s directory is erased using the Apache Commons IO Library.


## Commands:
Players can execute `/help WorldCreator` in-game to get a list of available plugin commands.


```
- /create-world <name> : command to create a world.
- /delete-world <name> : command to delete a world.
- /get-world : command to get the world where the player is.
- /list-world : command to get a list of the worlds of the server
- /spawn : command to teleport to the spawn
- /setspawn : command to set the spawn location
- /teleport-world <list | name> :
  -> <name> : teleport the player to the world specified
  -> <list> : allow the player to get a list of the different worlds (same as the /list-world command)
```


## Permissions:
Permissions for each command (can be set using plugins like [Luckperms](https://luckperms.net/)).


```
- /create-world -> worldcreator.create-world
- /delete-world -> worldcreator.delete-world
- /get-world -> worldcreator.get-world
- /list-world -> worldcreator.list-world
- /spawn -> worldcreator.spawn
- /setspawn -> worldcreator.setspawn
- /teleport-world -> worldcreator.teleport-world
```


## Aliases:
Each command has aliases (short versions of the command).


```
- /create-world <name> : /cw <name>
- /delete-world <name> : /dw <name>
- /get-world : /gw
- /list-world : /lw
- /spawn : /hub | /lobby
- /setspawn : /sethub | /setlobby
- /teleport-world <list | name> : /tpw <list | name>
```

Version: `1.0`

API Version: `1.8.8-R0.1-SNAPSHOT`

## Authors

- [@Gamordstrimer](https://github.com/Gamordstrimerr)
