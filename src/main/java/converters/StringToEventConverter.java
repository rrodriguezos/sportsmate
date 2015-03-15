

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.EventService;
import domain.Event;
@Component
@Transactional
public class StringToEventConverter implements Converter<String,Event>{
@Autowired
private EventService eventService;
@Override
public Event convert(String arg0) {
return eventService.findOne(Integer.valueOf(arg0));
}
}
