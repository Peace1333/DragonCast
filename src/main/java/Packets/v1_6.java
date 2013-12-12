/*     */ package Packets;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;

/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ public class v1_6 extends FakeDragon
/*     */ {
/*  22 */   private static final Integer EntityID = Integer.valueOf(6000);
/*     */ 
/*     */   public v1_6(String name, Location loc) {
/*  25 */     super(name, loc);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings("deprecation")
public Object getSpawnPacket()
/*     */   {
/*  31 */     Class<?> mob_class = Util.getCraftClass("Packet24MobSpawn");
/*  32 */     Object mobPacket = null;
/*     */     try {
/*  34 */       mobPacket = mob_class.newInstance();
/*     */ 
/*  36 */       Field a = Util.getField(mob_class, "a");
/*  37 */       a.setAccessible(true);
/*  38 */       a.set(mobPacket, EntityID);
/*  39 */       Field b = Util.getField(mob_class, "b");
/*  40 */       b.setAccessible(true);
/*  41 */       b.set(mobPacket, Short.valueOf(EntityType.ENDER_DRAGON.getTypeId()));
/*     */ 
/*  43 */       Field c = Util.getField(mob_class, "c");
/*  44 */       c.setAccessible(true);
/*  45 */       c.set(mobPacket, Integer.valueOf(getX()));
/*  46 */       Field d = Util.getField(mob_class, "d");
/*  47 */       d.setAccessible(true);
/*  48 */       d.set(mobPacket, Integer.valueOf(getY()));
/*  49 */       Field e = Util.getField(mob_class, "e");
/*  50 */       e.setAccessible(true);
/*  51 */       e.set(mobPacket, Integer.valueOf(getZ()));
/*  52 */       Field f = Util.getField(mob_class, "f");
/*  53 */       f.setAccessible(true);
/*  54 */       f.set(mobPacket, Byte.valueOf((byte)(int)(getPitch() * 256.0F / 360.0F)));
/*  55 */       Field g = Util.getField(mob_class, "g");
/*  56 */       g.setAccessible(true);
/*  57 */       g.set(mobPacket, Byte.valueOf((byte)0));
/*     */ 
/*  59 */       Field h = Util.getField(mob_class, "h");
/*  60 */       h.setAccessible(true);
/*  61 */       h.set(mobPacket, Byte.valueOf((byte)(int)(getYaw() * 256.0F / 360.0F)));
/*  62 */       Field i = Util.getField(mob_class, "i");
/*  63 */       i.setAccessible(true);
/*  64 */       i.set(mobPacket, Byte.valueOf(getXvel()));
/*  65 */       Field j = Util.getField(mob_class, "j");
/*  66 */       j.setAccessible(true);
/*  67 */       j.set(mobPacket, Byte.valueOf(getYvel()));
/*  68 */       Field k = Util.getField(mob_class, "k");
/*  69 */       k.setAccessible(true);
/*  70 */       k.set(mobPacket, Byte.valueOf(getZvel()));
/*     */ 
/*  72 */       Object watcher = getWatcher();
/*  73 */       Field t = Util.getField(mob_class, "t");
/*  74 */       t.setAccessible(true);
/*  75 */       t.set(mobPacket, watcher);
/*     */     } catch (InstantiationException e1) {
/*  77 */       e1.printStackTrace();
/*     */     } catch (IllegalAccessException e1) {
/*  79 */       e1.printStackTrace();
/*     */     }
/*     */ 
/*  82 */     return mobPacket;
/*     */   }
/*     */ 
/*     */   public Object getDestroyPacket()
/*     */   {
/*  87 */     Class<?> packet_class = Util.getCraftClass("Packet29DestroyEntity");
/*  88 */     Object packet = null;
/*     */     try {
/*  90 */       packet = packet_class.newInstance();
/*     */ 
/*  92 */       Field a = Util.getField(packet_class, "a");
/*  93 */       a.setAccessible(true);
/*  94 */       a.set(packet, new int[] { EntityID.intValue() });
/*     */     } catch (InstantiationException e) {
/*  96 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  98 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 101 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getMetaPacket(Object watcher)
/*     */   {
/* 106 */     Class<?> packet_class = Util.getCraftClass("Packet40EntityMetadata");
/* 107 */     Object packet = null;
/*     */     try {
/* 109 */       packet = packet_class.newInstance();
/*     */ 
/* 111 */       Field a = Util.getField(packet_class, "a");
/* 112 */       a.setAccessible(true);
/* 113 */       a.set(packet, EntityID);
/*     */ 
/* 115 */       Method watcher_c = Util.getMethod(watcher.getClass(), "c");
/* 116 */       Field b = Util.getField(packet_class, "b");
/* 117 */       b.setAccessible(true);
/* 118 */       b.set(packet, watcher_c.invoke(watcher, new Object[0]));
/*     */     } catch (InstantiationException e) {
/* 120 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 122 */       e.printStackTrace();
/*     */     } catch (IllegalArgumentException e) {
/* 124 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 126 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 129 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getTeleportPacket(Location loc)
/*     */   {
/* 134 */     Class<?> packet_class = Util.getCraftClass("Packet34EntityTeleport");
/* 135 */     Object packet = null;
/*     */     try {
/* 137 */       packet = packet_class.newInstance();
/*     */ 
/* 139 */       Field a = Util.getField(packet_class, "a");
/* 140 */       a.setAccessible(true);
/* 141 */       a.set(packet, EntityID);
/* 142 */       Field b = Util.getField(packet_class, "b");
/* 143 */       b.setAccessible(true);
/* 144 */       b.set(packet, Integer.valueOf((int)Math.floor(loc.getX() * 32.0D)));
/* 145 */       Field c = Util.getField(packet_class, "c");
/* 146 */       c.setAccessible(true);
/* 147 */       c.set(packet, Integer.valueOf((int)Math.floor(loc.getY() * 32.0D)));
/* 148 */       Field d = Util.getField(packet_class, "d");
/* 149 */       d.setAccessible(true);
/* 150 */       d.set(packet, Integer.valueOf((int)Math.floor(loc.getZ() * 32.0D)));
/* 151 */       Field e = Util.getField(packet_class, "e");
/* 152 */       e.setAccessible(true);
/* 153 */       e.set(packet, Byte.valueOf((byte)(int)(loc.getYaw() * 256.0F / 360.0F)));
/* 154 */       Field f = Util.getField(packet_class, "f");
/* 155 */       f.setAccessible(true);
/* 156 */       f.set(packet, Byte.valueOf((byte)(int)(loc.getPitch() * 256.0F / 360.0F)));
/*     */     } catch (InstantiationException e) {
/* 158 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/* 162 */     return packet;
/*     */   }
/*     */ 
/*     */   public Object getWatcher()
/*     */   {
/* 167 */     Class<?> watcher_class = Util.getCraftClass("DataWatcher");
/* 168 */     Object watcher = null;
/*     */     try {
/* 170 */       watcher = watcher_class.newInstance();
/*     */ 
/* 172 */       Method a = Util.getMethod(watcher_class, "a", new Class[] { Integer.TYPE, Object.class });
/* 173 */       a.setAccessible(true);
/*     */ 
/* 175 */       a.invoke(watcher, new Object[] { Integer.valueOf(0), Byte.valueOf((byte) (isVisible() ? 0 : 32)) });
/* 176 */       a.invoke(watcher, new Object[] { Integer.valueOf(6), Float.valueOf(this.health) });
/* 177 */       a.invoke(watcher, new Object[] { Integer.valueOf(7), Integer.valueOf(0) });
/* 178 */       a.invoke(watcher, new Object[] { Integer.valueOf(8), Byte.valueOf((byte) 0) });
/* 179 */       a.invoke(watcher, new Object[] { Integer.valueOf(10), this.name });
/* 180 */       a.invoke(watcher, new Object[] { Integer.valueOf(11), Byte.valueOf((byte) 1) });
/*     */     } catch (InstantiationException e) {
/* 182 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 184 */       e.printStackTrace();
/*     */     } catch (IllegalArgumentException e) {
/* 186 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 188 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 191 */     return watcher;
/*     */   }
/*     */ }

/* Location:           C:\Users\Michael\Downloads\BarAPI.jar
 * Qualified Name:     me.confuser.barapi.nms.v1_6
 * JD-Core Version:    0.6.2
 */