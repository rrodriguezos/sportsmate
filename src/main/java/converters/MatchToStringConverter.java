
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Match;
@Component
@Transactional
public class MatchToStringConverter implements Converter<Match, String> {
@Override
public String convert(Match arg0) {
return String.valueOf(arg0.getId());
}
}
