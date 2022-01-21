package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TransferAggregator {

    public List<TransferPerClient> readTransfer(Path path) {
        List<String> lines = readLines(path);
        return summarize(lines);
    }

    private List<String> readLines(Path path) {
        try{
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file.", ioe);
        }
    }

    private List<TransferPerClient> summarize(List<String> lines) {
        Map<String, TransferPerClient> transfers = new TreeMap<>();
        for (String s: lines) {
            String[] temp = s.split(",");
            String sourceClientId = temp[0];
            String targetClientId = temp[1];
            int amount = Integer.parseInt(temp[2]);

            TransferPerClient source = findOrInsert(transfers, sourceClientId);
            source.decrease(amount);

            TransferPerClient target = findOrInsert(transfers, targetClientId);
            target.increase(amount);
        }
        return new ArrayList<>(transfers.values());
    }

    private TransferPerClient findOrInsert(Map<String, TransferPerClient> transfers, String clientId) {
        TransferPerClient transfer = transfers.get(clientId);
        if (transfer == null) {
            transfer = new TransferPerClient(clientId);
            transfers.put(clientId, transfer);
        }
        return transfer;
    }

    public static void main(String[] args) {
        List<TransferPerClient> transfers = new TransferAggregator()
                .readTransfer(Paths.get("C:\\training\\java-sv2-dailiy-labs13\\week13-lab-tasks\\src\\main\\resources\\transfers.csv"));

        for (TransferPerClient t: transfers) {
            System.out.printf("%s %,12d %5d\n",
                    t.getClientId(), t.getSum(), t.getNumberOfTransactions());
        }
    }
}