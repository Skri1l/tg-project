package backend.academy.linktracker.bot.repository;

import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository {
    TelegramUpdateDto save(TelegramUpdateEntity entity);
    int update(TelegramUpdateEntity entity);
    void delete(Long id);
    void delete(Integer updateId);
    Optional<TelegramUpdateDto> getTelegramUpdate(Long id);
    List<TelegramUpdateDto> getTelegramUpdates(List<Long> ids);
}
