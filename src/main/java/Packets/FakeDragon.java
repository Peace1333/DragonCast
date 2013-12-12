/*     */ package Packets;


   	import org.bukkit.Location;
/*     */ 
/*     */ public abstract class FakeDragon
/*     */ {
/*     */   public static final int MAX_HEALTH = 200;
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*  13 */   private int pitch = 0;
/*  14 */   private int yaw = 0;
/*  15 */   private byte xvel = 0;
/*  16 */   private byte yvel = 0;
/*  17 */   private byte zvel = 0;
/*  18 */   public float health = 0.0F;
/*  19 */   private boolean visible = false;
/*     */   public String name;
/*     */   private Object world;
/*     */ 
/*     */   public FakeDragon(String name, Location loc, int percent)
/*     */   {
/*  24 */     this.name = name;
/*  25 */     this.x = loc.getBlockX();
/*  26 */     this.y = loc.getBlockY();
/*  27 */     this.z = loc.getBlockZ();
/*  28 */     this.health = (percent / 100.0F * 200.0F);
/*  29 */     this.world = Util.getHandle(loc.getWorld());
/*     */   }
/*     */ 
/*     */   public FakeDragon(String name, Location loc) {
/*  33 */     this.name = name;
/*  34 */     this.x = loc.getBlockX();
/*  35 */     this.y = loc.getBlockY();
/*  36 */     this.z = loc.getBlockZ();
/*  37 */     this.world = Util.getHandle(loc.getWorld());
/*     */   }
/*     */ 
/*     */   public int getMaxHealth() {
/*  41 */     return 200;
/*     */   }
/*     */ 
/*     */   public void setHealth(int percent) {
/*  45 */     this.health = (percent / 100.0F * 200.0F);
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  49 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public int getX() {
/*  53 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(int x) {
/*  57 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public int getY() {
/*  61 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(int y) {
/*  65 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public int getZ() {
/*  69 */     return this.z;
/*     */   }
/*     */ 
/*     */   public void setZ(int z) {
/*  73 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public int getPitch() {
/*  77 */     return this.pitch;
/*     */   }
/*     */ 
/*     */   public void setPitch(int pitch) {
/*  81 */     this.pitch = pitch;
/*     */   }
/*     */ 
/*     */   public int getYaw() {
/*  85 */     return this.yaw;
/*     */   }
/*     */ 
/*     */   public void setYaw(int yaw) {
/*  89 */     this.yaw = yaw;
/*     */   }
/*     */ 
/*     */   public byte getXvel() {
/*  93 */     return this.xvel;
/*     */   }
/*     */ 
/*     */   public void setXvel(byte xvel) {
/*  97 */     this.xvel = xvel;
/*     */   }
/*     */ 
/*     */   public byte getYvel() {
/* 101 */     return this.yvel;
/*     */   }
/*     */ 
/*     */   public void setYvel(byte yvel) {
/* 105 */     this.yvel = yvel;
/*     */   }
/*     */ 
/*     */   public byte getZvel() {
/* 109 */     return this.zvel;
/*     */   }
/*     */ 
/*     */   public void setZvel(byte zvel) {
/* 113 */     this.zvel = zvel;
/*     */   }
/*     */ 
/*     */   public boolean isVisible() {
/* 117 */     return this.visible;
/*     */   }
/*     */ 
/*     */   public void setVisible(boolean visible) {
/* 121 */     this.visible = visible;
/*     */   }
/*     */ 
/*     */   public Object getWorld() {
/* 125 */     return this.world;
/*     */   }
/*     */ 
/*     */   public void setWorld(Object world) {
/* 129 */     this.world = world;
/*     */   }
/*     */ 
/*     */   public abstract Object getSpawnPacket();
/*     */ 
/*     */   public abstract Object getDestroyPacket();
/*     */ 
/*     */   public abstract Object getMetaPacket(Object paramObject);
/*     */ 
/*     */   public abstract Object getTeleportPacket(Location paramLocation);
/*     */ 
/*     */   public abstract Object getWatcher();
/*     */ }

/* Location:           C:\Users\Michael\Downloads\BarAPI.jar
 * Qualified Name:     me.confuser.barapi.nms.FakeDragon
 * JD-Core Version:    0.6.2
 */