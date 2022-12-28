package rojinaReview.model.utilities;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface GenericStaffDAO {

    Object doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException;
}
