package org.bukkit;

import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.Validate;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cake;
import org.bukkit.material.Cauldron;
import org.bukkit.material.Chest;
import org.bukkit.material.Coal;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Command;
import org.bukkit.material.Crops;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.Dispenser;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.material.EnderChest;
import org.bukkit.material.FlowerPot;
import org.bukkit.material.Furnace;
import org.bukkit.material.Gate;
import org.bukkit.material.Ladder;
import org.bukkit.material.Lever;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.MonsterEggs;
import org.bukkit.material.Mushroom;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.PressurePlate;
import org.bukkit.material.Pumpkin;
import org.bukkit.material.Rails;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.material.RedstoneWire;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Sign;
import org.bukkit.material.Skull;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.SpawnEgg;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.Torch;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.Tree;
import org.bukkit.material.Tripwire;
import org.bukkit.material.TripwireHook;
import org.bukkit.material.Vine;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;
import org.bukkit.util.Java15Compat;

public enum Material
{
  AIR(0), 
  STONE(1), 
  GRASS(2), 
  DIRT(3), 
  COBBLESTONE(4), 
  WOOD(5, Tree.class), 
  SAPLING(6, Tree.class), 
  BEDROCK(7), 
  WATER(8, MaterialData.class), 
  STATIONARY_WATER(9, MaterialData.class), 
  LAVA(10, MaterialData.class), 
  STATIONARY_LAVA(11, MaterialData.class), 
  SAND(12), 
  GRAVEL(13), 
  GOLD_ORE(14), 
  IRON_ORE(15), 
  COAL_ORE(16), 
  LOG(17, Tree.class), 
  LEAVES(18, Tree.class), 
  SPONGE(19), 
  GLASS(20), 
  LAPIS_ORE(21), 
  LAPIS_BLOCK(22), 
  DISPENSER(23, Dispenser.class), 
  SANDSTONE(24, Sandstone.class), 
  NOTE_BLOCK(25), 
  BED_BLOCK(26, Bed.class), 
  POWERED_RAIL(27, PoweredRail.class), 
  DETECTOR_RAIL(28, DetectorRail.class), 
  PISTON_STICKY_BASE(29, PistonBaseMaterial.class), 
  WEB(30), 
  LONG_GRASS(31, LongGrass.class), 
  DEAD_BUSH(32), 
  PISTON_BASE(33, PistonBaseMaterial.class), 
  PISTON_EXTENSION(34, PistonExtensionMaterial.class), 
  WOOL(35, Wool.class), 
  PISTON_MOVING_PIECE(36), 
  YELLOW_FLOWER(37), 
  RED_ROSE(38), 
  BROWN_MUSHROOM(39), 
  RED_MUSHROOM(40), 
  GOLD_BLOCK(41), 
  IRON_BLOCK(42), 
  DOUBLE_STEP(43, Step.class), 
  STEP(44, Step.class), 
  BRICK(45), 
  TNT(46), 
  BOOKSHELF(47), 
  MOSSY_COBBLESTONE(48), 
  OBSIDIAN(49), 
  TORCH(50, Torch.class), 
  FIRE(51), 
  MOB_SPAWNER(52), 
  WOOD_STAIRS(53, Stairs.class), 
  CHEST(54, Chest.class), 
  REDSTONE_WIRE(55, RedstoneWire.class), 
  DIAMOND_ORE(56), 
  DIAMOND_BLOCK(57), 
  WORKBENCH(58), 
  CROPS(59, Crops.class), 
  SOIL(60, MaterialData.class), 
  FURNACE(61, Furnace.class), 
  BURNING_FURNACE(62, Furnace.class), 
  SIGN_POST(63, 64, Sign.class), 
  WOODEN_DOOR(64, Door.class), 
  LADDER(65, Ladder.class), 
  RAILS(66, Rails.class), 
  COBBLESTONE_STAIRS(67, Stairs.class), 
  WALL_SIGN(68, 64, Sign.class), 
  LEVER(69, Lever.class), 
  STONE_PLATE(70, PressurePlate.class), 
  IRON_DOOR_BLOCK(71, Door.class), 
  WOOD_PLATE(72, PressurePlate.class), 
  REDSTONE_ORE(73), 
  GLOWING_REDSTONE_ORE(74), 
  REDSTONE_TORCH_OFF(75, RedstoneTorch.class), 
  REDSTONE_TORCH_ON(76, RedstoneTorch.class), 
  STONE_BUTTON(77, Button.class), 
  SNOW(78), 
  ICE(79), 
  SNOW_BLOCK(80), 
  CACTUS(81, MaterialData.class), 
  CLAY(82), 
  SUGAR_CANE_BLOCK(83, MaterialData.class), 
  JUKEBOX(84), 
  FENCE(85), 
  PUMPKIN(86, Pumpkin.class), 
  NETHERRACK(87), 
  SOUL_SAND(88), 
  GLOWSTONE(89), 
  PORTAL(90), 
  JACK_O_LANTERN(91, Pumpkin.class), 
  CAKE_BLOCK(92, 64, Cake.class), 
  DIODE_BLOCK_OFF(93, Diode.class), 
  DIODE_BLOCK_ON(94, Diode.class), 
  LOCKED_CHEST(95), 
  TRAP_DOOR(96, TrapDoor.class), 
  MONSTER_EGGS(97, MonsterEggs.class), 
  SMOOTH_BRICK(98, SmoothBrick.class), 
  HUGE_MUSHROOM_1(99, Mushroom.class), 
  HUGE_MUSHROOM_2(100, Mushroom.class), 
  IRON_FENCE(101), 
  THIN_GLASS(102), 
  MELON_BLOCK(103), 
  PUMPKIN_STEM(104, MaterialData.class), 
  MELON_STEM(105, MaterialData.class), 
  VINE(106, Vine.class), 
  FENCE_GATE(107, Gate.class), 
  BRICK_STAIRS(108, Stairs.class), 
  SMOOTH_STAIRS(109, Stairs.class), 
  MYCEL(110), 
  WATER_LILY(111), 
  NETHER_BRICK(112), 
  NETHER_FENCE(113), 
  NETHER_BRICK_STAIRS(114, Stairs.class), 
  NETHER_WARTS(115, MaterialData.class), 
  ENCHANTMENT_TABLE(116), 
  BREWING_STAND(117, MaterialData.class), 
  CAULDRON(118, Cauldron.class), 
  ENDER_PORTAL(119), 
  ENDER_PORTAL_FRAME(120), 
  ENDER_STONE(121), 
  DRAGON_EGG(122), 
  REDSTONE_LAMP_OFF(123), 
  REDSTONE_LAMP_ON(124), 
  WOOD_DOUBLE_STEP(125, WoodenStep.class), 
  WOOD_STEP(126, WoodenStep.class), 
  COCOA(127, CocoaPlant.class), 
  SANDSTONE_STAIRS(128, Stairs.class), 
  EMERALD_ORE(129), 
  ENDER_CHEST(130, EnderChest.class), 
  TRIPWIRE_HOOK(131, TripwireHook.class), 
  TRIPWIRE(132, Tripwire.class), 
  EMERALD_BLOCK(133), 
  SPRUCE_WOOD_STAIRS(134, Stairs.class), 
  BIRCH_WOOD_STAIRS(135, Stairs.class), 
  JUNGLE_WOOD_STAIRS(136, Stairs.class), 
  COMMAND(137, Command.class), 
  BEACON(138), 
  COBBLE_WALL(139), 
  FLOWER_POT(140, FlowerPot.class), 
  CARROT(141), 
  POTATO(142), 
  WOOD_BUTTON(143, Button.class), 
  SKULL(144, Skull.class), 
  ANVIL(145), 

