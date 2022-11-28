package starter.models;

public class ItemsNotFoundResponse {

    private boolean error;
    private String message;
    private String requested_item;
    private String served_by;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequested_item() {
        return requested_item;
    }

    public void setRequested_item(String requested_item) {
        this.requested_item = requested_item;
    }

    public String getServed_by() {
        return served_by;
    }

    public void setServed_by(String served_by) {
        this.served_by = served_by;
    }
}
