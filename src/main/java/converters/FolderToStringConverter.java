
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Folder;
@Component
@Transactional
public class FolderToStringConverter implements Converter<Folder, String> {
@Override
public String convert(Folder arg0) {
return String.valueOf(arg0.getId());
}
}
