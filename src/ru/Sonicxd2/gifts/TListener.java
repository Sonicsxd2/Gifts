/*
 * The MIT License
 *
 * Copyright 2016 Yaroslav.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ru.Sonicxd2.gifts;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Yaroslav
 */
public class TListener implements Listener{
    @EventHandler
    public void onInteractAtBlock(PlayerInteractEvent e){
        if((e.getClickedBlock()!=null)&&(e.getClickedBlock().getType()==Material.WOOL)){
            Gift g=Main.instance.getGift(e.getClickedBlock().getLocation());
            if(g!=null){
                if(Main.instance.pc.hasGift(e.getPlayer().getName(), g.getId())){
                    e.getPlayer().sendMessage("У тебя уже есть этот подарок!");
                    return;
                }
                Main.instance.pc.giveGift(e.getPlayer().getName(), g.getId());
                e.getPlayer().sendMessage("Вы получили подарок!");
                g.runCommand(e.getPlayer().getName()); //SPECIAL FOR https://vk.com/d.zenegix
                
            }
        }
    }
}
