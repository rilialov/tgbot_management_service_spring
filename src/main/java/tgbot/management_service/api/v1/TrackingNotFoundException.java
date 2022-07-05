package tgbot.management_service.api.v1;

class TrackingNotFoundException extends RuntimeException {

    TrackingNotFoundException(long id) {
        super("Could not find tracking " + id);
    }
}
