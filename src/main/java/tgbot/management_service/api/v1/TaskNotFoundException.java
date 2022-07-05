package tgbot.management_service.api.v1;

class TaskNotFoundException extends RuntimeException {

    TaskNotFoundException(long id) {
        super("Could not find task " + id);
    }
}
