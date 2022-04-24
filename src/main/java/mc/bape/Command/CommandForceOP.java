package mc.bape.Command;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldSettings;
import mc.bape.vapu.Client;
import mc.bape.module.Module;
import mc.bape.manager.ModuleManager;
import mc.bape.utils.Helper;

import java.util.*;

import static org.lwjgl.input.Keyboard.KEY_NONE;

public class CommandForceOP implements ICommand {

	private Minecraft mc = Minecraft.getMinecraft();

	public Client vapuClient =null;

	public CommandForceOP(Client vapuClient) {
		this.vapuClient = vapuClient;
	}
	
	
	@Override
	public String getCommandName() {
		return "forceop";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/forceop";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		mc.thePlayer.sendChatMessage("/play solo_insane");
		Helper.sendMessageWithoutPrefix(mc.thePlayer.getName() + " ForceOP Enabled!");
		Objects.requireNonNull(ModuleManager.getModule("AntiBot")).setState(false);
		Objects.requireNonNull(ModuleManager.getModule("ClickGui")).setState(false);
		Objects.requireNonNull(ModuleManager.getModule("Killaura")).setKey(KEY_NONE);
		Objects.requireNonNull(ModuleManager.getModule("Speed")).setKey(KEY_NONE);
		Objects.requireNonNull(ModuleManager.getModule("AntiBot")).setKey(KEY_NONE);
		Objects.requireNonNull(ModuleManager.getModule("Fly")).setKey(KEY_NONE);
		Objects.requireNonNull(ModuleManager.getModule("ClickGui")).setKey(KEY_NONE);
		Objects.requireNonNull(ModuleManager.getModule("Killaura")).setState(true);
		Objects.requireNonNull(ModuleManager.getModule("InvMove")).setState(true);
		Objects.requireNonNull(ModuleManager.getModule("Fly")).setState(true);
		Objects.requireNonNull(ModuleManager.getModule("Speed")).setState(true);
		mc.thePlayer.sendChatMessage("Hello Everyone. I'm hacking! Report me! Hypixel LLLLL, type /wdr " + mc.thePlayer.getName() + " cheating");
		mc.thePlayer.sendChatMessage("/wdr " + mc.thePlayer.getName() + " fly speed killaura");
	}
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender.getCommandSenderEntity() instanceof EntityPlayer;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		List<String> list = new ArrayList<String>();
		try {
			Ordering<NetworkPlayerInfo> field_175252_a = Ordering.from(new Comparator<NetworkPlayerInfo>()
	        {
	            private void PlayerComparator()
	            {
	            }

	            public int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_)
	            {
	                ScorePlayerTeam scoreplayerteam = p_compare_1_.getPlayerTeam();
	                ScorePlayerTeam scoreplayerteam1 = p_compare_2_.getPlayerTeam();
	                return ComparisonChain.start().compareTrueFirst(p_compare_1_.getGameType() != WorldSettings.GameType.SPECTATOR, p_compare_2_.getGameType() != WorldSettings.GameType.SPECTATOR).compare(scoreplayerteam != null ? scoreplayerteam.getRegisteredName() : "", scoreplayerteam1 != null ? scoreplayerteam1.getRegisteredName() : "").compare(p_compare_1_.getGameProfile().getName(), p_compare_2_.getGameProfile().getName()).result();
	            }
	        });
			String last_s = "";
			for (NetworkPlayerInfo networkPlayerInfoIn : field_175252_a.sortedCopy(mc.thePlayer.sendQueue.getPlayerInfoMap())) {
				String s = networkPlayerInfoIn.getDisplayName() != null && false ? networkPlayerInfoIn.getDisplayName().getFormattedText() : networkPlayerInfoIn.getGameProfile().getName();
				if (!s.equals(last_s))
					list.add(s);
				last_s = s;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
        String[] astring = new String[list.size()];

        for (int i = 0; i < list.size(); ++i)
        {
            astring[i] = list.get(i);
        }

		return CommandBase.getListOfStringsMatchingLastWord(args, astring);
		//return MinecraftServer.getTabCompletions();
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return this.getCommandName().compareTo(arg0.getCommandName());
	}

	@Override
	public List<String> getCommandAliases() {
		// TODO Auto-generated method stub
		return Collections.<String>emptyList();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
}