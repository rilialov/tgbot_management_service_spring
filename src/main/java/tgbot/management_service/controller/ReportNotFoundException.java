package tgbot.management_service.controller;

class ReportNotFoundException extends RuntimeException {

    ReportNotFoundException(long id) {
        super("Could not find report " + id);
    }
}
