package ConsolePrintFormatter.FilledTextBox;

import ConsolePrintFormatter.Objects.ConsoleObject;
import ConsolePrintFormatter.FilledTextBox.Helpers.BuildColorCSI;
import ConsolePrintFormatter.FilledTextBox.Helpers.DecorateLines;
import ConsolePrintFormatter.FilledTextBox.Helpers.ProcessTabulators;
import ConsolePrintFormatter.Utils.LineTools.LineMerger;
import ConsolePrintFormatter.Utils.LineTools.StringSplitter;
import ConsolePrintFormatter.Utils.Normalize.NormalizeStringLines;
import ConsolePrintFormatter.Utils.Padding.CreatePadding;
import java.awt.*;

public class BuildFilledBox {
    final String _str;
    private int _verticalPadding = 0;
    private int _horizontalPadding = 0;
    private int _verticalMargin = 0;
    private int _horizontalMargin = 0;
    private Color _bgColor = new Color(0,0,0);
    private Color _fgColor = new Color(255,255,255);

    StringSplitter _splitString = new StringSplitter();
    NormalizeStringLines _normalize = new NormalizeStringLines();
    CreatePadding _surroundWithSpaces = new CreatePadding();
    LineMerger _mergeLines = new LineMerger();
    DecorateLines _fillColor = new DecorateLines();
    BuildColorCSI _buildCSI = new BuildColorCSI();
    ProcessTabulators _processTabulators = new ProcessTabulators();

    public BuildFilledBox(String str){
        _str = str;
    }

    public BuildFilledBox setBackroundColor(Color color){
        _bgColor = color;
        return this;
    }

    public BuildFilledBox setTextColor(Color color){
        _fgColor = color;
        return this;
    }

    public BuildFilledBox setPadding(int horizontal, int vertical){
        _verticalPadding = vertical;
        _horizontalPadding = horizontal;
        return this;
    }

    public BuildFilledBox setMargins(int horizontal, int vertical){
        _horizontalMargin = horizontal;
        _verticalMargin = vertical;
        return this;
    }

    public ConsoleObject build(){
        var lines = _splitString.split(_str);
        var processed = _processTabulators.process(lines);
        var normalizedLines = _normalize.normalize(processed);
        _surroundWithSpaces.create(normalizedLines, _verticalPadding, _horizontalPadding);
        var bgLines = _fillColor.fill(normalizedLines, _buildCSI.buildBg(_bgColor));
        var fgLines = _fillColor.fill(bgLines,_buildCSI.buildFg(_fgColor));
        _surroundWithSpaces.create(fgLines,_verticalMargin,_horizontalMargin);
        var result = _mergeLines.merge(fgLines);
        return new ConsoleObject(result);
    }
}
