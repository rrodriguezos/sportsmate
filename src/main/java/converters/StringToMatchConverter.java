

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.MatchService;
import domain.Match;
@Component
@Transactional
public class StringToMatchConverter implements Converter<String,Match>{
@Autowired
private MatchService matchService;
@Override
public Match convert(String arg0) {
return matchService.findOne(Integer.valueOf(arg0));
}
}
