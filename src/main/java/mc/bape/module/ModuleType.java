package mc.bape.module;

public enum ModuleType
{
    Combat("Combat", "\u6218\u6597\u7c7b"), 
    Blatant("Blatant", "\u66b4\u529b\u7c7b"), 
    Render("Render", "\u89c6\u89c9\u7c7b"), 
    Movement("Movement", "\u79fb\u52a8\u7c7b"), 
    Player("Player", "\u73a9\u5bb6\u7c7b"), 
    World("World", "\u4e16\u754c\u7c7b"), 
    Other("Other", "\u5176\u4ed6\u7c7b"), 
    Global("Global", "\u5168\u5c40\u8bbe\u7f6e");
    
    private final String name;
    private final String ChineseName;
    
    private ModuleType(final String name, final String chineseName) {
        this.name = name;
        this.ChineseName = chineseName;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public String getName() {
        return this.name;
    }
}
