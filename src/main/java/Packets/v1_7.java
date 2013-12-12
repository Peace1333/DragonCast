/*     */ package Packets;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;

import org.bukkit.Location;
/*     */ 
/*     */ public class v1_7 extends FakeDragon
/*     */ {
/*     */   private Object dragon;
/*     */   private int id;
/*     */ 
/*     */   public v1_7(String name, Location loc)
/*     */   {
/*  25 */     super(name, loc);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ })
public Object getSpawnPacket()
/*     */   {
/*  30 */     Class<?> Entity = Util.getCraftClass("Entity");
/*  31 */     Class<?> EntityLiving = Util.getCraftClass("EntityLiving");
/*  32 */     Class<?> EntityEnderDragon = Util.getCraftClass("EntityEnderDragon");
/*  33 */     Object packet = null;
/*     */     try {
/*  35 */       this.dragon = EntityEnderDragon.getConstructor(new Class[] { Util.getCraftClass("World") }).newInstance(new Object[] { getWorld() });
/*     */ 
/*  37 */       Method setLocation = Util.getMethod(EntityEnderDragon, "setLocation", new Class[] { Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE });
/*  38 */       setLocation.invoke(this.dragon, new Object[] { Integer.valueOf(getX()), Integer.valueOf(getY()), Integer.valueOf(getZ()), Integer.valueOf(getPitch()), Integer.valueOf(getYaw()) });
/*     */ 
/*  40 */       Method setInvisible = Util.getMethod(EntityEnderDragon, "setInvisible", new Class[] { Boolean.TYPE });
/*  41 */       setInvisible.invoke(this.dragon, new Object[] { Boolean.valueOf(isVisible()) });
/*     */ 
/*  43 */       Method setCustomName = Util.getMethod(EntityEnderDragon, "setCustomName", new Class[] { String.class });
/*  44 */       setCustomName.invoke(this.dragon, new Object[] { this.name });
/*     */ 
/*  46 */       Method setHealth = Util.getMethod(EntityEnderDragon, "setHealth", new Class[] { Float.TYPE });
/*  47 */       setHealth.invoke(this.dragon, new Object[] { Float.valueOf(this.health) });
/*     */ 
/*  49 */       Field motX = Util.getField(Entity, "motX");
/*  50 */       motX.set(this.dragon, Byte.valueOf(getXvel()));
/*     */ 
/*  52 */       Field motY = Util.getField(Entity, "motX");
/*  53 */       motY.set(this.dragon, Byte.valueOf(getYvel()));
/*     */ 
/*  55 */       Field motZ = Util.getField(Entity, "motX");
/*  56 */       motZ.set(this.dragon, Byte.valueOf(getZvel()));
/*     */ 
/*  58 */       Method getId = Util.getMethod(EntityEnderDragon, "getId", new Class[0]);
/*  59 */       this.id = ((Integer)getId.invoke(this.dragon, new Object[0])).intValue();
/*     */ 
/*  61 */       Class<?> PacketPlayOutSpawnEntityLiving = Util.getCraftClass("PacketPlayOutSpawnEntityLiving");
/*     */ 
/*  63 */       packet = PacketPlayOutSpawnEntityLiving.getConstructor(new Class[] { EntityLiving }).newInstance(new Object[] { this.dragon });
/*     */     } catch (IllegalArgumentException e) {
/*  65 */       e.printStackTrace();
/*     */     } catch (SecurityException e) {
/*  67 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/*  69 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  71 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/*  73 */       e.printStackTrace();
/*     */     } catch (NoSuchMethodException e) {
/*  75 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  78 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getDestroyPacket()
/*     */   {
/*  83 */     Class<?> PacketPlayOutEntityDestroy = Util.getCraftClass("PacketPlayOutEntityDestroy");
/*     */ 
/*  85 */     Object packet = null;
/*     */     try {
/*  87 */       packet = PacketPlayOutEntityDestroy.newInstance();
/*  88 */       Field a = PacketPlayOutEntityDestroy.getDeclaredField("a");
/*  89 */       a.setAccessible(true);
/*  90 */       a.set(packet, new int[] { this.id });
/*     */     } catch (SecurityException e) {
/*  92 */       e.printStackTrace();
/*     */     } catch (NoSuchFieldException e) {
/*  94 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/*  96 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  98 */       e.printStackTrace();
/*     */     } catch (IllegalArgumentException e) {
/* 100 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 103 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getMetaPacket(Object watcher)
/*     */   {
/* 108 */     Class<?> DataWatcher = Util.getCraftClass("DataWatcher");
/*     */ 
/* 110 */     Class<?> PacketPlayOutEntityMetadata = Util.getCraftClass("PacketPlayOutEntityMetadata");
/*     */ 
/* 112 */     Object packet = null;
/*     */     try {
/* 114 */       packet = PacketPlayOutEntityMetadata.getConstructor(new Class[] { Integer.TYPE, DataWatcher, Boolean.TYPE }).newInstance(new Object[] { Integer.valueOf(this.id), watcher, Boolean.valueOf(true) });
/*     */     } catch (IllegalArgumentException e) {
/* 116 */       e.printStackTrace();
/*     */     } catch (SecurityException e) {
/* 118 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/* 120 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 122 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 124 */       e.printStackTrace();
/*     */     } catch (NoSuchMethodException e) {
/* 126 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 129 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getTeleportPacket(Location loc)
/*     */   {
/* 134 */     Class<?> PacketPlayOutEntityTeleport = Util.getCraftClass("PacketPlayOutEntityTeleport");
/*     */ 
/* 136 */     Object packet = null;
/*     */     try
/*     */     {
/* 139 */       packet = PacketPlayOutEntityTeleport.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Byte.TYPE, Byte.TYPE }).newInstance(new Object[] { Integer.valueOf(this.id), Integer.valueOf(loc.getBlockX() * 32), Integer.valueOf(loc.getBlockY() * 32), Integer.valueOf(loc.getBlockZ() * 32), Byte.valueOf((byte)((int)loc.getYaw() * 256 / 360)), Byte.valueOf((byte)((int)loc.getPitch() * 256 / 360)) });
/*     */     } catch (IllegalArgumentException e) {
/* 141 */       e.printStackTrace();
/*     */     } catch (SecurityException e) {
/* 143 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/* 145 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 147 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 149 */       e.printStackTrace();
/*     */     } catch (NoSuchMethodException e) {
/* 151 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 154 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getWatcher()
/*     */   {
/* 159 */     Class<?> Entity = Util.getCraftClass("Entity");
/* 160 */     Class<?> DataWatcher = Util.getCraftClass("DataWatcher");
/*     */ 
/* 162 */     Object watcher = null;
/*     */     try {
/* 164 */       watcher = DataWatcher.getConstructor(new Class[] { Entity }).newInstance(new Object[] { this.dragon });
/* 165 */       Method a = Util.getMethod(DataWatcher, "a", new Class[] { Integer.TYPE, Object.class });
/*     */ 
/* 167 */       a.invoke(watcher, new Object[] { Integer.valueOf(0), Byte.valueOf((byte) (isVisible() ? 0 : 32)) });
/* 168 */       a.invoke(watcher, new Object[] { Integer.valueOf(6), Float.valueOf(this.health) });
/* 169 */       a.invoke(watcher, new Object[] { Integer.valueOf(7), Integer.valueOf(0) });
/* 170 */       a.invoke(watcher, new Object[] { Integer.valueOf(8), Byte.valueOf((byte) 0) });
/* 171 */       a.invoke(watcher, new Object[] { Integer.valueOf(10), this.name });
/* 172 */       a.invoke(watcher, new Object[] { Integer.valueOf(11), Byte.valueOf((byte) 1) });
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/* 175 */       e.printStackTrace();
/*     */     }
/*     */     catch (SecurityException e) {
/* 178 */       e.printStackTrace();
/*     */     }
/*     */     catch (InstantiationException e) {
/* 181 */       e.printStackTrace();
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 184 */       e.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 187 */       e.printStackTrace();
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 190 */       e.printStackTrace();
/*     */     }
/* 192 */     return watcher;
/*     */   }
/*     */ }

/* Location:           C:\Users\Michael\Downloads\BarAPI.jar
 * Qualified Name:     me.confuser.barapi.nms.v1_7
 * JD-Core Version:    0.6.2
 */