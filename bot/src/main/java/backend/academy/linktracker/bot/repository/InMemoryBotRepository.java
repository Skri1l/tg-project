package backend.academy.linktracker.bot.repository;

import backend.academy.linktracker.bot.model.TelegramUpdateEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBotRepository implements BotRepository {
    private static final List<TelegramUpdateEntity> storage = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public TelegramUpdateEntity save(TelegramUpdateEntity entity) {
        entity.setId(idCounter++);
        storage.add(entity);
        return entity;
    }

    @Override
    public int update(TelegramUpdateEntity entity) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUpdateId(), entity.getUpdateId())) {
                storage.set(i, entity);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void delete(Long id) {
        storage.removeIf(e -> Objects.equals(e.getChatId(), id));
    }

    @Override
    public void delete(Integer updateId) {
        storage.removeIf(e -> Objects.equals(e.getUpdateId(), updateId));
    }

    @Override
    public Optional<TelegramUpdateEntity> getTelegramUpdate(Long id) {
        return storage.stream()
            .filter(e -> Objects.equals(e.getChatId(), id))
            .findFirst();
    }

    @Override
    public List<TelegramUpdateEntity> getTelegramUpdates(List<Long> ids) {
        return storage.stream()
            .filter(e -> ids.contains(e.getChatId()))
            .toList();
    }
}
