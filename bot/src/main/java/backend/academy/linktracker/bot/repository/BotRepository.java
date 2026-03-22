package backend.academy.linktracker.bot.repository;


import backend.academy.linktracker.bot.model.dto.TelegramMessageRequestDto;
import org.springframework.stereotype.Repository;
import

@Repository
public interface BotRepository {

    void save(TelegramMessageRequestDto dto);
}
