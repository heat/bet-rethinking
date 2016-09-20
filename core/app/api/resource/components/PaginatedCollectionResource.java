package api.resource.components;

import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;

import java.net.URI;
import java.util.List;

public class PaginatedCollectionResource<T extends HalResource> implements HalResource {

    final int index;
    final int limit;

    final CollectionResource<T> collectionResource;

    public PaginatedCollectionResource(int index, int limit, CollectionResource<T> collectionResource) {
        this.index = index;
        this.limit = limit;
        this.collectionResource = collectionResource;
    }

    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        int size = collectionResource.size();
        List<T> items = collectionResource.items
                .subList(index, size );
        return collectionResource.representationBuilder();
    }

    @Override
    public URI location() {
        return null;
    }
}
