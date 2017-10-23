package jersey.companies;

import java.util.List;

/**
 *
 * @author crijk
 */
public class PagedCompanies extends PagedList<Company> {
    
    public PagedCompanies(List<Company> list) {
        super(list);
    }    
}
