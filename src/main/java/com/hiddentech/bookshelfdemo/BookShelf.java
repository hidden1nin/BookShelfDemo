package com.hiddentech.bookshelfdemo;

import com.hiddentech.grid.GridPlugin;
import com.hiddentech.grid.events.PlayerObjectRangeEvent;
import com.hiddentech.grid.objects.Holos.SingleHologram;
import com.hiddentech.grid.objects.Ranged;
import com.hiddentech.grid.objects.block.Destroyable;
import com.hiddentech.grid.objects.block.Interactable;
import com.hiddentech.grid.objects.ticking.Ticking;
import com.hiddentech.grid.utilities.Hologram;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BookShelf implements SingleHologram, Interactable, Destroyable, Ranged, Ticking {
    private Location location;
    private Boolean loaded;
    private Entity holo;
    public BookShelf(Location location){
        this.location = location;
        this.loaded = false;
        GridPlugin.getGridAPI().insertObject(this);
        loadHolograms();
        load();
        GridPlugin.getGridHandler().getLoaded().put(this, (short) 2);
    }

    @Override
    public Entity currentHolograms() {
        return this.holo;
    }

    @Override
    public void loadHolograms() {
        this.holo = Hologram.createItemHologram(this.location.clone().add(.5,-.3,.5),"",new ItemStack(Material.BOOKSHELF));

        ((ArmorStand)this.holo).setSmall(true);
    }

    @Override
    public void unloadHolograms() {
        GridPlugin.getHologramHandler().unload(this.holo);
        this.holo.remove();
    }

    @Override
    public void run(BlockBreakEvent blockBreakEvent) {
        destroy();
        blockBreakEvent.setCancelled(false);
        blockBreakEvent.setDropItems(false);
        blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(),new ItemStack(Material.BOOKSHELF, 1));

    }

    @Override
    public Block getBlock() {
        return this.location.getBlock();
    }

    @Override
    public Boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public void load() {
        this.loaded = true;
    }

    @Override
    public void unload() {

        this.loaded = false;
    }

    @Override
    public void destroy() {
        unload();
        unloadHolograms();
        GridPlugin.getGridAPI().removeObject(this);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void run(PlayerInteractEvent playerInteractEvent) {
        playerInteractEvent.getPlayer().sendMessage(ChatColor.GRAY+"BOOK TEXT HERE");

        ((ArmorStand)this.holo).setSmall(false);

        this.holo.teleport(location.clone().add(.5,-1.3,.5));
    }

    @Override
    public int getDistance() {
        return 3;
    }

    @Override
    public void run(PlayerObjectRangeEvent playerObjectRangeEvent) {
        ((ArmorStand)this.holo).setSmall(false);
        this.holo.teleport(location.clone().add(.5,-1.3,.5));
    }

    @Override
    public void tick() {
        ((ArmorStand)this.holo).setSmall(true);

        this.holo.teleport(location.clone().add(.5,-.3,.5));
    }
}
