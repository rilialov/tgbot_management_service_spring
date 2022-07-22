package tgbot.management_service.api.v1;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import tgbot.management_service.entity.Report;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReportsModelAssembler implements RepresentationModelAssembler<Report, EntityModel<Report>> {

    @Override
    public EntityModel<Report> toModel(Report report) {
        return EntityModel.of(report,
                linkTo(methodOn(ReportsController.class).findById(report.getId())).withSelfRel());
    }
}
