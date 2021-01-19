package com.hiddentech.bookshelfdemo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BookShelfDemo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
