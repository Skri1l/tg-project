package backend.academy.linktracker.bot.repository;

import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import java.util.List;
import java.util.Optional;
import backend.academy.linktracker.bot.model.TelegramUpdateEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository {
    TelegramUpdateEntity save(TelegramUpdateEntity entity);
    int update(TelegramUpdateEntity entity);
    void delete(Long id);
    void delete(Integer updateId);
    Optional<TelegramUpdateEntity> getTelegramUpdate(Long id);
    List<TelegramUpdateEntity> getTelegramUpdates(List<Long> ids);
}
