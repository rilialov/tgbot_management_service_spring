package tgbot.management_service.api.v1;

class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException(String entity, long id) {
        super("Could not find " + entity + " " + id);
    }

}
