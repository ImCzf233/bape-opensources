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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommandN implements ICommand {

	private Minecraft mc = Minecraft.getMinecraft();

	public Client vapuClient =null;

	public CommandN(Client vapuClient) {
		this.vapuClient = vapuClient;
	}
	
	
	@Override
	public String getCommandName() {
		return "n";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/n";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(mc.getSession().getUsername()), null);
		Helper.sendMessage("Copied!");
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