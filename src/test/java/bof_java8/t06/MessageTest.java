package bof_java8.t06;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MessageTest {

    @Test
    public void should_get_message() {
        Message message = new MessageImpl("Hello");

        assertThat(message.getMessage()).isEqualTo("Hello");
//        assertThat(message.getPrefixedMessage()).isEqualTo("It says: Hello");
    }

    private static interface Message {
        String getMessage();
    }

    private static final class MessageImpl implements Message {
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
