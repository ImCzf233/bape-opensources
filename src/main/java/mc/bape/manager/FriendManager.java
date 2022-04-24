package mc.bape.manager;

import java.util.*;

public class FriendManager
{
    private static HashMap<String, String> friends;
    
    public static boolean isFriend(final String name) {
        return FriendManager.friends.containsKey(name);
    }
    
    public static String getAlias(final Object friends2) {
        return FriendManager.friends.get(friends2);
    }
    
    public static HashMap<String, String> getFriends() {
        return FriendManager.friends;
    }
    
    public static void init() {
        FriendManager.friends = new HashMap<String, String>();
        final List<String> frriends = ConfigManager.read("Friends.cfg");
        for (final String v : frriends) {
            if (v.contains(":")) {
                final String name = v.split(":")[0];
                final String alias = v.split(":")[1];
                FriendManager.friends.put(name, alias);
            }
            else {
                FriendManager.friends.put(v, v);
            }
        }
    }
}
