// L Bape, Decompiled by ImCzf233

package mc.bape.module.other;

import net.minecraft.block.*;
import mc.bape.utils.*;
import net.minecraft.client.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.inventory.*;
import mc.bape.manager.*;
import mc.bape.module.blatant.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import java.util.*;

public class InvManager extends Module
{
    static final List<Block> blacklisted;
    TimerUtil timer;
    Minecraft mc;
    private Numbers<Double> BlockCap;
    private Numbers<Double> Delay;
    private Option<Boolean> Food;
    private Option<Boolean> sort;
    private Option<Boolean> Archery;
    private Option<Boolean> Sword;
    private Mode<Enum> Mode;
    private Option<Boolean> UHC;
    public static int weaponSlot;
    public static int pickaxeSlot;
    public static int axeSlot;
    public static int shovelSlot;
    
    public InvManager() {
        super("InvManager", 0, ModuleType.Blatant, "Auto Manage your items when openinv");
        this.timer = new TimerUtil();
        this.mc = Minecraft.getMinecraft();
        this.BlockCap = new Numbers<Double>("BlockCap", "BlockCap", 128.0, 0.0, 256.0, 8.0);
        this.Delay = new Numbers<Double>("Delay", "Delay", 1.0, 0.0, 10.0, 1.0);
        this.Food = new Option<Boolean>("Food", "Food", true);
        this.sort = new Option<Boolean>("sort", "sort", true);
        this.Archery = new Option<Boolean>("Archery", "Archery", true);
        this.Sword = new Option<Boolean>("Sword", "Sword", true);
        this.Mode = new Mode<Enum>("Mode", "Mode", EMode.values(), EMode.Basic);
        this.UHC = new Option<Boolean>("UHC", "UHC", false);
        this.addValues(this.BlockCap, this.Delay, this.Food, this.Archery, this.Sword, this.Mode, this.sort, this.UHC);
        InvManager.Chinese = "\u80cc\u5305\u7ba1\u7406";
    }
    
    @Override
    public void enable() {
        super.enable();
    }
    
