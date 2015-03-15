
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Event;
@Component
@Transactional
public class EventToStringConverter implements Converter<Event, String> {
@Override
public String convert(Event arg0) {
return String.valueOf(arg0.getId());
}
}
