package de.h2cl.spring.data.foundationdb.repository.tmp;

import java.util.Random;

import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;
import com.apple.foundationdb.KeyValue;
import com.apple.foundationdb.Transaction;
import com.apple.foundationdb.TransactionContext;
import com.apple.foundationdb.subspace.Subspace;
import com.apple.foundationdb.tuple.Tuple;

public class QueueExample {

    private static final FDB fdb;
    private static final Database db;
    private static final Subspace queue;
    private static final Random randno;

    static {
        fdb = FDB.selectAPIVersion(510);
        db = fdb.open();
        queue = new Subspace(Tuple.from("Q"));
        randno = new Random();
    }

    // Remove the top element from the queue.
    public static Object dequeue(TransactionContext tcx) {
        final KeyValue item = firstItem(tcx);
        if (item == null) {
            return null;
        }

        // Remove from the top of the queue.
        tcx.run((Transaction tr) -> {
            tr.clear(item.getKey());
            return null;
        });

        // Return the old value.
        return Tuple.fromBytes(item.getValue()).

                get(0);
    }

    // Add an element to the queue.
    public static void enqueue(TransactionContext tcx, final Object value) {
        tcx.run((Transaction tr) -> {
            byte[] rands = new byte[20];
            randno.nextBytes(rands); // Create random seed to avoid conflicts.
            tr.set(queue.subspace(Tuple.from(lastIndex(tr) + 1, rands)).pack(),
                    Tuple.from(value).pack());

            return null;
        });
    }

    // Get the top element of the queue.
    private static KeyValue firstItem(TransactionContext tcx) {
        return tcx.run(tr -> {
            for (KeyValue kv : tr.getRange(queue.range(), 1)) {
                return kv;
            }

            return null; // Empty queue. Should never be reached.
        });
    }

    // Get the last index in the queue.
    private static long lastIndex(TransactionContext tcx) {
        return tcx.run((Transaction tr) -> {
            for (KeyValue kv : tr.snapshot().getRange(queue.range(), 1, true)) {
                return (long) queue.unpack(kv.getKey()).get(0);
            }
            return 0L;
        });
    }
}
