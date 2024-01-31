# ArmorEquipEvent & ArmorUnequipEvent

This repository contains two new events for the SpigotAPI, these are the ArmorEquipEvent and ArmorUnequipEvent
Which are traditionally not already in the plugin.

Small parts of the code were taken from the original [ArmorEquipEvent](https://github.com/Arnuh/ArmorEquipEvent) by Arnah

## Usage example
```java
    @EventHandler
    public void onArmorApply(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemStack();
        
        if (!player.isOp()) {
            event.setCancelled(true);
            return;
        }
        
        player.sendMessage("You have successfully applied " + itemStack.getType());
    }

    @EventHandler
    public void onArmorRemove(ArmorUnequipEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemStack();

        player.sendMessage("You have successfully applied " + itemStack.getType());
    }
```

## How to import this library

Current Version:
![Release](https://jitpack.io/v/Splodgebox/armorequip.svg)

If you are using Gradle, you can use the following:
```groovy
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}


	dependencies {
	        implementation 'com.github.Splodgebox:armorequip:VERSION-ABOVE'
	}
```

If you are using Maven, you can use the following:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.Splodgebox</groupId>
	    <artifactId>armorequip</artifactId>
	    <version>VERSION-ABOVE</version>
	</dependency>
```

[<img alt="alt_text" height="300" src="https://i.imgur.com/q8RbwQP.png" />](https://gamersupps.gg/splodgebox)
