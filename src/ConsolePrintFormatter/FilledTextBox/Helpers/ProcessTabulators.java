package ConsolePrintFormatter.FilledTextBox.Helpers;

import java.util.ArrayList;
import java.util.List;

public class ProcessTabulators {
    private String processTabulators(String str){
        var sb = new StringBuilder();
        for (var i = 0;i < str.length();i++){
            var c = str.charAt(i);
            if(c == '\t')
                sb.append("    ");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    public List<String> process(List<String> strings){
        var processed = new ArrayList<String>(strings.size());
        strings.forEach(s -> processed.add(processTabulators(s)));
        return processed;
    }
}
