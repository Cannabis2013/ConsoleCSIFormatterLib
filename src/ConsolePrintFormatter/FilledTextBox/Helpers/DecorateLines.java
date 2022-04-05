package ConsolePrintFormatter.FilledTextBox.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DecorateLines {
    private String reset = "\033[m";

    private String formatLine(String str, String csiCode){
        return  csiCode + str + reset;
    }

    private List<String> formatLines(List<String> strings, String csiCode){
        var formattedLines = strings.stream().map(l -> formatLine(l,csiCode));
        var list = formattedLines.collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

    BuildColorCSI _buildColorCode = new BuildColorCSI();

    public List<String> fill(List<String> lines, String csiCode){
        var filledLines = formatLines(lines,csiCode);
        return filledLines;
    }
}
