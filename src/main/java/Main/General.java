/*     */ package Main;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;

import Packets.FakeDragon;
import Packets.Util;
/*     */ 
/*     */ public class General
/*     */ {
/*  17 */   public static Integer ENTITY_ID = Integer.valueOf(6000);
/*  18 */   public static Map<String, FakeDragon> players = new HashMap<String, FakeDragon>();
/*     */ 
/*     */   public static void setStatus(Player player, String text, int health) {
/*  22 */     if (players.containsKey(player.getName())) {     } else {
    FakeDragon dragon1 = getDragon(player, text);

    dragon1.name = cleanMessage(text);
    dragon1.health = 200.0F;

    sendDragon(dragon1, player);
/*  29 */       players.put(player.getName(), dragon1);
/*     */     }
/*     */ 
/*  32 */     if (text == "") {
	FakeDragon dragon1 = getDragon(player, text);
/*  33 */       Object destroyPacket = dragon1.getDestroyPacket();
/*  34 */       sendPacket(player, destroyPacket);
/*     */ 
/*  36 */       players.remove(player.getName());
/*     */     } else {
	FakeDragon dragon1 = getDragon(player, text);
/*  38 */       dragon1.name = text;
/*  39 */       dragon1.health = (health / 100.0F * 200.0F);
/*  40 */       Object metaPacket = dragon1.getMetaPacket(dragon1.getWatcher());
/*  41 */       Object teleportPacket = dragon1.getTeleportPacket(player.getLocation().add(0.0D, -200.0D, 0.0D));
/*  42 */       sendPacket(player, metaPacket);
/*  43 */       sendPacket(player, teleportPacket);
/*     */     }
/*     */   }
public static boolean hasBar(Player player) {
    return players.get(player.getName()) != null;
  }

  public static void removeBar(Player player) {
    if (!hasBar(player)) {
      return;
    }
    Util.sendPacket(player, getDragon(player, "").getDestroyPacket());

    players.remove(player.getName());
  }

  public static void setHealth(Player player, float percent) {
    if (!hasBar(player)) {
      return;
    }
    FakeDragon dragon = getDragon(player, "");
    dragon.health = (percent / 100.0F * 200.0F);
    sendDragon(dragon, player);
  }

  public static float getHealth(Player player) {
    if (!hasBar(player)) {
      return -1.0F;
    }
    return getDragon(player, "").health;
  }

  public static String getMessage(Player player) {
    if (!hasBar(player)) {
      return "";
    }
    return getDragon(player, "").name;
  }

  private static String cleanMessage(String message) {
    if (message.length() > 64) {
      message = message.substring(0, 63);
    }
    return message;
  }

  private static void sendDragon(FakeDragon dragon, Player player)
  {
    Util.sendPacket(player, dragon.getMetaPacket(dragon.getWatcher()));
    Util.sendPacket(player, dragon.getTeleportPacket(player.getLocation().add(0.0D, -200.0D, 0.0D)));
  }

  private static FakeDragon getDragon(Player player, String message) {
    if (hasBar(player)) {
      return (FakeDragon)players.get(player.getName());
    }
    return addDragon(player, cleanMessage(message));
  }

  private static FakeDragon addDragon(Player player, String message) {
    FakeDragon dragon = Util.newDragon(message, player.getLocation().add(0.0D, -200.0D, 0.0D));

    Util.sendPacket(player, dragon.getSpawnPacket());

    players.put(player.getName(), dragon);

    return dragon;
  }

  @SuppressWarnings("unused")
private static FakeDragon addDragon(Player player, Location loc, String message) {
    FakeDragon dragon = Util.newDragon(message, loc.add(0.0D, -200.0D, 0.0D));

    Util.sendPacket(player, dragon.getSpawnPacket());

    players.put(player.getName(), dragon);

    return dragon;
  }

/*     */   public static void displayDragonTextBar(Plugin plugin, String text, final Player player, long length, final int health) {
/*  48 */     setStatus(player, text, 100);
/*     */ 
/*  50 */     new BukkitRunnable()
/*     */     {
/*     */       public void run() {
/*  53 */         General.setStatus(player, "", health);
/*     */       }
/*     */     }
/*  56 */     .runTaskLater(plugin, length);
/*     */   }
/*     */ 
/*     */   public static void displayDragonLoadingBar(final Plugin plugin, final String text, final String completeText, final Player player, final int healthAdd, long delay, final boolean loadUp)
/*     */   {
/*  61 */     setStatus(player, "", loadUp ? 1 : 100);
/*     */ 
/*  63 */     new BukkitRunnable() {
/*  64 */       int health = healthAdd != 0 ? 1 : 100;
/*     */ 
/*     */       public void run()
/*     */       {
/*  68 */         if (loadUp ? this.health < 100 : this.health > 1) {
/*  69 */           General.setStatus(player, text, this.health);
/*  70 */           if (loadUp)
/*  71 */             this.health += healthAdd;
/*     */           else
/*  73 */             this.health -= healthAdd;
/*     */         }
/*     */         else {
/*  76 */           General.setStatus(player, completeText, loadUp ? 100 : 1);
/*  77 */           new BukkitRunnable()
/*     */           {
/*     */             public void run() {
/*  80 */               General.setStatus(player, "", loadUp ? 100 : 1);
/*     */             }
/*     */           }
/*  83 */           .runTaskLater(plugin, 20L);
/*     */ 
/*  85 */           cancel();
/*     */         }
/*     */       }
/*     */     }
/*  89 */     .runTaskTimer(plugin, delay, delay);
/*     */   }
/*     */ 
/*     */   public static void displayDragonLoadingBar(Plugin plugin, String text, String completeText, Player player, int secondsDelay, boolean loadUp)
/*     */   {
/*  94 */     int healthChangePerSecond = 100 / secondsDelay / 4;
/*     */ 
/*  96 */     displayDragonLoadingBar(plugin, text, completeText, player, healthChangePerSecond, 5L, loadUp);
/*     */   }
/*     */ 
/*     */   public static void sendPacket(Player p, Object packet)
/*     */   {
/*     */     try
/*     */     {
/* 103 */       Object nmsPlayer = getHandle(p);
/* 104 */       Field con_field = nmsPlayer.getClass().getField("playerConnection");
/* 105 */       Object con = con_field.get(nmsPlayer);
/* 106 */       Method packet_method = getMethod(con.getClass(), "sendPacket");
/* 107 */       packet_method.invoke(con, new Object[] { packet });
/*     */     } catch (SecurityException e) {
/* 109 */       e.printStackTrace();
/*     */     } catch (IllegalArgumentException e) {
/* 111 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 113 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 115 */       e.printStackTrace();
/*     */     } catch (NoSuchFieldException e) {
/* 117 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Class<?> getCraftClass(String ClassName) {
/* 122 */     String name = Bukkit.getServer().getClass().getPackage().getName();
/* 123 */     String version = name.substring(name.lastIndexOf('.') + 1) + ".";
/* 124 */     String className = "net.minecraft.server." + version + ClassName;
/* 125 */     Class<?> c = null;
/*     */     try {
/* 127 */       c = Class.forName(className);
/*     */     } catch (ClassNotFoundException e) {
/* 129 */       e.printStackTrace();
/*     */     }
/* 131 */     return c;
/*     */   }
/*     */ 
/*     */   public static Object getHandle(Entity entity) {
/* 135 */     Object nms_entity = null;
/* 136 */     Method entity_getHandle = getMethod(entity.getClass(), "getHandle");
/*     */     try {
/* 138 */       nms_entity = entity_getHandle.invoke(entity, new Object[0]);
/*     */     } catch (IllegalArgumentException e) {
/* 140 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 142 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 144 */       e.printStackTrace();
/*     */     }
/* 146 */     return nms_entity;
/*     */   }
/*     */ 
/*     */   public static Field getField(Class<?> cl, String field_name) {
/*     */     try {
/* 151 */       return cl.getDeclaredField(field_name);
/*     */     }
/*     */     catch (SecurityException e) {
/* 154 */       e.printStackTrace();
/*     */     } catch (NoSuchFieldException e) {
/* 156 */       e.printStackTrace();
/*     */     }
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method, Class<?>[] args) {
/* 162 */     for (Method m : cl.getMethods()) {
/* 163 */       if ((m.getName().equals(method)) && (ClassListEqual(args, m.getParameterTypes()))) {
/* 164 */         return m;
/*     */       }
/*     */     }
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method, Integer args) {
/* 171 */     for (Method m : cl.getMethods()) {
/* 172 */       if ((m.getName().equals(method)) && (args.equals(new Integer(m.getParameterTypes().length)))) {
/* 173 */         return m;
/*     */       }
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method) {
/* 180 */     for (Method m : cl.getMethods()) {
/* 181 */       if (m.getName().equals(method)) {
/* 182 */         return m;
/*     */       }
/*     */     }
/* 185 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
/* 189 */     boolean equal = true;
/*     */ 
/* 191 */     if (l1.length != l2.length) return false;
/* 192 */     for (int i = 0; i < l1.length; i++) {
/* 193 */       if (l1[i] != l2[i]) { equal = false; break;
/*     */       }
/*     */     }
/* 196 */     return equal;
/*     */   }
/*     */ }

/* Location:           C:\Users\Michael\Desktop\RageMode.jar
 * Qualified Name:     me.Peace1333.RageMode.API.bar.General
 * JD-Core Version:    0.6.2
 */