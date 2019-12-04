import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerConsumerTaskTest {

    @Test
    void main() {
        ProducerConsumerTask task = new ProducerConsumerTask(200);
        task.doAwfulThings(7, 5, 100);
        Assert.assertTrue(task.isAllTreadsDaemons());
    }
}