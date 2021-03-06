package ru.geracimov.otus.spring.hw12librarymongo.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class GenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    private final GenreRepository gRepo;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        ObjectId id = (ObjectId) source.get("_id");
        gRepo.removeGenreFromAllBooksByGenreId(id);
    }

}
