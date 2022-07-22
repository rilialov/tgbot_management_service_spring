package tgbot.management_service.api.v1;

class EntityNotFoundException extends RuntimeException {

    EntityNotFoundException(String entity, long id) {
        super("Could not find " + entity + " " + id);
    }

}
