package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RequestTeam;
@Component
@Transactional
public class RequestTeamtoStringConverter implements Converter<RequestTeam, String> {
	@Override
	public String convert(RequestTeam arg0) {
	return String.valueOf(arg0.getId());
	}
}