  IRON_SPADE(256, 1, 250), 
  IRON_PICKAXE(257, 1, 250), 
  IRON_AXE(258, 1, 250), 
  FLINT_AND_STEEL(259, 1, 64), 
  APPLE(260), 
  BOW(261, 1, 384), 
  ARROW(262), 
  COAL(263, Coal.class), 
  DIAMOND(264), 
  IRON_INGOT(265), 
  GOLD_INGOT(266), 
  IRON_SWORD(267, 1, 250), 
  WOOD_SWORD(268, 1, 59), 
  WOOD_SPADE(269, 1, 59), 
  WOOD_PICKAXE(270, 1, 59), 
  WOOD_AXE(271, 1, 59), 
  STONE_SWORD(272, 1, 131), 
  STONE_SPADE(273, 1, 131), 
  STONE_PICKAXE(274, 1, 131), 
  STONE_AXE(275, 1, 131), 
  DIAMOND_SWORD(276, 1, 1561), 
  DIAMOND_SPADE(277, 1, 1561), 
  DIAMOND_PICKAXE(278, 1, 1561), 
  DIAMOND_AXE(279, 1, 1561), 
  STICK(280), 
  BOWL(281), 
  MUSHROOM_SOUP(282, 1), 
  GOLD_SWORD(283, 1, 32), 
  GOLD_SPADE(284, 1, 32), 
  GOLD_PICKAXE(285, 1, 32), 
  GOLD_AXE(286, 1, 32), 
  STRING(287), 
  FEATHER(288), 
  SULPHUR(289), 
  WOOD_HOE(290, 1, 59), 
  STONE_HOE(291, 1, 131), 
  IRON_HOE(292, 1, 250), 
  DIAMOND_HOE(293, 1, 1561), 
  GOLD_HOE(294, 1, 32), 
  SEEDS(295), 
  WHEAT(296), 
  BREAD(297), 
  LEATHER_HELMET(298, 1, 55), 
  LEATHER_CHESTPLATE(299, 1, 80), 
  LEATHER_LEGGINGS(300, 1, 75), 
  LEATHER_BOOTS(301, 1, 65), 
  CHAINMAIL_HELMET(302, 1, 165), 
  CHAINMAIL_CHESTPLATE(303, 1, 240), 
  CHAINMAIL_LEGGINGS(304, 1, 225), 
  CHAINMAIL_BOOTS(305, 1, 195), 
  IRON_HELMET(306, 1, 165), 
  IRON_CHESTPLATE(307, 1, 240), 
  IRON_LEGGINGS(308, 1, 225), 
  IRON_BOOTS(309, 1, 195), 
  DIAMOND_HELMET(310, 1, 363), 
  DIAMOND_CHESTPLATE(311, 1, 528), 
  DIAMOND_LEGGINGS(312, 1, 495), 
  DIAMOND_BOOTS(313, 1, 429), 
  GOLD_HELMET(314, 1, 77), 
  GOLD_CHESTPLATE(315, 1, 112), 
  GOLD_LEGGINGS(316, 1, 105), 
  GOLD_BOOTS(317, 1, 91), 
  FLINT(318), 
  PORK(319), 
  GRILLED_PORK(320), 
  PAINTING(321), 
  GOLDEN_APPLE(322), 
  SIGN(323, 16), 
  WOOD_DOOR(324, 1), 
  BUCKET(325, 16), 
  WATER_BUCKET(326, 1), 
  LAVA_BUCKET(327, 1), 
  MINECART(328, 1), 
  SADDLE(329, 1), 
  IRON_DOOR(330, 1), 
  REDSTONE(331), 
  SNOW_BALL(332, 16), 
  BOAT(333, 1), 
  LEATHER(334), 
  MILK_BUCKET(335, 1), 
  CLAY_BRICK(336), 
  CLAY_BALL(337), 
  SUGAR_CANE(338), 
  PAPER(339), 
  BOOK(340), 
  SLIME_BALL(341), 
  STORAGE_MINECART(342, 1), 
  POWERED_MINECART(343, 1), 
  EGG(344, 16), 
  COMPASS(345), 
  FISHING_ROD(346, 1, 64), 
  WATCH(347), 
  GLOWSTONE_DUST(348), 
  RAW_FISH(349), 
  COOKED_FISH(350), 
  INK_SACK(351, Dye.class), 
  BONE(352), 
  SUGAR(353), 
  CAKE(354, 1), 
  BED(355, 1), 
  DIODE(356), 
  COOKIE(357), 

