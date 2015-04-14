
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Tournament;
@Component
@Transactional
public class TournamentToStringConverter implements Converter<Tournament, String> {
@Override
public String convert(Tournament arg0) {
return String.valueOf(arg0.getId());
}
}
