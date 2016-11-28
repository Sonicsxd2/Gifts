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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Yaroslav
 */
public class PlayerConfig {
    YamlConfiguration yc;

    public PlayerConfig() {
        loadConfig();
    }
    
    public void loadConfig(){
        File f=new File(Main.instance.getDataFolder(), "players.yml");
        f.mkdirs(); //CREATE ALL FOLDERS
        if(!f.exists()) try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        yc=YamlConfiguration.loadConfiguration(f);
    }
    public void saveConfig(){
        File f=new File(Main.instance.getDataFolder(), "players.yml");
        try {
            yc.save(f);
        } catch (IOException ex) {
            Logger.getLogger(PlayerConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean hasGift(String player,int numb){
        return yc.getBoolean(player+"."+numb, false);
    }
    
    public void giveGift(String player, int numb){
        yc.set(player+"."+numb, true);
    }
}