  MAP(358, MaterialData.class), 
  SHEARS(359, 1, 238), 
  MELON(360), 
  PUMPKIN_SEEDS(361), 
  MELON_SEEDS(362), 
  RAW_BEEF(363), 
  COOKED_BEEF(364), 
  RAW_CHICKEN(365), 
  COOKED_CHICKEN(366), 
  ROTTEN_FLESH(367), 
  ENDER_PEARL(368, 16), 
  BLAZE_ROD(369), 
  GHAST_TEAR(370), 
  GOLD_NUGGET(371), 
  NETHER_STALK(372), 

  POTION(373, 1, MaterialData.class), 
  GLASS_BOTTLE(374), 
  SPIDER_EYE(375), 
  FERMENTED_SPIDER_EYE(376), 
  BLAZE_POWDER(377), 
  MAGMA_CREAM(378), 
  BREWING_STAND_ITEM(379), 
  CAULDRON_ITEM(380), 
  EYE_OF_ENDER(381), 
  SPECKLED_MELON(382), 
  MONSTER_EGG(383, 64, SpawnEgg.class), 
  EXP_BOTTLE(384, 64), 
  FIREBALL(385, 64), 
  BOOK_AND_QUILL(386, 1), 
  WRITTEN_BOOK(387, 1), 
  EMERALD(388, 64), 
  ITEM_FRAME(389), 
  FLOWER_POT_ITEM(390), 
  CARROT_ITEM(391), 
  POTATO_ITEM(392), 
  BAKED_POTATO(393), 
  POISONOUS_POTATO(394), 
  EMPTY_MAP(395), 
  GOLDEN_CARROT(396), 
  SKULL_ITEM(397), 
  CARROT_STICK(398, 1, 25), 
  NETHER_STAR(399), 
  PUMPKIN_PIE(400), 
  GOLD_RECORD(2256, 1), 
  GREEN_RECORD(2257, 1), 
  RECORD_3(2258, 1), 
  RECORD_4(2259, 1), 
  RECORD_5(2260, 1), 
  RECORD_6(2261, 1), 
  RECORD_7(2262, 1), 
  RECORD_8(2263, 1), 
  RECORD_9(2264, 1), 
  RECORD_10(2265, 1), 
  RECORD_11(2266, 1);

