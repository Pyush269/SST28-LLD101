/**
 * LSP contract: send() always returns a non-null SendResult.
 * If the channel cannot deliver, isError is true with a message.
 * No sender should throw for a valid (non-null) Notification.
 */
public class SendResult {
    public final boolean isError;
    public final String errorMessage;

    private SendResult(boolean isError, String errorMessage) {
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public static SendResult ok() { return new SendResult(false, null); }
    public static SendResult error(String msg) { return new SendResult(true, msg); }
}
