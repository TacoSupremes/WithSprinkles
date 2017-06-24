package com.tacosupremes.withsprinkles.common.lib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tacosupremes.withsprinkles.common.utils.BlockUtils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LibMisc {
	
	public static final String MODID = "withsprinkles";
	public static final String VERSION = "1.02";
	public static final String COMMONPROXY = "com.tacosupremes.withsprinkles.proxy.CommonProxy";
	public static final String CLIENTPROXY = "com.tacosupremes.withsprinkles.proxy.ClientProxy";
	
	
	public static class Ores {
		
	
	public static List<String> ores = new ArrayList<String>();
	public static List<String> oreName = new ArrayList<String>();
	public static List<String> dusts = new ArrayList<String>();
	
	public static Map<String, ItemStack> oL = new HashMap<String, ItemStack>();
	
	public static Map<String, Color> oC = new HashMap<String, Color>();
	
	public static void preInit(){
		
		//Stolen From Botania
				addOre("oreAluminum", Color.lightGray); // Tinkers' Construct
				addOre("oreAmber", Color.orange); // Thaumcraft
				addOre("oreApatite", Color.blue); // Forestry
				addOre("oreBlueTopaz", Color.BLUE); // Ars Magica
				addOre("oreCertusQuartz", Color.blue); // Applied Energistics
				addOre("oreChimerite",Color.ORANGE); // Ars Magica
				addOre("oreCinnabar", Color.red); // Thaumcraft
				addOre("oreCoal", Color.black); // Vanilla
				addOre("oreCopper", Color.orange); // IC2, Thermal Expansion, Tinkers' Construct, etc.
				addOre("oreDark", Color.BLACK); // EvilCraft
				addOre("oreDarkIron", Color.black); // Factorization (older versions)
				addOre("oreFzDarkIron", Color.black); // Factorization (newer versions)
				addOre("oreDiamond", Color.BLUE); // Vanilla
				addOre("oreEmerald", Color.GREEN); // Vanilla
				addOre("oreGalena", Color.green); // Factorization
				addOre("oreGold", Color.YELLOW); // Vanilla
				addOre("oreInfusedAir", Color.yellow); // Thaumcraft
				addOre("oreInfusedEarth", Color.red); // Thaumcraft
				addOre("oreInfusedEntropy", Color.black); // Thaumcraft
				addOre("oreInfusedFire", Color.red); // Thaumcraft
				addOre("oreInfusedOrder", Color.white); // Thaumcraft
				addOre("oreInfusedWater", Color.cyan); // Thaumcraft
				addOre("oreIron", Color.gray); // Vanilla
				addOre("oreLapis", Color.BLUE); // Vanilla
				addOre("oreLead", Color.DARK_GRAY); // IC2, Thermal Expansion, Factorization, etc.
				addOre("oreMCropsEssence", Color.GREEN); // Magical Crops
				addOre("oreMithril", Color.MAGENTA); // Thermal Expansion
				addOre("oreNickel", Color.LIGHT_GRAY); // Thermal Expansion
				addOre("oreOlivine", Color.GREEN); // Project RED
				addOre("orePlatinum", Color.cyan); // Thermal Expansion
				addOre("oreRedstone", Color.RED); // Vanilla
				addOre("oreRuby", Color.RED); // Project RED
				addOre("oreSapphire", Color.BLUE); // Project RED
				addOre("oreSilver", Color.LIGHT_GRAY); // Thermal Expansion, Factorization, etc.
				addOre("oreSulfur",  Color.YELLOW); // Railcraft
				addOre("oreTin",  Color.gray); // IC2, Thermal Expansion, etc.
				addOre("oreUranium",  Color.green); // IC2
				addOre("oreVinteum",  Color.blue); // Ars Magica
				addOre("oreYellorite",  Color.yellow); // Big Reactors
				addOre("oreZinc", Color.blue); // Flaxbeard's Steam Power
				addOre("oreMythril",  Color.RED); // Simple Ores2
				addOre("oreAdamantium" , Color.RED); // Simple Ores2
				addOre("oreTungsten", Color.black); // Simple Tungsten
		
	}
	
	public static void postInit(){
		
		for(String s : oL.keySet()){
			OreDictionary.registerOre(s, oL.get(s));		
		}
		
		
		
	//	cleanOres();
		makeDusts();
	}
	


	private static void cleanOres() {
		
		for(int i =0; i< ores.size();i++){
			String s = ores.get(i);
			if(OreDictionary.getOres(s) == null || OreDictionary.getOres(s).isEmpty())
				ores.remove(s);	
		}
		
	}
	
	private static void makeDusts() {
		
		for(String s2 : ores){
			String s = s2.replace("ore", "dust");
			if(OreDictionary.getOres(s) != null && !OreDictionary.getOres(s).isEmpty())
				dusts.add(s);	
		}
		
		
	}

	public static void addOre(String s, Color c){
		ores.add(s);
		oreName.add(s.substring(3));
		oC.put(s, c);
	}
	
	public static boolean isOre(IBlockState b){
		
		
		ItemStack is = new ItemStack(b.getBlock(), 1, b.getBlock().getMetaFromState(b));
		
		for(String s : ores){
			
			for(ItemStack is2 : OreDictionary.getOres(s)){
				
				if(is2.areItemsEqual(is, is2))
					return true;
				
			}
				
			
		}
		
		return false;
	}
	
	public static boolean isOre(ItemStack is3){
		
		if(is3 == null)
			return false;
		
		ItemStack is = is3.copy().splitStack(1);
		
		for(String s : ores){
			
			for(ItemStack is2 : OreDictionary.getOres(s)){
				
				if(is2.areItemsEqual(is, is2))
					return true;
				
			}
				
			
		}
		
		return false;
	}
	
	public static String getName(IBlockState b){
		
		
		ItemStack is = new ItemStack(b.getBlock(), 1, b.getBlock().getMetaFromState(b));
		
		for(String s : ores){
			
			for(ItemStack is2 : OreDictionary.getOres(s)){
				
				if(is2.areItemsEqual(is, is2))
					return s;
				
			}
				
			
		}
		
		return null;
	}

	public static String getName(ItemStack is3){
	
		
	if(is3 == null)
		return null;		
	ItemStack is = is3.copy().splitStack(1);
	
	
	for(String s : ores){
		
		for(ItemStack is2 : OreDictionary.getOres(s)){
			
			if(is2.areItemsEqual(is, is2))
				return s;
			
		}
			
		
	}
	
	return null;
}

public ItemStack getOtherForms(ItemStack i){
	
	
	String s = getName(i);
	
	
	
	List<ItemStack> l = OreDictionary.getOres(s);
	
	if(l == null || l.size() == 1 || l.size() == 0)
		return null;
	
	for(ItemStack is : l){
		if(is.isItemEqual(is))
			continue;
		
		return is;
	}
	
	
	
	return null;
	
}
/**From Ore to Color **/
public static Color getColor(String s){
	
	return oC.get(s);
	
}


	/**From Ore to Dust **/
	public static String toDust(String s){
	
		if(s == null || !s.contains("ore"))
			return null;
		
		
		
		
		return s.replace("ore","dust");
		
		
		
	}
	
	/**From Ore to Dust **/
	public static ItemStack toDust(ItemStack is){
	
		
		
		return OreDictionary.getOres(toDust(getName(is))).isEmpty() ? null : OreDictionary.getOres(toDust(getName(is))).get(0);

	}
	
	/**From Ore to Dust **/
	public static ItemStack toDust(IBlockState state){
		
		return toDust(BlockUtils.toItemStack(state));
	}
	
	/**From Dust to Ore **/
	public static String toOre(String s){

		return s.replace("dust", "ore");

	}
	

	
	
	
	/**From Dust to Ingot **/
	public static String toIngot(String s){
		
		return s.replace("dust", "ingot");
	}
	
	
	public static String getDustName(ItemStack is3){
		
		ItemStack is = is3.copy().splitStack(1);
		
		
		for(String s : dusts){
			
			for(ItemStack is2 : OreDictionary.getOres(s)){
				
				if(is2.areItemsEqual(is, is2))
					return s;
				
			}
				
			
		}
		
		return null;
	}
	
	
	
	
	
	}
	
	
	public static class GuiIDs {

		public static int MODBOOK = 0;
		
		
		
		public static class Buttons {
			
			public static final int NEXT = 46583;
			public static final int BACK = 45673;
		}
		
		
	}
	
	
}
