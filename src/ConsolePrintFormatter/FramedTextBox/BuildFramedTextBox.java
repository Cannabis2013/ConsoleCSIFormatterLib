package ConsolePrintFormatter.FramedTextBox;

import ConsolePrintFormatter.Objects.ConsoleObject;
import ConsolePrintFormatter.FilledTextBox.Helpers.ProcessTabulators;
import ConsolePrintFormatter.FramedTextBox.AddFrame.AddFrame;
import ConsolePrintFormatter.Utils.LineTools.LineMerger;
import ConsolePrintFormatter.Utils.LineTools.StringSplitter;
import ConsolePrintFormatter.Utils.Normalize.NormalizeStringLines;
import ConsolePrintFormatter.Utils.Padding.CreatePadding;

public class BuildFramedTextBox {
    private final String _str;
    private int _vPad = 0;
    private int _hPad = 0;
    private int _verticalMargin = 0;
    private int _horizontalMargin = 0;

    StringSplitter _splitString = new StringSplitter();
    NormalizeStringLines _normalize = new NormalizeStringLines();
    CreatePadding _createPaddings = new CreatePadding();
    AddFrame _addFrame = new AddFrame();
    LineMerger _mergeLines = new LineMerger();
    ProcessTabulators _processTabulators = new ProcessTabulators();

    public BuildFramedTextBox(String str) {
        _str = str;
    }

    public BuildFramedTextBox setPadding(int horizontal, int vertical){
        _vPad = vertical;
        _hPad = horizontal;
        return this;
    }

    public BuildFramedTextBox setMargins(int horizontal, int vertical){
        _horizontalMargin = horizontal;
        _verticalMargin = vertical;
        return this;
    }

    public ConsoleObject build(){
        var lines = _splitString.split(_str);
        var processed = _processTabulators.process(lines);
        var normalized = _normalize.normalize(processed);
        var padded = _createPaddings.create(normalized, _vPad, _hPad);
        var merged = _mergeLines.merge(padded);
        var result = _addFrame.add(merged);
        return new ConsoleObject(result);
    }
}
