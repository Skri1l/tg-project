package backend.academy.linktracker.bot.repository;

import backend.academy.linktracker.bot.mapper.TelegramMessageMapper;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBotRepository implements BotRepository {

    private final List<TelegramUpdateDto> storage = new ArrayList<>();
    private final TelegramMessageMapper mapper;

    public InMemoryBotRepository(TelegramMessageMapper mapper) {
        this.mapper = mapper;
    }

    public TelegramUpdateDto save(TelegramUpdateDto entity) {
        storage.add(entity);
        return mapper.toEntity(entity);
    }

    public int update(TelegramUpdateEntity entity) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).updateId(), entity.getUpdateId())) {
                storage.set(i, entity);
                return 1;
            }
        }
        return 0;
    }

    public void delete(Long id) {
        storage.removeIf(e -> Objects.equals(e.chatId(), id));
    }

    public void delete(Integer updateId) {
        storage.removeIf(e -> Objects.equals(e.updateId(), updateId));
    }

    public Optional<TelegramUpdateDto> getTelegramUpdate(Long id) {
        return storage.stream()
            .filter(e -> Objects.equals(e.chatId(), id));
    }
}
