package tgbot.management_service.controller;

class TaskNotFoundException extends RuntimeException {

    TaskNotFoundException(long id) {
        super("Could not find task " + id);
    }
}
