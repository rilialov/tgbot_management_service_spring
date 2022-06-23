package tgbot.management_service.controller;

class TrackingNotFoundException extends RuntimeException {

    TrackingNotFoundException(long id) {
        super("Could not find tracking " + id);
    }
}
