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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommandHelp implements ICommand {

	private Minecraft mc = Minecraft.getMinecraft();

	public Client vapuClient =null;

	public CommandHelp(Client vapuClient) {
		this.vapuClient = vapuClient;
	}
	
	
	@Override
	public String getCommandName() {
		return "vapuhelp";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/vapuhelp";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Helper.sendMessageWithoutPrefix("Command Help:");
		Helper.sendMessageWithoutPrefix("Bind a Module Hot Key: /bind <Module> <key>");
		Helper.sendMessageWithoutPrefix("Save a config: /save <Config name>");
		Helper.sendMessageWithoutPrefix("Load a config: /config <Config name>");
		Helper.sendMessageWithoutPrefix("Delete a config: /del <Config name>");
		Helper.sendMessageWithoutPrefix("Enable a Module: /enbale <module>");
		Helper.sendMessageWithoutPrefix("Disable a Module: /disable <module>");
		Helper.sendMessageWithoutPrefix("Add/Delete friend: /f [add/del] <Player Name>");
		Helper.sendMessageWithoutPrefix("Show Friend List: /f list");
		Helper.sendMessageWithoutPrefix("Get OP in Hypixel: /forceop");
		Helper.sendMessageWithoutPrefix("Get a super diamond sword in OP server: /getsword");
		Helper.sendMessageWithoutPrefix("Generate a Manbox: /manbox <birthday>");
		Helper.sendMessageWithoutPrefix("Generate a Womanbox: /womanbox <birthday>");
		Helper.sendMessageWithoutPrefix("Generate a Phone namber: /phone");
		Helper.sendMessageWithoutPrefix("Generate a location: /saylocal");
		Helper.sendMessageWithoutPrefix("Toggle Module: /t <module> | /toggle <module>");
		Helper.sendMessageWithoutPrefix("Change watermark: /watermark <New watermark>");
		Helper.sendMessageWithoutPrefix("Copy your ingame name: /n | /ign");
		Helper.sendMessageWithoutPrefix("Change your game window title: /title <New Title>");
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