  private final int id;
  private final Class<? extends MaterialData> data;
  private static Material[] byId;
  private static final Map<String, Material> BY_NAME;
  private final int maxStack;
  private final short durability;
  private static Object reflectionFactory;
  private static Method newConstructorAccessor;
  private static Method newInstance;
  private static Method newFieldAccessor;
  private static Method fieldAccessorSet;
  private static boolean isSetup;

  private Material(int id) { this(id, 64); }

  private Material(int id, int stack)
  {
    this(id, stack, null);
  }

  private Material(int id, int stack, int durability) {
    this(id, stack, durability, null);
  }

  private Material(int id, Class<? extends MaterialData> data) {
    this(id, 64, data);
  }

  private Material(int id, int stack, Class<? extends MaterialData> data) {
    this(id, stack, 0, data);
  }

  private Material(int id, int stack, int durability, Class<? extends MaterialData> data) {
    this.id = id;
    this.durability = (short)durability;
    this.maxStack = stack;
    this.data = (data == null ? MaterialData.class : data);
  }

  public int getId()
  {
    return this.id;
  }

  public int getMaxStackSize()
  {
    return this.maxStack;
  }

  public short getMaxDurability()
  {
    return this.durability;
  }

  public Class<? extends MaterialData> getData()
  {
    return this.data;
  }

  public MaterialData getNewData(byte raw)
  {
    try
    {
      Constructor ctor = this.data.getConstructor(new Class[] { Integer.TYPE, Byte.TYPE });

      return (MaterialData)ctor.newInstance(new Object[] { Integer.valueOf(this.id), Byte.valueOf(raw) });
    } catch (InstantiationException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchMethodException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SecurityException ex) {
      Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null;
  }

  public boolean isBlock()
  {
    return this.id < 256;
  }

  public boolean isEdible()
  {
    return (equals(BREAD)) || (equals(COOKIE)) || (equals(MELON)) || (equals(MUSHROOM_SOUP)) || (equals(RAW_CHICKEN)) || (equals(COOKED_CHICKEN)) || (equals(RAW_BEEF)) || (equals(COOKED_BEEF)) || (equals(RAW_FISH)) || (equals(COOKED_FISH)) || (equals(PORK)) || (equals(GRILLED_PORK)) || (equals(APPLE)) || (equals(GOLDEN_APPLE)) || (equals(ROTTEN_FLESH)) || (equals(SPIDER_EYE));
  }

  public static Material getMaterial(int id)
  {
    if (byId.length > id) {
      return byId[id];
    }
    return null;
  }

  public static Material getMaterial(String name)
  {
    return (Material)BY_NAME.get(name);
  }

  public static Material matchMaterial(String name)
  {
    Validate.notNull(name, "Name cannot be null");

    Material result = null;
    try
    {
      result = getMaterial(Integer.parseInt(name));
    } catch (NumberFormatException ex) {
    }
    if (result == null) {
      String filtered = name.toUpperCase();

      filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
      result = (Material)BY_NAME.get(filtered);
    }

    return result;
  }

  public static void addMaterial(int id)
  {
    addMaterial(id, "X" + String.valueOf(id), false);
  }
  
  public static void addMaterial(int id, String name)
  {
    addMaterial(id, name, false);
  }

  public static void addMaterial(int id, String name, boolean xNameAlso) {
    if (byId[id] == null) {
      Material material = (Material)addEnum(Material.class, name, new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(id) });

      byId[id] = material;
      BY_NAME.put(name, material);

      String material_name = name.toUpperCase().trim();
      material_name = material_name.replaceAll("\\s+", "_").replaceAll("\\W", "");
      BY_NAME.put(material_name, material);
      
      if (xNameAlso)
      {
          BY_NAME.put("X" + String.valueOf(id), material);
      }
    }
  }

  public static void setMaterialName(int id, String name) {
    String material_name = name.toUpperCase().trim();
    material_name = material_name.replaceAll("\\s+", "_").replaceAll("\\W", "");

    if (byId[id] == null) {
      addMaterial(id, material_name, false);
    } else {
      Material material = getMaterial(id);
      BY_NAME.put(name, material);
      BY_NAME.put(material_name, material);
    }
  }