    @SubscribeEvent
    public void onEvent(final TickEvent.PlayerTickEvent event) {
        if (this.isMoving()) {
            return;
        }
        if (this.mc.thePlayer.openContainer instanceof ContainerChest && this.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        final InvManager i3 = (InvManager)ModuleManager.getModule("InvManager");
        final long delay = this.Delay.getValue().longValue() * 50L;
        final long Adelay = AutoArmor.DELAY.getValue().longValue() * 50L;
        if (this.timer.hasReached((double)Adelay) && i3.getState() && (i3.Mode.getValue() != AutoArmor.EMode.OpenInv || this.mc.currentScreen instanceof GuiInventory) && (this.mc.currentScreen == null || this.mc.currentScreen instanceof GuiInventory || this.mc.currentScreen instanceof GuiChat)) {
            this.getBestArmor();
        }
        if (i3.getState()) {
            for (int type = 1; type < 5; ++type) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                    final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                    if (!AutoArmor.isBestArmor(is, type)) {
                        return;
                    }
                }
                else if (this.invContainsType(type - 1)) {
                    return;
                }
            }
        }
        if (this.Mode.getValue() == EMode.OpenInv && !(this.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (this.mc.currentScreen == null || this.mc.currentScreen instanceof GuiInventory || this.mc.currentScreen instanceof GuiChat) {
            if (this.timer.hasReached((double)delay) && InvManager.weaponSlot >= 36) {
                if (!this.mc.thePlayer.inventoryContainer.getSlot(InvManager.weaponSlot).getHasStack()) {
                    this.getBestWeapon(InvManager.weaponSlot);
                }
                else if (!this.isBestWeapon(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.weaponSlot).getStack())) {
                    this.getBestWeapon(InvManager.weaponSlot);
                }
            }
            if (this.sort.getValue()) {
                if (this.timer.hasReached((double)delay) && InvManager.pickaxeSlot >= 36) {
                    this.getBestPickaxe(InvManager.pickaxeSlot);
                }
                if (this.timer.hasReached((double)delay) && InvManager.shovelSlot >= 36) {
                    this.getBestShovel(InvManager.shovelSlot);
                }
                if (this.timer.hasReached((double)delay) && InvManager.axeSlot >= 36) {
                    this.getBestAxe(InvManager.axeSlot);
                }
            }
            if (this.timer.hasReached((double)delay)) {
                for (int j = 9; j < 45; ++j) {
                    if (this.mc.thePlayer.inventoryContainer.getSlot(j).getHasStack()) {
                        final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(j).getStack();
                        if (this.shouldDrop(is, j)) {
                            this.drop(j);
                            this.timer.reset();
                            if (delay > 0L) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean shouldDrop(final ItemStack stack, final int slot) {
        if (stack.getDisplayName().contains("\u70b9\u51fb")) {
            return false;
        }
        if (stack.getDisplayName().contains("\u53f3\u952e")) {
            return false;
        }
        if (stack.getDisplayName().toLowerCase().contains("(right click)")) {
            return false;
        }
        if (stack.getItem() instanceof ItemSkull) {
            return false;
        }
        if (this.UHC.getValue()) {
            if (stack.getDisplayName().toLowerCase().contains("\u5934")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("apple")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("head")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("gold")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("crafting table")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("stick")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("and") && stack.getDisplayName().toLowerCase().contains("ril")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("axe of perun")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("barbarian")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("bloodlust")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragonchest")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragon sword")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("dragon armor")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("excalibur")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("exodus")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("fusion armor")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hermes boots")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hide of leviathan")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("scythe")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("seven-league boots")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("shoes of vidar")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("apprentice")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("master")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("vorpal")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("enchanted")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("spiked")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("tarnhelm")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("philosopher")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("anvil")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("panacea")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("fusion")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("excalibur")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5b66\u5f92")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5927\u5e08\u7f57\u76d8")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u65a9\u9996\u4e4b\u5251")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u9644\u9b54")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5de8\u9f99\u4e4b\u5251")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5de8\u9f99\u4e4b\u7532")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5203\u7532")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u4e03\u56fd\u6218\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u51b0\u6597\u6e56")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u54f2\u4eba")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u94c1\u7827")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u82f9\u679c")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u91d1")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u6c38\u751f\u4e4b\u9152")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u4e18\u6bd4\u7279\u4e4b\u5f13")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u953b\u7089")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("backpack")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u805a\u53d8\u4e4b\u7532")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u80cc\u5305")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u6708\u795e")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u6c38\u751f")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u6f6e\u6c50")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u96f7\u65a7")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u738b\u8005\u4e4b\u5251")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u5b89\u90fd\u745e\u5c14")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u6b7b\u795e\u9570\u5200")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u4e30\u9976\u4e4b\u89d2")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u7ef4\u8fbe\u6218\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u593a\u9b42\u4e4b\u5203")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u86ee\u4eba\u4e4b\u7532")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("\u7a83\u8d3c\u4e4b\u9774")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("hermes")) {
                return false;
            }
            if (stack.getDisplayName().toLowerCase().contains("barbarian")) {
                return false;
            }
        }
        if (slot == InvManager.weaponSlot) {
            if (this.isBestWeapon(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.weaponSlot).getStack())) {
                return false;
            }
        }
        if (slot == InvManager.pickaxeSlot) {
            if (this.isBestPickaxe(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.pickaxeSlot).getStack()) && InvManager.pickaxeSlot >= 0) {
                return false;
            }
        }
        if (slot == InvManager.axeSlot) {
            if (this.isBestAxe(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.axeSlot).getStack()) && InvManager.axeSlot >= 0) {
                return false;
            }
        }
        if (slot == InvManager.shovelSlot) {
            if (this.isBestShovel(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.shovelSlot).getStack())) {
                if (InvManager.shovelSlot >= 0) {
                    return false;
                }
            }
        }
        if (stack.getItem() instanceof ItemArmor) {
            for (int type = 1; type < 5; ++type) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                    final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                    if (AutoArmor.isBestArmor(is, type)) {
                        continue;
                    }
                }
                if (AutoArmor.isBestArmor(stack, type)) {
                    return false;
                }
            }
        }
        return (this.BlockCap.getValue().intValue() != 0 && stack.getItem() instanceof ItemBlock && (this.getBlockCount() > this.BlockCap.getValue().intValue() || InvManager.blacklisted.contains(((ItemBlock)stack.getItem()).getBlock()))) || (stack.getItem() instanceof ItemPotion && this.isBadPotion(stack)) || (stack.getItem() instanceof ItemFood && this.Food.getValue() && !(stack.getItem() instanceof ItemAppleGold)) || (stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor) || ((stack.getItem() instanceof ItemBow || stack.getItem().getUnlocalizedName().contains("arrow")) && this.Archery.getValue()) || stack.getItem().getUnlocalizedName().contains("tnt") || stack.getItem().getUnlocalizedName().contains("stick") || stack.getItem().getUnlocalizedName().contains("egg") || stack.getItem().getUnlocalizedName().contains("string") || stack.getItem().getUnlocalizedName().contains("cake") || stack.getItem().getUnlocalizedName().contains("mushroom") || stack.getItem().getUnlocalizedName().contains("flint") || stack.getItem().getUnlocalizedName().contains("compass") || stack.getItem().getUnlocalizedName().contains("dyePowder") || stack.getItem().getUnlocalizedName().contains("feather") || stack.getItem().getUnlocalizedName().contains("bucket") || (stack.getItem().getUnlocalizedName().contains("chest") && !stack.getDisplayName().toLowerCase().contains("collect")) || stack.getItem().getUnlocalizedName().contains("snow") || stack.getItem().getUnlocalizedName().contains("fish") || stack.getItem().getUnlocalizedName().contains("enchant") || stack.getItem().getUnlocalizedName().contains("exp") || stack.getItem().getUnlocalizedName().contains("shears") || stack.getItem().getUnlocalizedName().contains("anvil") || stack.getItem().getUnlocalizedName().contains("torch") || stack.getItem().getUnlocalizedName().contains("seeds") || stack.getItem().getUnlocalizedName().contains("leather") || stack.getItem().getUnlocalizedName().contains("reeds") || stack.getItem().getUnlocalizedName().contains("skull") || stack.getItem().getUnlocalizedName().contains("record") || stack.getItem().getUnlocalizedName().contains("snowball") || stack.getItem() instanceof ItemGlassBottle || stack.getItem().getUnlocalizedName().contains("piston");
    }
    
    private boolean isBadPotion(final ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemPotion) {
            final ItemPotion potion = (ItemPotion)stack.getItem();
            if (potion.getEffects(stack) == null) {
                return true;
            }
            for (final PotionEffect effect : potion.getEffects(stack)) {
                final PotionEffect o = effect;
                if (effect.getPotionID() != Potion.poison.getId() && effect.getPotionID() != Potion.harm.getId() && effect.getPotionID() != Potion.moveSlowdown.getId() && effect.getPotionID() != Potion.weakness.getId()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    private int getBlockCount() {
        int blockCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                final Item item = is.getItem();
                if (is.getItem() instanceof ItemBlock && !InvManager.blacklisted.contains(((ItemBlock)item).getBlock())) {
                    blockCount += is.stackSize;
                }
            }
        }
        return blockCount;
    }
    
    private void getBestAxe(final int slot) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestAxe(is) && InvManager.axeSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(InvManager.axeSlot).getHasStack()) {
                        this.swap(i, InvManager.axeSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestAxe(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.axeSlot).getStack())) {
                        this.swap(i, InvManager.axeSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private boolean isBestAxe(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemAxe)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemAxe && !this.isBestWeapon(stack)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void getBestShovel(final int slot) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestShovel(is) && InvManager.shovelSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(InvManager.shovelSlot).getHasStack()) {
                        this.swap(i, InvManager.shovelSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestShovel(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.shovelSlot).getStack())) {
                        this.swap(i, InvManager.shovelSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private boolean isBestShovel(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemSpade)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemSpade) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isMoving() {
        return this.mc.thePlayer.moveForward != 0.0f || this.mc.thePlayer.moveStrafing != 0.0f;
    }
    
    public void drop(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 1, 4, (EntityPlayer)this.mc.thePlayer);
    }
    
    private void getBestPickaxe(final int slot) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestPickaxe(is) && InvManager.pickaxeSlot != i && !this.isBestWeapon(is)) {
                    if (!this.mc.thePlayer.inventoryContainer.getSlot(InvManager.pickaxeSlot).getHasStack()) {
                        this.swap(i, InvManager.pickaxeSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                    else if (!this.isBestPickaxe(this.mc.thePlayer.inventoryContainer.getSlot(InvManager.pickaxeSlot).getStack())) {
                        this.swap(i, InvManager.pickaxeSlot - 36);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private boolean isBestPickaxe(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemPickaxe)) {
            return false;
        }
        final float value = this.getToolEffect(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getToolEffect(is) > value && is.getItem() instanceof ItemPickaxe) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private float getToolEffect(final ItemStack stack) {
        final Item item = stack.getItem();
        if (!(item instanceof ItemTool)) {
            return 0.0f;
        }
        final String name = item.getUnlocalizedName();
        final ItemTool tool = (ItemTool)item;
        float value = 1.0f;
        if (item instanceof ItemPickaxe) {
            value = tool.getStrVsBlock(stack, Blocks.stone);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        else if (item instanceof ItemSpade) {
            value = tool.getStrVsBlock(stack, Blocks.dirt);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        else {
            if (!(item instanceof ItemAxe)) {
                return 1.0f;
            }
            value = tool.getStrVsBlock(stack, Blocks.log);
            if (name.toLowerCase().contains("gold")) {
                value -= 5.0f;
            }
        }
        value += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) * 0.0075);
        value += (float)(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 100.0);
        return value;
    }
    
    public void getBestWeapon(final int slot) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.isBestWeapon(is) && this.getDamage(is) > 0.0f && (is.getItem() instanceof ItemSword || !this.Sword.getValue())) {
                    this.swap(i, slot - 36);
                    this.timer.reset();
                    break;
                }
            }
        }
    }
    
    public void swap(final int slot1, final int hotbarSlot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot1, hotbarSlot, 2, (EntityPlayer)this.mc.thePlayer);
    }
    
    public boolean isBestWeapon(final ItemStack stack) {
        final float damage = this.getDamage(stack);
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                if (this.getDamage(is) > damage && (is.getItem() instanceof ItemSword || !this.Sword.getValue())) {
                    return false;
                }
            }
        }
        return stack.getItem() instanceof ItemSword || !this.Sword.getValue();
    }
    
    private float getDamage(final ItemStack stack) {
        float damage = 0.0f;
        final Item item = stack.getItem();
        if (item instanceof ItemTool) {
            final ItemTool tool = (ItemTool)item;
            damage += tool.getMaxDamage();
        }
        if (item instanceof ItemSword) {
            final ItemSword sword = (ItemSword)item;
            damage += sword.getDamageVsEntity();
        }
        return damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25f + EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) * 0.01f;
    }
    
    boolean invContainsType(final int type) {
        for (int i = 9; i < 45; ++i) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                final Item item = is.getItem();
                if (item instanceof ItemArmor) {
                    final ItemArmor armor = (ItemArmor)item;
                    if (type == armor.armorType) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void getBestArmor() {
        for (int type = 1; type < 5; ++type) {
            if (this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getHasStack()) {
                final ItemStack is = this.mc.thePlayer.inventoryContainer.getSlot(4 + type).getStack();
                if (AutoArmor.isBestArmor(is, type)) {
                    continue;
                }
                this.drop(4 + type);
            }
            for (int i = 9; i < 45; ++i) {
                if (this.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                    final ItemStack is2 = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                    if (AutoArmor.isBestArmor(is2, type) && AutoArmor.getProtection(is2) > 0.0f) {
                        this.shiftClick(i);
                        this.timer.reset();
                        if (this.Delay.getValue().longValue() > 0L) {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void shiftClick(final int slot) {
        this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, slot, 0, 1, (EntityPlayer)this.mc.thePlayer);
    }
    
    static {
        blacklisted = Arrays.asList(Blocks.air, (Block)Blocks.water, (Block)Blocks.flowing_water, (Block)Blocks.lava, (Block)Blocks.flowing_lava, Blocks.enchanting_table, Blocks.ender_chest, (Block)Blocks.yellow_flower, Blocks.carpet, Blocks.glass_pane, (Block)Blocks.stained_glass_pane, Blocks.iron_bars, Blocks.crafting_table, Blocks.snow_layer, Blocks.packed_ice, Blocks.coal_ore, Blocks.diamond_ore, Blocks.emerald_ore, (Block)Blocks.chest, Blocks.torch, Blocks.anvil, Blocks.trapped_chest, Blocks.noteblock, Blocks.gold_ore, Blocks.iron_ore, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.redstone_ore, Blocks.wooden_pressure_plate, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.stone_button, Blocks.wooden_button, (Block)Blocks.cactus, Blocks.lever, Blocks.activator_rail, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.furnace, Blocks.ladder, Blocks.oak_fence, Blocks.redstone_torch, Blocks.iron_trapdoor, Blocks.trapdoor, (Block)Blocks.tripwire_hook, (Block)Blocks.hopper, Blocks.acacia_fence_gate, Blocks.birch_fence_gate, Blocks.dark_oak_fence_gate, Blocks.jungle_fence_gate, Blocks.spruce_fence_gate, Blocks.oak_fence_gate, Blocks.dispenser, Blocks.sapling, (Block)Blocks.tallgrass, (Block)Blocks.deadbush, Blocks.web, (Block)Blocks.red_flower, (Block)Blocks.red_mushroom, (Block)Blocks.brown_mushroom, Blocks.nether_brick_fence, Blocks.vine, (Block)Blocks.double_plant, Blocks.flower_pot, (Block)Blocks.beacon, Blocks.pumpkin, Blocks.lit_pumpkin);
        InvManager.weaponSlot = 36;
        InvManager.pickaxeSlot = 37;
        InvManager.axeSlot = 38;
        InvManager.shovelSlot = 39;
    }
    
    enum EMode
    {
        Basic, 
        OpenInv;
    }
}
