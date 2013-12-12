/*     */ package Packets;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class Util
/*     */ {
/*  26 */   public static boolean newProtocol = false;
/*     */ 
/*  31 */     static String name = Bukkit.getServer().getClass().getPackage().getName();
/*  32 */     static String mcVersion = name.substring(name.lastIndexOf('.') + 1);
static /*  33 */     String[] versions = mcVersion.split("_");
/*  40 */   public static String version = mcVersion + ".";
/*     */ 
/*  28 */   public static Class<?> fakeDragonClass = v1_6.class;
/*     */ 
/*     */   static {
/*     */ 
/*  35 */     if ((versions[0].equals("v1")) && (Integer.parseInt(versions[1]) > 6)) {
/*  36 */       newProtocol = true;
/*  37 */       fakeDragonClass = v1_7.class;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static FakeDragon newDragon(String message, Location loc)
/*     */   {
/*  44 */     FakeDragon fakeDragon = null;
/*     */     try
/*     */     {
/*  47 */       fakeDragon = (FakeDragon)fakeDragonClass.getConstructor(new Class[] { String.class, Location.class }).newInstance(new Object[] { message, loc });
/*     */     } catch (IllegalArgumentException e) {
/*  49 */       e.printStackTrace();
/*     */     } catch (SecurityException e) {
/*  51 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/*  53 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  55 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/*  57 */       e.printStackTrace();
/*     */     } catch (NoSuchMethodException e) {
/*  59 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  62 */     return fakeDragon;
/*     */   }
/*     */ 
/*     */   public static void sendPacket(Player p, Object packet)
/*     */   {
/*     */     try {
/*  68 */       Object nmsPlayer = getHandle(p);
/*  69 */       Field con_field = nmsPlayer.getClass().getField("playerConnection");
/*  70 */       Object con = con_field.get(nmsPlayer);
/*  71 */       Method packet_method = getMethod(con.getClass(), "sendPacket");
/*  72 */       packet_method.invoke(con, new Object[] { packet });
/*     */     } catch (SecurityException e) {
/*  74 */       e.printStackTrace();
/*     */     } catch (IllegalArgumentException e) {
/*  76 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/*  78 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/*  80 */       e.printStackTrace();
/*     */     } catch (NoSuchFieldException e) {
/*  82 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Class<?> getCraftClass(String ClassName) {
/*  87 */     String className = "net.minecraft.server." + version + ClassName;
/*  88 */     @SuppressWarnings("rawtypes")
Class c = null;
/*     */     try {
/*  90 */       c = Class.forName(className);
/*     */     } catch (ClassNotFoundException e) {
/*  92 */       e.printStackTrace();
/*     */     }
/*  94 */     return c;
/*     */   }
/*     */ 
/*     */   public static Object getHandle(World world) {
/*  98 */     Object nms_entity = null;
/*  99 */     Method entity_getHandle = getMethod(world.getClass(), "getHandle");
/*     */     try {
/* 101 */       nms_entity = entity_getHandle.invoke(world, new Object[0]);
/*     */     } catch (IllegalArgumentException e) {
/* 103 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 105 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 107 */       e.printStackTrace();
/*     */     }
/* 109 */     return nms_entity;
/*     */   }
/*     */ 
/*     */   public static Object getHandle(Entity entity) {
/* 113 */     Object nms_entity = null;
/* 114 */     Method entity_getHandle = getMethod(entity.getClass(), "getHandle");
/*     */     try {
/* 116 */       nms_entity = entity_getHandle.invoke(entity, new Object[0]);
/*     */     } catch (IllegalArgumentException e) {
/* 118 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 120 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 122 */       e.printStackTrace();
/*     */     }
/* 124 */     return nms_entity;
/*     */   }
/*     */ 
/*     */   public static Field getField(Class<?> cl, String field_name) {
/*     */     try {
/* 129 */       return cl.getDeclaredField(field_name);
/*     */     }
/*     */     catch (SecurityException e) {
/* 132 */       e.printStackTrace();
/*     */     } catch (NoSuchFieldException e) {
/* 134 */       e.printStackTrace();
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method, Class<?>[] args) {
/* 140 */     for (Method m : cl.getMethods()) {
/* 141 */       if ((m.getName().equals(method)) && (ClassListEqual(args, m.getParameterTypes()))) {
/* 142 */         return m;
/*     */       }
/*     */     }
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method, Integer args) {
/* 149 */     for (Method m : cl.getMethods()) {
/* 150 */       if ((m.getName().equals(method)) && (args.equals(new Integer(m.getParameterTypes().length)))) {
/* 151 */         return m;
/*     */       }
/*     */     }
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> cl, String method) {
/* 158 */     for (Method m : cl.getMethods()) {
/* 159 */       if (m.getName().equals(method)) {
/* 160 */         return m;
/*     */       }
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
/* 167 */     boolean equal = true;
/*     */ 
/* 169 */     if (l1.length != l2.length)
/* 170 */       return false;
/* 171 */     for (int i = 0; i < l1.length; i++) {
/* 172 */       if (l1[i] != l2[i]) {
/* 173 */         equal = false;
/* 174 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 178 */     return equal;
/*     */   }
/*     */ }

/* Location:           C:\Users\Michael\Downloads\BarAPI.jar
 * Qualified Name:     me.confuser.barapi.Util
 * JD-Core Version:    0.6.2
 */