  private static void setup()
  {
    if (isSetup)
    {
      return;
    }
    try {
      Method getReflectionFactory = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("getReflectionFactory", new Class[0]);
      reflectionFactory = getReflectionFactory.invoke(null, new Object[0]);
      newConstructorAccessor = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("newConstructorAccessor", new Class[] { Constructor.class });
      newInstance = Class.forName("sun.reflect.ConstructorAccessor").getDeclaredMethod("newInstance", new Class[] { Object[].class });
      newFieldAccessor = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("newFieldAccessor", new Class[] { Field.class, Boolean.TYPE });
      fieldAccessorSet = Class.forName("sun.reflect.FieldAccessor").getDeclaredMethod("set", new Class[] { Object.class, Object.class });
    } catch (Exception e) {
      e.printStackTrace();
    }

    isSetup = true;
  }

  private static Object getConstructorAccessor(Class<?> enumClass, Class<?>[] additionalParameterTypes) throws Exception {
    Class[] parameterTypes = null;

    parameterTypes = new Class[additionalParameterTypes.length + 2];
    parameterTypes[0] = String.class;
    parameterTypes[1] = Integer.TYPE;
    System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);

    return newConstructorAccessor.invoke(reflectionFactory, new Object[] { enumClass.getDeclaredConstructor(parameterTypes) });
  }

  private static <T extends Enum<?>> T makeEnum(Class<T> enumClass, String value, int ordinal, Class<?>[] additionalTypes, Object[] additionalValues) throws Exception {
    Object[] parms = null;

    parms = new Object[additionalValues.length + 2];
    parms[0] = value;
    parms[1] = Integer.valueOf(ordinal);
    System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);

    return (T)enumClass.cast(newInstance.invoke(getConstructorAccessor(enumClass, additionalTypes), new Object[] { parms }));
  }

  private static void setFailsafeFieldValue(Field field, Object target, Object value) throws Exception {
    field.setAccessible(true);
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
    Object fieldAccessor = newFieldAccessor.invoke(reflectionFactory, new Object[] { field, Boolean.valueOf(false) });
    fieldAccessorSet.invoke(fieldAccessor, new Object[] { target, value });
  }

  private static void blankField(Class<?> enumClass, String fieldName) throws Exception {
    for (Field field : Class.class.getDeclaredFields())
      if (field.getName().contains(fieldName)) {
        field.setAccessible(true);
        setFailsafeFieldValue(field, enumClass, null);
        break;
      }
  }

  private static void cleanEnumCache(Class<?> enumClass) throws Exception
  {
    blankField(enumClass, "enumConstantDirectory");
    blankField(enumClass, "enumConstants");
  }

  public static <T extends Enum<?>> T addEnum(Class<T> enumType, String enumName, Class<?>[] paramTypes, Object[] paramValues)
  {
    if (!isSetup) setup();
    Field valuesField = null;
    Field[] fields = enumType.getDeclaredFields();
    int flags = 4122;
    String valueType = String.format("[L%s;", new Object[] { enumType.getName() });

    for (Field field : fields) {
      if (((field.getModifiers() & flags) != flags) || (!field.getType().getName().equals(valueType))) {
        continue;
      }
      valuesField = field;
      break;
    }

    valuesField.setAccessible(true);
    try
    {
      Enum[] previousValues = (Enum[])(Enum[])valuesField.get(enumType);
      List values = new ArrayList(Arrays.asList(previousValues));
      Enum newValue = makeEnum(enumType, enumName, values.size(), paramTypes, paramValues);
      values.add(newValue);
      setFailsafeFieldValue(valuesField, null, values.toArray((Enum[])(Enum[])Array.newInstance(enumType, 0)));
      cleanEnumCache(enumType);

      return (T) newValue;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public boolean isRecord()
  {
    return (this.id >= GOLD_RECORD.id) && (this.id <= RECORD_11.id);
  }

  static
  {
    byId = new Material[32000];
    BY_NAME = Maps.newHashMap();

    reflectionFactory = null;
    newConstructorAccessor = null;
    newInstance = null;
    newFieldAccessor = null;
    fieldAccessorSet = null;
    isSetup = false;

    for (Material material : values()) {
      if (byId.length > material.id) {
        byId[material.id] = material;
      } else {
        byId = (Material[])Java15Compat.Arrays_copyOfRange(byId, 0, material.id + 2);
        byId[material.id] = material;
      }
      BY_NAME.put(material.name(), material);
    }
  }
}