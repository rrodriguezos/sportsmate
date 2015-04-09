

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.MessageService;
import domain.Message;
@Component
@Transactional
public class StringToMessageConverter implements Converter<String,Message>{
@Autowired
private MessageService messageService;
@Override
public Message convert(String arg0) {
return messageService.findOne(Integer.valueOf(arg0));
}
}
