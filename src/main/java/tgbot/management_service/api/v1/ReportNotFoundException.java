package tgbot.management_service.api.v1;

class ReportNotFoundException extends RuntimeException {

    ReportNotFoundException(long id) {
        super("Could not find report " + id);
    }
}
