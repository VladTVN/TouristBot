package projectTouristBot.bot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import projectTouristBot.services.CityService;

@Component
@PropertySource("classpath:bot.properties")
public class TouristBot extends TelegramLongPollingBot {
    private static final String GREETINGS = "Привет! Я туристический бот. Введите название города, и я расскажу о нем все, что знаю.";
    @Value("${bot.username}")
    private  String botUsername;
    @Value("${bot.token}")
    private  String botToken;

    @Autowired
    private CityService cityServiceImpl;

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    public void onUpdateReceived(Update update) {
        if(update.getMessage().hasText()){
        Message message = update.getMessage();
        sendMsg(message);
        }else return;
    }

    private void sendMsg(Message message) {
        String cityName = message.getText().toLowerCase();

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());

        if(message.getText().equals("/start")){
            sendMessage.setText(GREETINGS);
        }else {
            sendMessage.setText(cityServiceImpl.findByCityName(cityName).toString());
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
