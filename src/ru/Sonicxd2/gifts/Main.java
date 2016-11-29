/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Sonicxd2.gifts;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Sonicxd2
 */
public class Main extends JavaPlugin{
    static Main instance;
    Gift[] gifts;
    PlayerConfig pc;
    @Override
    public void onLoad() {
        instance=this;
        super.onLoad(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void loadGifts(){
        File f=new File(getDataFolder(), "gifts.yml");
        f.mkdirs(); //CREATE ALL FOLDERS
        if(!f.exists()) try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        YamlConfiguration yc=YamlConfiguration.loadConfiguration(f);
        int amount=yc.getInt("Amount"); //Мне реально лень разделять конфиг, поэтому так:3
        gifts=new Gift[amount];
        for(int i=0;i<amount;i++){
            gifts[i]=new Gift(i,new Location(Bukkit.getWorld(yc.getString(i+".World")),
                    yc.getInt(i+".X"), yc.getInt(i+".Y"), yc.getInt(i+".Z")),yc.getString(i+".Command")); //Страшная строка, ооочень страшная)
        }
    }
    
    public Gift getGift(Location loc){
        for(int i=0;i<gifts.length;i++){
            if(gifts[i].getLoc().equals(loc)) return gifts[i];
        }
        return null;
    }
    
    @Override
    public void onEnable() {
        //Plugin is not working normal. Please recode him!
        loadGifts();
        pc=new PlayerConfig();
        getServer().getPluginManager().registerEvents(new TListener(), this);
        super.onEnable(); //To change body of generated methods, choose Tools | Templates.
    }
       
    
    
}
