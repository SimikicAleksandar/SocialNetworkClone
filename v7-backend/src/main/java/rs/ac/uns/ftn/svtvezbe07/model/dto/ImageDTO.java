package rs.ac.uns.ftn.svtvezbe07.model.dto;

import rs.ac.uns.ftn.svtvezbe07.model.entity.Image;

import javax.validation.constraints.NotNull;

public class ImageDTO {
    @NotNull
    private String path;

    public ImageDTO (Image image) {
        this.path = image.getPath();
    }

    public ImageDTO() {}
}
