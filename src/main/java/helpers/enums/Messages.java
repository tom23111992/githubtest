package helpers.enums;

public enum Messages {

    BRANCH_CREATED("Branch created."),
    REPO_DELETED("was successfully deleted.")
    ;

    private final String message;

    private Messages(String s) {

        message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
