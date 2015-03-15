
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Friendship;
@Component
@Transactional
public class FriendshipToStringConverter implements Converter<Friendship, String> {
@Override
public String convert(Friendship arg0) {
return String.valueOf(arg0.getId());
}
}
