package mc.bape.Command;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import mc.bape.utils.esufaker;
import mc.bape.vapu.Client;
import mc.bape.module.Module;
import mc.bape.manager.ModuleManager;
import mc.bape.utils.Helper;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommandManbox implements ICommand {

	private Minecraft mc = Minecraft.getMinecraft();

	public Client vapuClient =null;

	public CommandManbox(Client vapuClient) {
		this.vapuClient = vapuClient;
	}
	
	
	@Override
	public String getCommandName() {
		return "manbox";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/manbox birthday";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1 && !ModuleManager.getModule("NoCommand").getState()) {
			try {
				mc.thePlayer.sendChatMessage("姓名："+ esufaker.getName("man")+" 身份证："+ esufaker.getSFZ(args[0]));
			}
			catch(Exception e) {
			}
		}
		else {
		}
	}
	

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender.getCommandSenderEntity() instanceof EntityPlayer;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		//this.exampleMod.log(MinecraftServer.getServer().getAllUsernames().toString());
//		for(String s : MinecraftServer.getServer().getAllUsernames()) {
//			this.exampleMod.log(s);
//		}
		//return Collections.<String>emptyList();
		List<String> list = new ArrayList<String>();
		try {
			//this.exampleMod.log(MinecraftServer.getServer().getConfigurationManager().toString());
			
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