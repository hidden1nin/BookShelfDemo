package com.hiddentech.bookshelfdemo;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void BlockPlace(BlockPlaceEvent blockPlaceEvent){

        if(blockPlaceEvent.getBlock().getType()!= Material.BOOKSHELF)return;

        blockPlaceEvent.getBlock().setType(Material.BROWN_STAINED_GLASS);

        new BookShelf(blockPlaceEvent.getBlock().getLocation());

    }
}
