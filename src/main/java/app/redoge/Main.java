package app.redoge;

import app.redoge.util.EventWorker;
import app.redoge.util.FilesUtil;

import java.io.IOException;

public class Main {
    private static final FilesUtil filesUtil = new FilesUtil();
    private static final EventWorker worker = new EventWorker(new OrderBook());

    public static void main(String[] args) throws IOException {
        var lines = filesUtil.getLinesByFileName("input.txt");
        for(var line: lines){
            worker.doByLine(line);
        }
        var output = worker.getOutput();
        filesUtil.writeFileByLinesAndFileName(output, "output.txt");
    }
}