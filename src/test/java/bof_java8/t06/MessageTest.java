package bof_java8.t06;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MessageTest {

    public interface Message {
        String getMessage();
    }

    @Test
    public void should_get_message() {
        Message message = buildMessage("Hello");

        assertThat(message.getMessage()).isEqualTo("Hello");
//        assertThat(message.getPrefixedMessage()).isEqualTo("It says: Hello");
    }

    /*
     * You cannot access to what is below!!!
     */

    private Message buildMessage(String message) {
        return new MessageImpl(message);
    }

    private final class MessageImpl implements Message {
        private final String message;

        public MessageImpl(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

}
