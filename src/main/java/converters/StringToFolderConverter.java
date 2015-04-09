

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.FolderService;
import domain.Folder;
@Component
@Transactional
public class StringToFolderConverter implements Converter<String,Folder>{
@Autowired
private FolderService folderService;
@Override
public Folder convert(String arg0) {
return folderService.findOne(Integer.valueOf(arg0));
}
}
