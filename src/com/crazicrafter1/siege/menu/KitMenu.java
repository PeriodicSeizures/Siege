package com.crazicrafter1.siege.menu;

import com.crazicrafter1.siege.Main;
import com.crazicrafter1.siege.game.TeamAssigner;
import com.crazicrafter1.siege.game.TeamUtils;
import com.crazicrafter1.siege.game.TempPlayer;
import com.crazicrafter1.siege.game.team.Team;
import com.crazicrafter1.siege.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class KitMenu {

    public static HashMap<UUID, KitMenu> menus = new HashMap<>();

    private final static int kitSlot = 13;

    private UUID uuid;
    private Team.Type type;

    private Inventory inventory;


    public KitMenu(Player player) {
        this.uuid = player.getUniqueId();
        TempPlayer assigned = TeamAssigner.getAssigned(player.getUniqueId());
        type = assigned.getType(); //.matchType(kit);

        this.inventory = KitMenuData.getDefaultInventory();
        this.inventory.setItem(kitSlot, KitMenuData.itemByKit.get(assigned.getKit()));
        player.openInventory(inventory);
    }

    /*
    public Team.Kit getKit() {
        return kit;
    }

     */


    // abstractive selector
    private void selectTeamKit(int slot) {

        if (KitMenuData.itemBySlot.containsKey(slot)) {
            Team.Kit kit = KitMenuData.kitBySlot.get(slot);
            TempPlayer assigned = TeamAssigner.getAssigned(this.uuid);
            // Get
            if (TeamUtils.matchType(kit) == this.type) {
                //this.kit = kit;
                //inventory.setItem(kitSlot, KitMenuData.itemBySlot.get(slot));
                TeamAssigner.setKit(uuid, kit);

                assigned.unQueue();

            } else { // Other is selected regardless of queue

                // Test if clicked on already queued

                if (assigned != null) {

                    if (assigned.getQueued() == null) {
                        // then select queue
                        TeamAssigner.addToQueue(new TempPlayer(uuid, type, kit));

                        //inventory.setItem(slot, null);
                    } else {
                        if (assigned.getQueued().getKit() == kit) {

                            // then unqueue
                            TeamAssigner.removeFromQueue(new TempPlayer(uuid, type, kit));
                        } else TeamAssigner.addToQueue(new TempPlayer(uuid, type, kit));
                    }

                }



                //TeamAssigner.
            }

            System.out.println("Team : " + assigned.getType() + ", kit : " + assigned.getKit() + ", queued : " + (assigned.getQueued() != null));
            if (assigned.getQueued() != null) {
                System.out.println("Queued kit : " + assigned.getQueued().getKit());
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    updateItems();
                }
            }.runTaskLater(Main.getInstance(), 1);
        }
    }


    /**
     * SELECT KIT (NOT QUEUE)
     */

/*
    private void selectQueuedKit(Team.Kit kit) {
        // find that kit slot, then make glow
        // ALSO, remove glow from all other kits if there are any
        for (Integer slot : KitMenuData.itemBySlot.keySet()) {

            // remove all other glows from inventory
            inventory.setItem(slot, ItemUtil.removeGlow(KitMenuData.itemBySlot.get(slot)));

        }
    }


 */
    private void resetKitItems() {
        for (Integer slot : KitMenuData.itemBySlot.keySet()) {
            inventory.setItem(slot, null);
            inventory.setItem(slot, KitMenuData.itemBySlot.get(slot));
        }
    }

    /**
     * updated when kit changed / ...
     */
    private void updateItems() {
        //inventory.na

        resetKitItems();
        // get Team
        TempPlayer player = TeamAssigner.getAssigned(this.uuid);

        if (player == null) return;

        // Select active kit
        inventory.setItem(kitSlot, KitMenuData.itemByKit.get(player.getKit()));

        // Select queued kit (if applicable)
        if (player.getQueued() != null) {

            // Pre-reset kit items

            int slot = KitMenuData.slotByKit.get(player.getQueued().getKit());

            // Now glow queued kit
            ItemStack makeGlow = inventory.getItem(slot);
            inventory.setItem(slot, ItemBuilder.builder(makeGlow).glow().toItem());

            System.out.println("Slot-Queued : " + slot);

            //this.selectQueuedKit(player.getQueued().getKit());
            // then select queued kit too, but in actual:
        }

    }

    /**
     * called on EventListener#event(...)
     */
    public void onInteract(InventoryClickEvent e) {

        int slot = e.getRawSlot();

        /*
         * if click on specific slot, event ... OR SPECIFIC ITEM
         */

        selectTeamKit(slot);

        e.setCancelled(true);
    }

}
