

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.InvoiceService;
import domain.Invoice;
@Component
@Transactional
public class StringToInvoiceConverter implements Converter<String,Invoice>{
@Autowired
private InvoiceService invoiceService;
@Override
public Invoice convert(String arg0) {
return invoiceService.findOne(Integer.valueOf(arg0));
}
}
